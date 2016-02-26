package com.bitreactive.library.mqtt;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MQTTConfigParam {
	private String serverName;
	private int portNo = -1;	//use default
	private String clientId;

	private boolean useSecureConn = false;

	private String defaultPublishTopic = "";
	private List<String> subscribeTopics = new ArrayList<String>();
	private List<Integer> subscribeQos = new ArrayList<Integer>();	//default qos 1

	private String username;
	private String password;

	private int keepAliveInterval = -1;   //use default
	private int connectionTimeout  = -1;  //use default

	private Boolean cleanSession = null;  // use default

	private int defaultQoS = 0;

	private String CAcertFile = null;
	private String certFile = null;
	private String privateKeyFile = null;

	private MQTTMessage lastWillMsg = null;

	/**
	 * Creates new configuration object with all connection parameters. Topic(s) must be added later
	 * @param serverName
	 * @param portNo
	 * @param clientId
	 */
	public MQTTConfigParam(String serverName, int portNo, String clientId) {
		super();
		this.serverName = serverName;
		this.portNo = portNo;
		this.clientId = clientId;
		this.setDefaultSecureCommFlag(portNo);
	}

	/**
	 * Creates new configuration object with host and clientId.
	 * Port number is default for MQTT
	 * Topic(s) must be added later
	 * @param serverName
	 * @param clientId
	 */
	public MQTTConfigParam(String serverName, String clientId) {
		super();
		this.serverName = serverName;
		this.clientId = clientId;
	}

	/**
	 * Creates new configuration object with host and port.
	 * Client ID is generated automatically
	 * Topic(s) must be added later
	 * @param serverName
	 * @param portNo
	 */
	public MQTTConfigParam(String serverName, int portNo) {
		super();
		this.serverName = serverName;
		this.portNo = portNo;
		this.clientId = generateUUID();
		this.setDefaultSecureCommFlag(portNo);
	}

	/**
	 * Creates new configuration object with host.
	 * Port number is default for MQTT
	 * Client ID is generated automatically
	 * Topic(s) must be added later
	 * @param serverName
	 */
	public MQTTConfigParam(String serverName) {
		super();
		this.serverName = serverName;
		this.clientId = generateUUID();
	}

	public MQTTConfigParam useSecureConn() {
		this.useSecureConn = true;
		return this;
	}

	public MQTTConfigParam useSecureConn(boolean use) {
		this.useSecureConn = use;
		return this;
	}

	private MQTTConfigParam setDefaultSecureCommFlag(int portNo) {
		if (portNo == 8883) {
			this.useSecureConn = true;
		}
		return this;
	}

	public String getUrl() {
		StringBuilder sb = new StringBuilder();
		if (useSecureConn) {
			sb.append("ssl://");
		} else {
			sb.append("tcp://");
		}
		sb.append(serverName);
		if (portNo != -1) {
			sb.append(":").append(portNo);
		}
		return sb.toString();
	}

	public String getClientId() {
		return clientId;
	}

	/**
	 * Add subscription topic. See {@linkplain http://mqtt.org/wiki/doku.php/topic_format} and {@link http://www.embedded.com/electronics-blogs/embedded-cloud-talkers/4397229/Device-to-Cloud--}
	 * about topic syntax
	 * @param subscribeTopic Another Topic to subscribe to (if applicable)
	 */
	public MQTTConfigParam addSubscribeTopic(String subscribeTopic) {
		addSubscribeTopic(subscribeTopic, 1);
		return this;
	}

	public String[] getSubscribeTopics() {
		return subscribeTopics.toArray(new String[0]);
	}

	public void clearSubscribeTopics() {
		this.subscribeTopics.clear();
		this.subscribeQos.clear();
	}

	public MQTTConfigParam addSubscribeTopic(String subscribeTopic, int maxQoS) {
		this.subscribeTopics.add(subscribeTopic);
		this.subscribeQos.add(new Integer(maxQoS));
		return this;
	}

	public int[] getSubscribeQoS() {
		int[] result = new int[subscribeQos.size()];
		int i = 0;
		for (Integer e : subscribeQos) {
			result[i++] = e.intValue();
		}
		return result;
	}

	/**
	 * Set default publish topic. See {@linkplain http://mqtt.org/wiki/doku.php/topic_format} about topic syntax
	 * Used when sending message, will be used if topic in message is empty
	 * @param defaultPublishTopic
	 */
	public MQTTConfigParam setDefaultPublishTopic(String defaultPublishTopic) {
		this.defaultPublishTopic = defaultPublishTopic;
		return this;
	}
	public String getDefaultPublishTopic() {
		return defaultPublishTopic;
	}

	static public String generateUUID() {
		return UUID.randomUUID().toString().substring(0, 20);
	}

	public MQTTConfigParam setUsername(String username) {
		this.username = username;
		return this;
	}

	public MQTTConfigParam setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public MQTTConfigParam setCertificatesAndKeyFiles(String CAcertFile, String certFile, String privateKeyFile) {
		this.CAcertFile = CAcertFile;
		this.certFile = certFile;
		this.privateKeyFile = privateKeyFile;
		return this;
	}

	public String getCAcertFile() {
		return CAcertFile;
	}

	public String getCertFile() {
		return certFile;
	}

	public String getPrivateKeyFile() {
		return privateKeyFile;
	}

	public MQTTMessage getLastWillMsg() {
		return lastWillMsg;
	}

	public MQTTConfigParam setLastWillMsg(MQTTMessage lastWillMsg) {
		this.lastWillMsg = lastWillMsg;
		return this;
	}

	public int getKeepAliveInterval() {
		return keepAliveInterval;
	}

	public MQTTConfigParam setKeepAliveInterval(int keepAliveInterval) {
		this.keepAliveInterval = keepAliveInterval;
		return this;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public MQTTConfigParam setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
		return this;
	}

	public Boolean getCleanSession() {
		return cleanSession;
	}

	public MQTTConfigParam setCleanSession(Boolean cleanSession) {
		this.cleanSession = cleanSession;
		return this;
	}

	public int getDefaultQoS() {
		return defaultQoS;
	}

	public MQTTConfigParam setDefaultQoS(int defaultQoS) {
		this.defaultQoS = defaultQoS;
		return this;
	}
}
