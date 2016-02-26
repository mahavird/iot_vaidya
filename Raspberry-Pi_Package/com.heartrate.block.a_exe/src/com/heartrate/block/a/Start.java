package com.heartrate.block.a;

import no.ntnu.item.arctis.runtime.Runtime;

public class Start {

	public static void main(String[] args) throws Exception {
		System.out.println("Starting runtime...");
		Runtime runtime = MyRuntime.create();
		runtime.start();
		runtime.setTerminationListener(new Runtime.IRuntimeTerminationListener() {
			@Override
			public void terminationReqFromApp() {
			}

			@Override
			public void runtimeStopped() {
				System.exit(0);
			}
		});
	}

}
