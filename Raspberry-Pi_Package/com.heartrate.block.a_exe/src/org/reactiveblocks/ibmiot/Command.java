package org.reactiveblocks.ibmiot;

public class Command {
	
	private String command;
	private byte[] payload;
	
	public Command(String command, byte[] payload) {
		this.command = command;
		this.payload = payload;
	}
	
	public String getCommand() {
		return command;
	}
	public byte[] getPayload() {
		return payload;
	}		
}