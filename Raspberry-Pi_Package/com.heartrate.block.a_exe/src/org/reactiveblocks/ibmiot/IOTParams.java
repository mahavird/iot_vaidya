package org.reactiveblocks.ibmiot;

public class IOTParams {
	
	private String deviceId;
	private String deviceType;
	private String organizationId;
	private String authenticationToken;

	/** 
	 * @param deviceID - A 12 hexadecimal character MAC address in lower case, without delimiting characters. For example, a36d7c91bf9e. "410fd9078216"
	 * @param deviceType - an identifier you provide, for example “acme-thing” or "iotqs-sensor"
	 */
	public IOTParams(String deviceId, String deviceType, String organizationId, String authenticationToken) {
		this.deviceId = deviceId;
		this.deviceType = deviceType;
		this.organizationId = organizationId;
		this.authenticationToken = authenticationToken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}
	
}