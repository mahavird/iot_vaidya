package no.ntnu.item.arctis.runtime;

import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides access to the functions provided by the runtime added during the
 * compilation process. 
 * 
 * Note that class <code>Block</code> or any subclasses of it provide the same functionality in a
 * more comfortable way. We therefore recommend that any building block should extend class <code>Block</code> 
 * or any of its subclasses instead of accessing <code>AbstractRuntime</code> directly.
 * 
 * @author kraemer
 *
 */
public abstract class AbstractRuntime implements IRuntime {
	
	private final Logger logger = LoggerFactory.getLogger("Runtime");
	
	public abstract boolean isSessionActive(String sessionID);
	
	final Hashtable context = new Hashtable();
	
	public boolean containsProperty(String key) {
		return context.containsKey(key);
	}
	
	public Object getProperty(String key) {
		return context.get(key);
	}
	
	public void setProperty(String key, Object value) {
		context.put(key, value);
	}
    
	/**
	 * @deprecated use the setProperty() method of Block.
	 * @param id
	 * @param contextObject
	 */
    public void addContext(String id, Object contextObject) {
    	if(id==null) {
    		logger.warn("Key cannot be null when adding a context object.");
    		return;
    	}
    	if(contextObject==null) {
    		logger.warn("Tried to add context object with value null for key {}.", id);
    		return;
    	}
        context.put(id, contextObject);
    }

    /**
	 * @deprecated use the getProperty() method of Block.
	 */
    public Object getContext(String id) {
        return context.get(id);
    }
    
    public abstract boolean isRunning();

		
	/**
	 * Sends a signal into the runtime system. This signal must be 
	 * received by the UML model of the same building block. 
	 * The provided signal ID must match that of the UML signal.
	 * The provided data type must match the one declared for the UML signal.
	 * 
	 * @param blockID - the block ID provided via <code>IArctisBuildingBlock</cock>.
	 * @param signalName - the name of the signal.
	 * @param data - data to be transported by the signal. 
	 */
	public abstract void sendToBlock(String sessionID, String blockID, String signalName, Object data);

	/**
	 * Sends a signal into the runtime system. This signal must be 
	 * received by the UML model of the same building block. 
	 * The provided signal ID must match that of the UML signal.
	 * 
	 * @param blockID - the block ID provided via <code>IArctisBuildingBlock</cock>.
	 * @param signalName - the name of the signal.  
	 */
	public abstract void sendToBlock(String sessionID, String blockID, String signalName);

}