package no.ntnu.item.arctis.runtime;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.ntnu.item.arctis.runtime.Scheduler.InputQueue;

public abstract class IStateMachine {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final int CONSUME_SIGNAL = 1;
	public static final int DISCARD_SIGNAL = 2;
	public static final int EXCEPTION = 3;
	public static final int TRIGGER_UNKNOWN = 4;
	public static final int TERMINATE_MACHINE = 5;
	public static final int BLOCKING_CHOICE = 6;
	
	public static final int MULTIPLICITY_NORMAL = 0;
	public static final int MULTIPLICITY_SINGLETON = 1;
	public static final int MULTIPLICITY_SINGLE_SESSION = 2;
	public static final int MULTIPLICITY_MULTI_SESSION = 3;
	
	protected Scheduler scheduler;
	
	protected void registerAlias(String alias, String sessionID) {
	}
	
	public int getSessionCount() {
		return 0;
	}
	

	public abstract int fireInitial();

	public abstract int fire(Object sender, Object receiver, String signalId, Object data);

	public abstract int fireTimer(String timerId);

	private InputQueue queue;
	
	public IStateMachine(Scheduler scheduler) {
		this.scheduler = scheduler;
		this.queue = new InputQueue(this);
		this.sessionID = null;
		this.parentSessionID = null;
		this.sessionPath = "/";
		this.unitPrefix = "b000" + "://";
	}
	
	public IStateMachine(Scheduler scheduler, String unitBlockID, String sessionID, String parentSessionID, String parentSessionPath) {
		this.scheduler = scheduler;
		this.queue = new InputQueue(this);
		this.sessionID = sessionID;
		this.parentSessionID = parentSessionID;
		this.sessionPath = parentSessionPath + "/" + sessionID;
		this.unitPrefix = unitBlockID + ":" + this.sessionPath + "/";
	}
	
	InputQueue getInputQueue() {
		return queue;
	}
	
	protected String parentSessionID;
	protected String sessionID;
	protected String sessionAlias;
	protected String sessionPath;
	protected String unitPrefix;
	
	protected int _state;
	
	String getSessionId() {
		return sessionID;
	}
	
	protected void logStep(String step) {
		System.err.println(unitPrefix + "#" + step);
	}
	
	public int getMultiplicityPattern() {
		return MULTIPLICITY_NORMAL;
	}
	
	protected Set<String> childSessions = new HashSet<String>();
	
	public static class Timer {
		String id;
		int delay;
		long time;
		IStateMachine stateMachine;

		public Timer(int delay, String id, IStateMachine stateMachine) {
			this.delay = delay;
			this.time = System.currentTimeMillis() + delay;
			this.id = id;
			this.stateMachine = stateMachine;
		}
		
		public Timer(String id, IStateMachine stateMachine) {
			this.time = System.currentTimeMillis() + delay;
			this.id = id;
			this.stateMachine = stateMachine;
		}

		void reset() {
			this.time = System.currentTimeMillis() + delay;
		}
	}
	
	public static class Break {
		String id;
		IStateMachine stateMachine;

		public Break(String id, IStateMachine stateMachine) {
			this.id = id;
			this.stateMachine = stateMachine;
		}

	}
	
}