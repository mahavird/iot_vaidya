package com.bitreactive.iotvaidyaadcevent.start.adcibmevent;

import no.ntnu.item.arctis.runtime.IStateMachine;
import no.ntnu.item.arctis.runtime.Runtime;
import no.ntnu.item.arctis.runtime.Register;
import no.ntnu.item.arctis.runtime.IRegister;
import no.ntnu.item.arctis.runtime.Scheduler;

public class MyRuntime extends Runtime {

	private MyRuntime(IRegister register) {
		super(register);
	}

    public static Runtime create() {
        MyRuntime runtime = new MyRuntime(new Register());
        return runtime;
    }

	protected IStateMachine createDefaultStateMachine(Scheduler scheduler) {
		return new com.bitreactive.iotvaidyaadcevent.start.adcibmevent.component.ComponentSM(scheduler);
	}

	protected IStateMachine createSession(String signalID, Scheduler scheduler, String sessionID, String parentSessionID, String parentSessionPath) {
        if("singleton_1698104509".equals(sessionID)) return new com.bitreactive.iotvaidyaadcevent.start.adcibmevent.component.DeviceSingletonSM(scheduler, sessionID, parentSessionID, parentSessionPath);

        return null;
    }

	protected boolean createsSession(String signalID) {

        return false;
    }


	public void applicationErrorDetected() {
		System.exit(1);
	}
}
