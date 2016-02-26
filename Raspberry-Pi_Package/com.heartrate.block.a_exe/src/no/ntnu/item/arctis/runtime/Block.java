package no.ntnu.item.arctis.runtime;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java classes for building block partitions may extend this class to obtain
 * easier access to runtime features.
 * 
 * This class manages the ID of a block by implementing IArctisBuildingBlock.
 * 
 */
public abstract class Block implements IArctisBuildingBlock {
	
	public static final String DEVICE_ID = "DEVICE_ID";

	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * The ID of this block. This value is set when a state machine is started,
	 * and must not to be written by application code.
	 */
	protected String blockID;
	protected String sessionID;
	protected IRuntime runtime;

	/**
	 * Used by the runtime system to sets the ID of this block when a state
	 * machine is started, and must not to be used by application code.
	 */
	@Override
	public void setBlockID(String blockID, String sessionID) {
		this.blockID = blockID;
		this.sessionID = sessionID;
	}
	
	@Override
	public void setRuntime(IRuntime runtime) {
		this.runtime = runtime;
	}

	/**
	 * Send an internal notification to this block with the given signal name.
	 * 
	 * Note that the suffix with the blockID for the signal name is optional, and
	 * will be appended if it is not present. If this is not desired, use the
	 * method provided by AbstractRuntime.
	 * 
	 * @param signalName
	 */
	public final void sendToBlock(String signalName) {
		if (signalName.endsWith(blockID) || signalName.contains(".")) {
			runtime.sendToBlock(sessionID, blockID, signalName);
		} else {
			runtime.sendToBlock(sessionID, blockID, signalName + "_" + blockID);
		}
	}

	/**
	 * Send an internal notification to this block with the given signal name.
	 * 
	 * Note that the suffix with the blockID for the signal name is optional, and
	 * will be appended if it is not present. If this is not desired, use the
	 * method provided by AbstractRuntime.
	 * 
	 * @param signalName
	 * @param data
	 */
	public final void sendToBlock(String signalName, Object data) {
		if (signalName.endsWith(blockID) || signalName.contains(".")) {
			runtime.sendToBlock(sessionID, blockID, signalName, data);
		} else {
			runtime.sendToBlock(sessionID, blockID, signalName + "_" + blockID, data);
		}
	}

	public final void logError(String message) {
		logger.error(message);
	}
	public final void logWarn(String message) {
		logger.warn(message);
	}
	public final void logInfo(String message) {
		logger.info(message);
	}
	public final void logDebug(String message) {
		logger.debug(message);
	}
	public final void logTrace(String message) {
		logger.trace(message);
	}
	
	private final static Logger staticLogger = LoggerFactory.getLogger(Block.class);
	/**
	 * @deprecated Use the logger object directly, or the logging methods with a logging level.
	 * For static access to the logger, create your own logger object.
	 */
	@Deprecated
	public static final void log(String message) {
		staticLogger.info(message);
	}

	/**
	 * Logs the given exception depending on the execution platform.
	 * 
	 * @param message
	 * @param t
	 */
	public final void log(String message, Throwable t) {
		logger.error(message, t);
	}
	
	public final void logException(String message, Throwable t) {
		logger.error(message, t);
	}
	
	public static interface LogListener {
		public void log(String subject, String message);
	}
	
	private static Set<LogListener> listeners = new HashSet<LogListener>();
	
	public static void notifyListeners(String subject, String message) {
		for(LogListener l: listeners) {
			l.log(subject, message);
		}
	}
	
	public static void addListener(LogListener l) {
		listeners.add(l);
	}
	
	public static void removeListener(LogListener l) {
		listeners.remove(l);
	}
	
	/**
	 * Operations within building block must never block. Long-running operations
	 * should be executed within an extra Runnable, executed via this method.
	 * 
	 * Use internal events to synchronize the execution of the long-running operation back
	 * to the logic of the building block.
	 * 
	 * This method creates a new thread. It is recommended to use this method instead of
	 * creating own threads, since we may add logging and monitoring to this method in the future.
	 * 
	 * 
	 * @param r the Runnable containing the long-running operation to be executed
	 */
	public void runAsync(Runnable r) {
		new Thread(r).start();
	}
	
	public Object getProperty(String key) {
		if(!runtime.containsProperty(key)) {
			logger.warn("Property for key {} does not exist.", key);
			return null;
		}
		return runtime.getProperty(key);
	}
	
	public boolean hasProperty(String key) {
		return runtime.containsProperty(key);
	}
	
	public void setProperty(String key, Object value) {
		runtime.setProperty(key, value);
	}

}