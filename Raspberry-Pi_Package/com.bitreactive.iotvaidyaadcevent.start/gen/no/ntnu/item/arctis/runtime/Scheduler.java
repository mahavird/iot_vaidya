package no.ntnu.item.arctis.runtime;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import no.ntnu.item.arctis.runtime.IStateMachine.Break;
import no.ntnu.item.arctis.runtime.IStateMachine.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scheduler implements Runnable {

	static final boolean debug = false;

	private boolean keepRunning;
	private Runtime register;
	private final List<IStateMachine> uninitatedSm = Collections.synchronizedList(new LinkedList<IStateMachine>());
	private final List<IStateMachine> smQueues = Collections.synchronizedList(new LinkedList<IStateMachine>());
	private final BreakQueue breaks = new BreakQueue();
	private final TimerQueue timers = new TimerQueue();
	private int index = 0;
	private Thread thread;
	
	private static final String PREFIX_STEP_START = "/";
	private static final String PREFIX_STEP_END = "\\";

	public final Logger logger = LoggerFactory.getLogger("Runtime");

	private long lastStartOfTransition;
	private boolean isCurrentlyExecutingATransition = false;

	private boolean forwardAssertionErrors = false;
		
	/**
	 * set to true so that only one transition is executed before an input (return) from the console is needed to proceed.
	 */
	private final static boolean stepping = false;
	private Debugger debugger;

	/**
	 * Causes the scheduler to forward assertion exception raised in transitions
	 * instead of catching them. This is needed when the system is executed as a
	 * test case using JUnit, and the verdicts should be catched by the JUnit runtime.
	 *
	 * @param forwardAssertionErrors
	 */
	public void setForwardAssertionErrors(boolean forwardAssertionErrors) {
		this.forwardAssertionErrors = forwardAssertionErrors;
	}

	public Scheduler(Runtime register) {
		this.register = register;
	}
	
	public Runtime getRuntime() {
		return register;
	}

	public void start(Thread thread) {
		this.thread = thread;
		thread.start();
	}

	public void add(IStateMachine sm) {
		uninitatedSm.add(sm);
	}

	private void deregisterStateMachine(IStateMachine sm) {
		logger.debug("Deregistering state machine: " + sm.getSessionId());
		register.terminateStateMachine(sm);	
		smQueues.remove(sm);
		breaks.removeStateMachine(sm);
		timers.removeStateMachine(sm);
		uninitatedSm.remove(sm);
		//sm.deregisterAllAliases();

		// TODO also remove all "child" state machines
		// for now, we do NOT destroy the scheduler, since we could wait for 
		// session creating signals from outside.
//		if (queues.isEmpty()) {
//			logger.debug("No more state machines to execute, closing scheduler!");
//			destroy();
//		}
	}
	
	private String currentStep = null;
	private void logStep(String step) {
		currentStep = step;
	}

	public void run() {
		if (stepping) {
			debugger = new Debugger((Register) register.register);
		}
		
		/*
		 * This method implements a prioritized round robin scheduling scheme
		 * with the following prioritization: (1) Breaks, (2) Timers, (3) Uninitiated and
		 * registered state machines, (4) Normal messages
		 */
		keepRunning = true;
		logger.debug("Scheduler starts running.");

		while (keepRunning) {
			// breaks
			while (!breaks.isEmpty()) {
				Break b = breaks.consume();
				executeTimerOrBreak(b.id, b.stateMachine, false);
			}
			
			// expired timers
			while (breaks.isEmpty() && timers.hasExpiredTimer()) {
				Timer t = timers.consume();
				executeTimerOrBreak(t.id, t.stateMachine, true);
			}

			// state machines to initialize
			while (breaks.isEmpty() && !uninitatedSm.isEmpty()) {
				logger.debug("Initiating state machine(s).");
				IStateMachine sm = uninitatedSm.remove(0);
				if (sm != null) {
					executeUnintiatedSm(sm);
				}
			}

			// next state machine in the queue
			if (breaks.isEmpty() && !smQueues.isEmpty()) {
				if (index > smQueues.size() - 1) {
					index = 0;
				}
				IStateMachine sm = smQueues.get(index++);
				if (sm != null && !sm.getInputQueue().isEmpty()) {
					executeSmTransition(sm, sm.getInputQueue().removeFirstMessage());
				}
			} else if (keepRunning && breaks.isEmpty() && timers.isEmpty() && uninitatedSm.isEmpty()){
				logger.warn("Scheduler is running, but could not find a state machine to execute!");
			}

			if (breaks.isEmpty() && uninitatedSm.isEmpty() && allInputQueuesAreEmpty() && keepRunning) {
				/*
				 * Wait until next timer expires OR an incoming message
				 * notifies the object, see deliver().
				 */
				try {
					synchronized (this) {
						if (timers.isEmpty()) {
							logger.trace("All events processed, no pending timers, will wait for new event.");
							wait();
						} else {
							long wait = timers.getTimeToNextExpiration();
							if (wait > 10) {
								logger.trace("All events processed, will wait for new event or next timeout in {} ms.", wait);
								wait(wait);
							}
						}
					}
				} catch (Throwable e) {
					// Ignore.
					e.printStackTrace();
				}
			} 
		}
	}

	private void executeTimerOrBreak(String id, IStateMachine sm, boolean isTimer) {
		int result = IStateMachine.EXCEPTION;
		if (isTimer) {
			logger.trace(PREFIX_STEP_START + " TIMEOUT {} in machine {}", id, sm.getClass().getSimpleName());
		} else {
			logger.trace(PREFIX_STEP_START + " BREAKOUT {} in machine {}", id, sm.getClass().getSimpleName());
		}
		long time = System.currentTimeMillis();
		
		try {
			isCurrentlyExecutingATransition = true;
			lastStartOfTransition = System.currentTimeMillis();
			result = sm.fireTimer(id);
			isCurrentlyExecutingATransition = false;
		} catch (Throwable t) {
			handleException(t);
			applicationErrorDuringFireTimer(sm, "", id, t);
			register.applicationErrorDetected();
		}
		
		switch (result) {
		
		case IStateMachine.CONSUME_SIGNAL:
			logger.trace(PREFIX_STEP_END + "--> Transition executed within {} ms.", System.currentTimeMillis() - time);
			break;
			
		case IStateMachine.TERMINATE_MACHINE:
			if (index > 0 && smQueues.size() > 1) {
				assert smQueues.contains(sm);
				if (smQueues.indexOf(sm) < index) {
					index--;
				}
			}
			deregisterStateMachine(sm);
			logger.trace(PREFIX_STEP_END + "--> Transition executed within {} ms, machine terminated.", System.currentTimeMillis() - time);
			break;
			
		case IStateMachine.DISCARD_SIGNAL:
			logger.warn(PREFIX_STEP_END + "--> Discarded timout event: {}", id, sm.getClass().getSimpleName());
			break;

		case IStateMachine.BLOCKING_CHOICE:
			logger.error(PREFIX_STEP_END + "--> Blocking decision in machine.");
			break;
			
		default:
			break;
		}
		
		debugStep();
	}
	
	private void executeUnintiatedSm(IStateMachine sm) {
		int result = IStateMachine.EXCEPTION;
		logger.trace(PREFIX_STEP_START + " INIT of new machine {}", sm.getClass().getSimpleName());
		long time = System.currentTimeMillis();
		
		try {
			isCurrentlyExecutingATransition = true;
			lastStartOfTransition = System.currentTimeMillis();
			result = sm.fireInitial();
			isCurrentlyExecutingATransition = false;
		} catch (Throwable t) {
			handleException(t);
			t.printStackTrace();
			logger.error("APPLICATION ERROR IN INITIAL TRANSITION. stm type: " + getSTMString(sm), t);
			logger.trace(PREFIX_STEP_END + "--> Application error.");
			Block.notifyListeners("Application Error During Fire Initial",getStackTrace(t));
			register.applicationErrorDetected();
		}
		
		switch (result) {
		
		case IStateMachine.CONSUME_SIGNAL:
			smQueues.add(sm);
			logger.trace(PREFIX_STEP_END + "--> Transition executed within {} ms.", System.currentTimeMillis() - time);
			break;

		case IStateMachine.TERMINATE_MACHINE:
			deregisterStateMachine(sm);
			logger.trace(PREFIX_STEP_END + "--> Transition executed within {} ms, machine terminated.", System.currentTimeMillis() - time);
			break;
		
		case IStateMachine.BLOCKING_CHOICE:
			logger.error(PREFIX_STEP_END + "--> Blocking decision in machine.");
			break;
		
		default:
			break;
		}
		
		debugStep();
	}
	
	private void executeSmTransition(IStateMachine sm, Message msg) {
		int result = IStateMachine.EXCEPTION;
		logger.trace(PREFIX_STEP_START + " EVENT " + msg.signalID + " for machine " + sm.getClass().getSimpleName());
		long time = System.currentTimeMillis();

		try {
			isCurrentlyExecutingATransition = true;
			lastStartOfTransition = System.currentTimeMillis();
			result = sm.fire(msg.sender, msg.receiver, msg.signalID, msg.content);
			isCurrentlyExecutingATransition = false;
		} catch (Throwable t) {
			handleException(t);
			applicationErrorDuringFire(sm, "" + sm.getClass().getSimpleName(), msg.signalID, t);
			register.applicationErrorDetected();
		}

		switch (result) {

		case IStateMachine.CONSUME_SIGNAL:
			logger.trace(PREFIX_STEP_END + "--> Transition executed within {} ms.", System.currentTimeMillis() - time);
			break;

		case IStateMachine.TERMINATE_MACHINE:
			assert smQueues.contains(sm);
			if (index > 0) {
				index--;
			}
			deregisterStateMachine(sm);
			logger.trace(PREFIX_STEP_END + "--> Transition executed within {} ms, machine terminated.", System.currentTimeMillis() - time);
			break;

		case IStateMachine.DISCARD_SIGNAL:
			logger.warn(PREFIX_STEP_END + "--> Discarded event: {} State Machine: {}", msg.signalID, sm.getClass().getSimpleName());
			break;

		case IStateMachine.BLOCKING_CHOICE:
			logger.error(PREFIX_STEP_END + "--> Blocking decision in machine.");
			break;

		case IStateMachine.TRIGGER_UNKNOWN:
			unknownTrigger(msg.signalID, msg.sender, msg.receiver, sm);
			break;

		default:
			// an exception happened. signal is marked as consumed
			// to not try again
			break;
		}

		debugStep();
	}
	
	private void handleException(Throwable t) {
		if (forwardAssertionErrors) {
			if (t instanceof AssertionError) {
				throw (AssertionError) t;
			} else {
				throw new RuntimeException(t);
			}
		}
	}
	
	private void debugStep() {
		if (stepping) {
			debugger.step();
		}
	}

	public String createFreshSessionID() {
		return register.getFreshSessionId();
	}

	public void sendToSession(IStateMachine sender, String sessionID, String signalID, Object data) {
		sendToSession(sender, sessionID, signalID, data, null);
	}

	public void sendToSession(IStateMachine sender, String sessionID, String signalID, Object data, String alias) {
		register.deliverInternalMessage(sender, sessionID, signalID, data, alias);
	}

	public void sendToSessions(IStateMachine sender, Collection<String> sessionIDs, String signalID, Object data) {
		Iterator<String> i = sessionIDs.iterator();
		while(i.hasNext()) {
			register.deliverInternalMessage(sender, i.next(), signalID, data, null);
		}
	}

	public void stopTimer(Timer timer) {
		timers.stopTimer(timer);
	}

	public void startOrRestartTimer(Timer timer, int delay) {
		timer.delay = delay;
		startOrRestartTimer(timer);
	}
	
	public void startOrRestartTimer(Timer timer) {
		if (timers.containsTimer(timer)) {
			logger.trace("Restarting timer with ID: " + timer.id + " and delay " + timer.delay);
		} else {
			logger.trace("Starting timer with ID: " + timer.id + " and delay " + timer.delay);
		}
		timers.startOrRestartTimer(timer);
	}
	
	public void startBreak(Break b) {
		breaks.startBreak(b);
		logger.trace("Starting break with ID: " + b.id);
	}

	public void stopBreak(Break b) {
		breaks.stopBreak(b);
	}

	public void restartBreak(Break b) {
		breaks.restartBreak(b);
	}

	public void sendToParent(IStateMachine sender, String signalID, Object data) {
		register.deliverInternalMessage(sender, sender.parentSessionID, signalID, data, null);
	}

	public void sendToParent(IStateMachine sender, String signalID) {
		register.deliverInternalMessage(sender, sender.parentSessionID, signalID, null, null);
	}
	
	public void sendToMachine(IStateMachine queue, String sender, String signalID, Object data) {
		queue.getInputQueue().addMessage(sender, null, signalID, data);
		register.register.getDefaultScheduler().awakenScheduler();
	}
	
	public void flushEventQueue(String sessionID, String signalID) {
		IStateMachine sm = null;
		synchronized (smQueues) {
			for (IStateMachine sm2 : smQueues) {
				if (sm2.getSessionId().equals(sessionID)) {
					sm = sm2;
				}
			}
		}
		if (sm != null) {
			logger.trace("Flush event queue for signal " + signalID + " in state machine id " + sm.getClass().getSimpleName());
			sm.getInputQueue().removeInternalSignal(signalID);
		}
	}

	public void destroy() {
		keepRunning = false;
	}

	private boolean allInputQueuesAreEmpty() {
		synchronized (smQueues) {
			for (IStateMachine sm : smQueues) {
				if (!sm.getInputQueue().isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}

	void awakenScheduler() {
		synchronized (this) {
			notifyAll();
		}
	}

	public static final int MAXIMUM_TRANSITION_TIME = 1000;

	public void checkBlocking() {
		if(isCurrentlyExecutingATransition) {
			long time = System.currentTimeMillis() - lastStartOfTransition;
			if(time > MAXIMUM_TRANSITION_TIME) {
				StringBuilder b = new StringBuilder();
				for(StackTraceElement s: thread.getStackTrace()) {
					b.append(s.toString() + "\n");
				}
				logger.warn("The scheduler is blocked by a transition since at least {} ms. Check if there are any blocking methods. {}", MAXIMUM_TRANSITION_TIME, b.toString());
			}
		}
	}

	private class TimerQueue {
		private final List<Timer> timers = Collections.synchronizedList(new LinkedList<Timer>());

		private void removeStateMachine(IStateMachine sm) {
			synchronized (timers) {
				Set<Timer> toRemove = new HashSet<Timer>();
				for (Timer t : timers) {
					if (t.stateMachine == sm) {
						toRemove.add(t);
					}
				}
				timers.removeAll(toRemove);
			}
		}

		private Timer consume() {
			return timers.remove(0);
		}

		private boolean hasExpiredTimer() {
			return (!timers.isEmpty()) && timers.get(0).time <= System.currentTimeMillis();
		}

		private boolean isEmpty() {
			return timers.isEmpty();
		}

		private long getTimeToNextExpiration() {
			long duration = timers.get(0).time - System.currentTimeMillis();
			return duration>0 ? duration : 0;
		}

		private void stopTimer(Timer t) {
			timers.remove(t);
		}
	
		private boolean containsTimer(Timer t) {
			return timers.contains(t);
		}

		private void startOrRestartTimer(Timer t) {
			if (timers.contains(t)) {
				timers.remove(t);
				t.reset();
			} else {
				t.time = System.currentTimeMillis() + t.delay;
			}
			insertTimerSortedByExpirationTime(t);
		}

		private void insertTimerSortedByExpirationTime(Timer t) {
			synchronized (timers) {
				int i = 0;
				for (Timer t2 : timers) {
					if (t2.time <= t.time) {
						i++;
					} else {
						break;
					}
				}
				timers.add(i, t);
			}
		}
	}
	
	private class BreakQueue {
		private final List<Break> breaks = Collections.synchronizedList(new LinkedList<Break>());

		private void removeStateMachine(IStateMachine sm) {
			synchronized (breaks) {
				Set<Break> toRemove = new HashSet<Break>();
				for (Break b : breaks) {
					if (b.stateMachine == sm) {
						toRemove.add(b);
					}
				}
				breaks.removeAll(toRemove);
			}
		}

		private Break consume() {
			return breaks.remove(0);
		}

		private boolean isEmpty() {
			return breaks.isEmpty();
		}

		private void startBreak(Break b) {
			breaks.add(b);
		}

		private void stopBreak(Break b) {
			breaks.remove(b);
		}

		private void restartBreak(Break b) {
			if(breaks.contains(b)) {
				breaks.remove(b);
			}
			breaks.add(b);
		}
	}

	static class Message {
		public final String signalID;
		public final String sender;
		public final String receiver;
		public final Object content;

		Message(String sender, String receiver, String signalID, Object content) {
			this.sender = sender;
			this.receiver = receiver;
			this.content = content;
			this.signalID = signalID;
		}

		public String toString() {
			String n = System.getProperty("line.separator");
			StringBuffer s = new StringBuffer();
			s.append(n + "SignalID: " + signalID);
			s.append(n + "Sender: " + sender);
			s.append(n + "Receiver: " + receiver);
			s.append(n + "Content :" + content);
			return s.toString();
		}
	}

	static class InputQueue {
		private final List<Message> messages = Collections.synchronizedList(new LinkedList<Message>());
		private IStateMachine machine;

		InputQueue(IStateMachine machine) {
			this.machine = machine;
		}

		IStateMachine getStateMachine() {
			return machine;
		}

		private boolean isEmpty() {
			return messages.isEmpty();
		}

		void addMessage(String sender, String sessionID, String signalID, Object data) {
			addMessage(new Message(sender, sessionID, signalID, data));
		}

		void addMessage(Message msg) {
			messages.add(msg);
		}

		private Message removeFirstMessage() {
			return messages.remove(0);
		}

		private void removeInternalSignal(String signalId) {
			synchronized (messages) {
				Set<Message> toRemove = new HashSet<Message>();
				for (Message m : messages) {
					if (m.signalID.equals(signalId)) {
						toRemove.add(m);
					}
				}
				for (Message m : toRemove) {
					messages.remove(m);
				}
			}
		}
	}

	private void applicationErrorDuringFire(IStateMachine stateMachine, String state, String signalID, Throwable t) {
		String message = " ! APPLICATION ERROR. " + "\n"
				+ "                     stm: " + getSTMString(stateMachine) + "\n"
				+ "                   state: " + state + "\n"
				+ "                signalID: " + signalID + "\n"; 
		logger.error(message, t);
		Block.notifyListeners("Application Error During Fire",getStackTrace(t));
	}

	private void applicationErrorDuringFireTimer(IStateMachine stateMachine, String state, String timerID, Throwable t) {
		String message = " ! APPLICATION ERROR.      stm: " + getSTMString(stateMachine) + "\n"
				+ "                         state: " + state + "\n"
				+ "                       timerID: " + timerID + "\n"; 
		logger.error(message, t);
		Block.notifyListeners("Application Error During Fire Timer", getStackTrace(t));
	}

	private String getSTMString(IStateMachine stateMachine) {
		if(stateMachine.sessionAlias!=null) {
			return stateMachine.getClass().getSimpleName() 
					+ " " + stateMachine.sessionID 
					+ " " + " alias: " + stateMachine.sessionAlias;
		} else if (stateMachine.sessionID!=null) {
			return stateMachine.getClass().getSimpleName() + " " + stateMachine.sessionID;
		} else {
			return stateMachine.getClass().getSimpleName();
		}
	}
	
	private void unknownTrigger(String signalName, String sender, String receiver, IStateMachine stateMachine) {
		String message = " ! UNKNOWN TRIGGER.   id: " + signalName + "\n"
					   + "                  sender: " + sender + "\n"
					   + "                receiver: " + receiver + "\n"
                       + "                 machine: " + getSTMString(stateMachine) + "\n"
					   + "                    help: http://reference.bitreactive.com/reference/runtime-unknown-trigger.html";
		logger.error(message);
		Block.notifyListeners("Unknown Trigger", message);
	}
	
	private String getStackTrace(Throwable t) {
		if(t==null) return "";
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

}