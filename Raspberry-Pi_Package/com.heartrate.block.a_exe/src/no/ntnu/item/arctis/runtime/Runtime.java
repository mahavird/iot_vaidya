package no.ntnu.item.arctis.runtime;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Runtime extends AbstractRuntime {

	IRegister register;
	private Thread thread;

	private static final Logger logger = LoggerFactory.getLogger(Runtime.class);
	
	private IRuntimeTerminationListener terminationListener;
	
	public void applicationErrorDetected() {
		// per default, nothing is done, system continues
	}

	public static interface IRuntimeTerminationListener {
		public void terminationReqFromApp();
		public void runtimeStopped();
	}

	public void setTerminationListener(IRuntimeTerminationListener terminationListener) {
		this.terminationListener = terminationListener;
	}
	
	public enum TerminationRequester {
		NONE,
		FRAMEWORK,
		APP;
	}
	
	private TerminationRequester terminationRequester = TerminationRequester.NONE;
	
	public void setTerminationRequester(TerminationRequester t) {
		this.terminationRequester = t;
	}
	
	public TerminationRequester getTerminationRequester() {
		return terminationRequester;
	}

	public Runtime(IRegister register) {
		this.register = register;

		Scheduler defaultScheduler = new Scheduler(this);
		IStateMachine defaultMachine = createDefaultStateMachine(defaultScheduler);
		defaultMachine.sessionID = IRegister.MAIN_SESSION_ID;
		this.register.registerStateMachine(IRegister.MAIN_SESSION_ID, defaultMachine);
		defaultScheduler.add(defaultMachine);

		register.setDefaultMachine(defaultMachine);
		register.setDefaultScheduler(defaultScheduler);
	}
	
	@Override
	public boolean isSessionActive(String sessionID) {
		return register.getStateMachineBySessionID(sessionID)!=null;
	}
	
	boolean isRunning = false;
	
	@Override
	public boolean isRunning() {
		return isRunning;
	}

	public void start() {
		thread = new Thread(register.getDefaultScheduler());
		isRunning = true;
		register.getDefaultScheduler().start(thread);
	}
	
	/**
	 * Start the runtime. This method will block until the runtime terminates on its own,
	 * and the scheduler will execute in the same thread as this method is called from.
	 * Starting with this method is intended for instance for testing, so that test methods
	 * do not terminate before the system is finished.
	 */
	public void startBlocking() {
		isRunning = true;
		register.getDefaultScheduler().run();
	}
	
	/**
	 * Start the runtime, for the execution as a JUnit test.
	 * The call is blocking, and will return after the system terminated.
	 * The scheduler is set so it will not catch any assertion errors.
	 */
	public void startForJUnit() {
		register.getDefaultScheduler().setForwardAssertionErrors(true);
		startBlocking();
	}

	public void stop() {
		isRunning = false;
		if (terminationRequester == TerminationRequester.NONE) {
			// Should never happen!
			logger.error("Runtime.stop() is called when no termination request is received!");
		}
		register.getDefaultScheduler().destroy();
		
		if (terminationListener != null) {
			terminationListener.runtimeStopped();
			terminationListener = null;
		}
	}

	public void setContext(String key, Object value) {
		context.put(key, value);
	}

	void deliverInternalMessage(IStateMachine senderMachine, String sessionID, String signalID, Object data, String alias) {
		String sender = senderMachine.getSessionId();
		if (sessionID == null) {
			logger.error("No sessionID provided for routing. Dropping event {}.", signalID);
		} else {
			IStateMachine queue = register.getStateMachineBySessionID(sessionID);
			if (queue != null) {
				queue.getInputQueue().addMessage(sender, sessionID, signalID, data);
				register.getDefaultScheduler().awakenScheduler();
			} else {
				IStateMachine stm = createSession(signalID, register.getDefaultScheduler(), sessionID, senderMachine.sessionID, senderMachine.sessionPath);
				if(stm==null) {
					logger.error("No state machine was found for event {}.", signalID);
				} else {
					startStateMachine(stm);
					if(alias!=null) {
						stm.sessionAlias = alias;
						stm.registerAlias(alias, sessionID);
					}
					logger.debug("Session of type " + stm.getClass().getSimpleName() + " created. Now there are " +
							stm.getSessionCount() + " instances of that type. SessionID: " +
							stm.sessionID + " Alias: " + stm.sessionAlias);
					register.registerStateMachine(sessionID, stm);
					register.getDefaultScheduler().add(stm);
					stm.getInputQueue().addMessage(sender, sessionID, signalID, data);
					register.getDefaultScheduler().awakenScheduler();
				}
			}
		}
	}

	
	public void terminateStateMachine(IStateMachine stm) {
		if(stm.childSessions.isEmpty()) {
			// this is how a perfect application should be.
			Set<String> sessionsToRemove = new HashSet<String>();
			sessionsToRemove.add(stm.sessionID);
			removeChildSessionsAtAllParents(stm.parentSessionID, sessionsToRemove);
		} else {
			// this constitutes a flaw, we warn, and terminate all child machines.
			logger.warn("Sessions terminated by surrounding session. State machine {} has still {} nested sessions.", stm.getClass().getSimpleName(), stm.childSessions.size());

			// find all child session IDs
			Set<String> childSessionIDs = new HashSet<String>();
			childSessionIDs.add(stm.sessionID);
			collectAllChildSessions(childSessionIDs, stm);
			
			// we terminate each child session
			for(String child: childSessionIDs) {
				childSessionIDs.add(child);
				IStateMachine c = register.getStateMachineBySessionID(child);
				if(c!=null) {
					register.removeStateMachine(c);
					logger.warn("Nested session terminated Parent: " + stm.getClass().getSimpleName() +
							", " + stm.sessionID + " Child: " + c.getClass().getSimpleName() +
							", " + c.sessionID + ", " + c.getSessionCount());
				}
			}
			
			removeChildSessionsAtAllParents(stm.parentSessionID, childSessionIDs);
		}
		
		// The default state machine terminated, so the runtime will terminate
		// we assume that one runtime executes exactly one default machine.
		// session should be terminated, but this is the task of the application
		if (stm == register.getDefaultMachine()) {
			if (terminationRequester == TerminationRequester.FRAMEWORK) {
				// Termination was requested by the OSGi framework and TERMINATE signal has been processed.
				// Nothing to do
			} else if (terminationRequester == TerminationRequester.APP) {
				// Multiple terminations were requested by the application. Impossible due to RB semantics.
				logger.error("Multiple terminations were requested by the application.");
			} else { // TerminationRequester.NONE;
				// Termination is requested by the application.
				logger.debug("Termination is requested by the application.");
				
				terminationRequester = TerminationRequester.APP;
				if (terminationListener != null) {
					terminationListener.terminationReqFromApp();
				}
			}
			
			register.removeStateMachine(stm);
			logger.info("Shutting down runtime system. Good bye!");
			// TODO stop all remaining singletons
			stop();
		} else {
			register.removeStateMachine(stm);
			logger.debug("Session of type " + stm.getClass().getSimpleName() +
					" terminated. SessionID: " + stm.sessionID + ", remaining instances: " + stm.getSessionCount());
		}
	}
	
	/**
	 * Moves down and collects all child sessions of this machine.
	 * 
	 * @param childSessionIDs
	 * @param stm
	 */
	private void collectAllChildSessions(Set<String> childSessionIDs, IStateMachine stm) {
		for(String child: stm.childSessions) {
			childSessionIDs.add(child);
			IStateMachine c = register.getStateMachineBySessionID(child);
			if(c!=null) {
				collectAllChildSessions(childSessionIDs, c);
			}
		}
	}
	
	/**
	 * Crawls up the hierarchy of sessions and removes the given session IDs from all of the state machines.
	 * @param parentSessionID
	 * @param sessionsToRemove
	 */
	private void removeChildSessionsAtAllParents(String parentSessionID, Set<String> sessionsToRemove) {
		boolean topReached = false;
		String currentParentSessionID = parentSessionID;
		while(!topReached) {
			if(currentParentSessionID==null) {
				IStateMachine p = register.getDefaultMachine();
				p.childSessions.removeAll(sessionsToRemove);
				topReached = true;
			} else {
				IStateMachine p = register.getStateMachineBySessionID(currentParentSessionID);
				if(p!=null) {
					p.childSessions.removeAll(sessionsToRemove);
					currentParentSessionID = p.parentSessionID;
				} else {
					logger.error("State machine was not found " + currentParentSessionID);
					topReached = true;
				}
			}
		}
	}
	
	/**
	 * Add the session ID of stm to each of its parents, until the topmost state machine is reached.
	 * @param stm
	 */
	public void startStateMachine(IStateMachine stm) {
		if(stm.getMultiplicityPattern()!=IStateMachine.MULTIPLICITY_SINGLETON) {
			boolean topReached = false;
			String currentParentSessionID = stm.parentSessionID;
			while(!topReached) {
				if(currentParentSessionID==null) {
					IStateMachine p = register.getDefaultMachine();
					p.childSessions.add(stm.sessionID);
					topReached = true;
				} else {
					IStateMachine p = register.getStateMachineBySessionID(currentParentSessionID);
					p.childSessions.add(stm.sessionID);
					currentParentSessionID = p.parentSessionID;
				}
			}
		}
	}

	private final Random randomSessionIdGenerator = new Random();

	public String getFreshSessionId() {
		return "" + Math.abs(randomSessionIdGenerator.nextLong());
	}

	protected abstract boolean createsSession(String signalID);

	protected abstract IStateMachine createSession(String signalID, Scheduler scheduler, String sessionID, String parentSessionID, String parentSessionPath);

	protected abstract IStateMachine createDefaultStateMachine(Scheduler scheduler);


	@Override
	public void sendToBlock(String sessionID, String blockID, String signalID, Object data) {
		IStateMachine m;
		if(sessionID==null) {
			m = register.getDefaultMachine();
			if(m==null) {
				logger.error("Default state machine is null, when trying to dispatch signal with ID {} ", signalID);
				logger.error(((Register)register).printSessionIDToStateMachine());
				return;
			}
		} else {
			m = register.getStateMachineBySessionID(sessionID);
			if(m==null) {
				logger.error("Could not find a state machine for the session ID {} while sending a signal with ID {}", sessionID, signalID);
				logger.error(((Register)register).printSessionIDToStateMachine());
				return;
			}
		}
		if(!isRunning()) {
			logger.error("Event {} was sent into a block, but the runtime is already terminated.", signalID);
		}
		m.getInputQueue().addMessage(null, null, signalID, data);
		register.getDefaultScheduler().awakenScheduler();
		register.getDefaultScheduler().checkBlocking();
	}

	@Override
	public void sendToBlock(String sessionID, String blockID, String signalId) {
		sendToBlock(sessionID, blockID, signalId, null);
	}

}