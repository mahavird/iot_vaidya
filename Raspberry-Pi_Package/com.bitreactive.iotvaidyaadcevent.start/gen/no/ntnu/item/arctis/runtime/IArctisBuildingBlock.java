package no.ntnu.item.arctis.runtime;

/**
 * This interface describes which methods a building block class needs to 
 * implement in order to work properly with the runtime classes added during the
 * compilation process. 
 * 
 * We recommend that building blocks rather extend <code>Block</code>, which provides
 * all functions a block can use in a more comfortable way.
 * 
 * @author kraemer
 *
 */
public interface IArctisBuildingBlock {
	
	/**
	 * Provides a building block instance with its unique ID within the system.
	 * @param blockID the unique ID of this building block instance
	 */
	public void setBlockID(String blockID, String sessionID);
	
	public void setRuntime(IRuntime runtime);

}
