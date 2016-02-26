package no.ntnu.item.arctis.runtime;

public class SingletonData {
	
	private String sessionID; 
	private String instance;
	
	public SingletonData() {
	}

	public SingletonData(Object data) {
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	private Object data;
	
	public String getSessionID() {
		return sessionID;
	}
	
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	public String getInstance() {
		return instance;
	}
	
	public void setInstance(String instance) {
		this.instance = instance;
	}

}
