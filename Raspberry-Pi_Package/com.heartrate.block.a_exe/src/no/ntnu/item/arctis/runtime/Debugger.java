package no.ntnu.item.arctis.runtime;

import java.util.Scanner;

public class Debugger {
	
	private Scanner inputScanner = new Scanner(System.in);
	private boolean isActive = true;
	
	private Register register;
	
	Debugger(Register register) {
		this.register = register;
	}
	
	void step() {
		if(!isActive) return;
		System.out.println("--> press Enter to run next step, or enter 'help' for more info.");
		String line = null;
		try {
			line = inputScanner.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(line==null || line.trim().isEmpty()) {
			// execute the next transition
			return;
		} else if(line.startsWith("help")) {
			printHelp();
			step();
		} else if(line.startsWith("status")) {
			printStatus();
			step();
		} else if (line.startsWith("resume")) {
			this.isActive = false;
		} else if (line.startsWith("exit")) {
			System.exit(0);
		}
	}
	
	private void printHelp() {
		System.out.println("The following commands are available:");
		System.out.println(" help     show all commands");
		System.out.println(" status   show the status of the system");
		System.out.println(" resume   stop debugging and let the system run");
		System.out.println(" exit     abort all execution and exit");
	}
	
	private void printStatus() {
		System.out.println("A list of all sessions:");
		System.out.println(register.printSessionIDToStateMachine());
	}

}
