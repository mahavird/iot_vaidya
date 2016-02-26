package com.bitreactive.library.mqtt.connhandler;

import javax.net.ssl.SSLSocketFactory;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.MQTTSocketFactory;

import no.ntnu.item.arctis.runtime.Block;

public class ConnHandler extends Block {
	
	private MqttClient client = null;
	private MqttConnectOptions options = new MqttConnectOptions();

	private boolean isStopping = false;
	private boolean isReconnecting = false;
	
	private int waitDuration = -1;
	
	public ConnHandler.Parameters p;
	
	public static class Parameters {
		MQTTConfigParam config;
		MqttCallback callback;
		int waitDurationToReconnectInMillis;
		boolean useExponentialBackoffReconnect;
		
		public Parameters(MQTTConfigParam config, MqttCallback callback, 
				int waitDurationToReconnectInMillis, boolean useExponentialBackoffReconnect) {
			this.config = config;
			this.callback = callback;
			this.waitDurationToReconnectInMillis = waitDurationToReconnectInMillis;
			this.useExponentialBackoffReconnect = useExponentialBackoffReconnect;
		}
	}
	
	public void connect() {
		if (waitDuration == -1) {
			waitDuration = p.waitDurationToReconnectInMillis;
		}
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				try {
					
					MQTTConfigParam c = p.config;

					if (c.getClientId()!=null) {
						client = new MqttClient(c.getUrl(), c.getClientId(), new MemoryPersistence());
					} else {
						client = new MqttClient(c.getUrl(), MqttClient.generateClientId(), new MemoryPersistence());
					}
					client.setCallback(p.callback);
					if (c.getUsername() != null && c.getPassword() != null) {
						options.setUserName(c.getUsername());
						options.setPassword(c.getPassword().toCharArray());
					}

					options.setKeepAliveInterval(5); // (!)
					
					if (c.getCAcertFile() != null && c.getCertFile() != null && c.getPrivateKeyFile() != null) {
						SSLSocketFactory socketFactory;
						socketFactory = MQTTSocketFactory.getSocketFactory(c.getCAcertFile(),c.getCertFile(),c.getPrivateKeyFile());
						options.setSocketFactory(socketFactory);						
					}
						
					if (c.getLastWillMsg() != null) {
						MQTTMessage lastWill = c.getLastWillMsg();
						if (lastWill.getTopic() != null || lastWill.getPayload() != null) {
							options.setWill(lastWill.getTopic(), lastWill.getPayload(), lastWill.getQoS(), lastWill.getRetained());							
						} else {
							logWarn("Unable to set last will: Missing topic and/or payload");							
						}
					}
					
					if (c.getConnectionTimeout() >= 0) {
						options.setConnectionTimeout(c.getConnectionTimeout());
					}

					if (c.getKeepAliveInterval() >= 0) {
						options.setKeepAliveInterval(c.getKeepAliveInterval());						
					}
										
					if (c.getCleanSession() != null) {
						logDebug("Setting clean session flag to " + c.getCleanSession());
						options.setCleanSession(c.getCleanSession());
					}
					
					if (!isStopping) {						
						logger.debug("Trying to connect to " + p.config.getUrl() + " with id=" + client.getClientId());
						client.connect(options);
						logger.debug("Connection to " + p.config.getUrl() + " with id=" + client.getClientId() + " is OK");
						if (!isStopping) {
							waitDuration = p.waitDurationToReconnectInMillis;
							sendToBlock("INIT_OK", client);
						}
					}
				} catch (MqttException e) {
					String errMsg = "Error during connect: " + e + " " + e.getMessage() + " , retrying...";
					if (!isStopping) {
						logger.warn(errMsg);
						if (p.useExponentialBackoffReconnect) {
							waitDuration *= 2;
						}
						sendToBlock("INIT_FAILED");
					}				
				} catch (Exception e) {
					String errMsg = "Error during connect: " + e + " " + e.getMessage() + " , retrying...";
					if (!isStopping) {
						logger.warn(errMsg);
						if (p.useExponentialBackoffReconnect) {
							waitDuration *= 2;
						}
						sendToBlock("INIT_FAILED");
					}				
				}
			}
		};
		runAsync(r);
	}
	
	public void reconnect() {
		isReconnecting = true;
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				try {
					logger.debug("Attempt to reconnect to " + p.config.getUrl());
					client.connect(options);
					isReconnecting = false;
					if (!isStopping) {						
						logger.debug("Reconnected to " + p.config.getUrl());
						waitDuration = p.waitDurationToReconnectInMillis;
						sendToBlock("RECONN_OK");
					}
				} catch (MqttException e) {
					String errMsg = "Reconnect to " + p.config.getUrl() + 
							" with id=" + client.getClientId() + 
							" failed with error: " + e + " " + e.getMessage() + 
							" " + e.getCause() + " " + e.getStackTrace()[0].getMethodName();
					isReconnecting = false;
					if (!isStopping) {
						logger.warn(errMsg);
						if (p.useExponentialBackoffReconnect) {
							waitDuration *= 2;
						}
						sendToBlock("RECONN_FAILED");
					}				
				}
			}
		};
		runAsync(r);
	}
	
	public int getWaitingDuration() {
		return waitDuration;
	}
	
	public void stop() {
		isStopping = true;
		isReconnecting = false;
		waitDuration = -1;
		if (client != null) {
			try {
				if (client.isConnected()) {
					logger.debug("Disconnecting MQTT Client " + client.getServerURI());
					client.disconnect(0);
				}
			} catch (MqttException e) {
				logger.debug("Error during MQTT Client disconnect: " + e + ":" + e.getCause() + " " + e.getMessage() + " " + e.getStackTrace()[0].getMethodName());
			}
			
			try {
				logger.debug("Closing MQTT Client " + client.getServerURI());
				client.close();				
			} catch (MqttException e) {
				logger.debug("Error during MQTT Client close: " + e + ":" + e.getCause() + " " + e.getMessage() + " " + e.getStackTrace()[0].getMethodName());
			}
			logger.debug("Stopped MQTT Client " + client.getServerURI());
		}
		client = null;
	}

	public boolean tryReconnect() {
		if (client == null || client.isConnected() || isReconnecting) {
			return false;
		}
		return true;
	}
}
