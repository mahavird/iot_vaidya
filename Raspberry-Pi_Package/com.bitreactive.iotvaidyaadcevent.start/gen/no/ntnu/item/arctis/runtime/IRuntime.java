package no.ntnu.item.arctis.runtime;

public interface IRuntime {
	

	public void sendToBlock(String sessionID, String blockID, String eventID);

	public void sendToBlock(String sessionID, String blockID, String eventID, Object data);

	public boolean containsProperty(String key);
	
	public Object getProperty(String key);
	
	public void setProperty(String key, Object value);
	
}
