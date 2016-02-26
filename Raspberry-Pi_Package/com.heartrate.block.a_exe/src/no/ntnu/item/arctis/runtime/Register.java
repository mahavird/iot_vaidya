package no.ntnu.item.arctis.runtime;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Register implements IRegister {

	private Map<String, IStateMachine> sessionIDToMachine = new HashMap<String, IStateMachine>();

	public IStateMachine getDefaultMachine() {
		return defaultMachine;
	}

	public void setDefaultMachine(IStateMachine defaultMachine) {
		assert this.defaultMachine == null;
		this.defaultMachine = defaultMachine;
	}

	public Scheduler getDefaultScheduler() {
		return defaultScheduler;
	}

	public void setDefaultScheduler(Scheduler defaultScheduler) {
		assert this.defaultScheduler == null;
		this.defaultScheduler = defaultScheduler;
	}

	private IStateMachine defaultMachine;
	private Scheduler defaultScheduler;

	public IStateMachine getStateMachineBySessionID(String sessionID) {
		return sessionIDToMachine.get(sessionID);
	}

	public void registerStateMachine(String sessionID, IStateMachine stm) {
		assert stm != null;
		assert sessionID != null;
		sessionIDToMachine.put(sessionID, stm);
	}
	
	public String printSessionIDToStateMachine() {
		StringBuffer b = new StringBuffer();
		for(Entry<String,IStateMachine> e: sessionIDToMachine.entrySet()) {
			b.append(e.getKey() + ": " + e.getValue().sessionID + " " + e.getValue().getClass().getSimpleName() + "\n");
		}
		return b.toString();
	}

	public void removeStateMachine(IStateMachine stm) {
		assert stm.sessionID!=null;
		sessionIDToMachine.remove(stm.sessionID);
	}

}
