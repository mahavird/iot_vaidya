package no.ntnu.item.arctis.runtime;

public interface IRegister {
	
	public static final String MAIN_SESSION_ID = "000";

	public abstract IStateMachine getDefaultMachine();

	public abstract void setDefaultMachine(IStateMachine defaultMachine);

	public abstract Scheduler getDefaultScheduler();

	public abstract void setDefaultScheduler(Scheduler defaultScheduler);

	public abstract IStateMachine getStateMachineBySessionID(String sessionID);

	public abstract void registerStateMachine(String sessionID, IStateMachine stm);

	public abstract void removeStateMachine(IStateMachine stm);

}