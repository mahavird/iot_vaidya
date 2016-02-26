package com.bitreactive.library.mqtt.robustmqtt;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.connhandler.ConnHandler;
import com.bitreactive.library.mqtt.publishmanager.PublishManager;
import com.bitreactive.library.mqtt.subscriber.Subscriber;

import no.ntnu.item.arctis.runtime.Block;

public class RobustMQTT extends Block {
	public RobustMQTT.Parameters p;
	public MqttClient client;

	public static class Parameters {
		public MQTTConfigParam config;

		/**
		 * Used to calculate waiting duration before an attempt to reconnect.
		 * When exponential backoff reconnect is not used, this is the waiting duration.
		 */
		public int waitDurationToReconnectInMillis = 5000;	// 5 seconds

		/**
		 * Using exponential backoff reconnect means:
		 * 1. Waiting duration for reconnect is doubled for every failed reconnect attempt.
		 * 2. Successful attempt sets the waiting duration back to the value
		 * in the property waitDurationToReconnectInMillis.
		 */
		public boolean useExponentialBackoffReconnect = false;

		/**
		 * A message is considered old if its timestamp is older than some seconds.
		 * This message will not be sent.
		 * -1 means all messages are fresh.
		 */
		public int messageFreshnessInSeconds = -1;

		/**
		 * Whether persistent buffer should be used for all published messages.
		 */
		public boolean usePersistentBuffer = false;

		/**
		 * If persistent buffer is used, published messages are temporarily saved in this file.
		 * The file will be stored in the Java's default temp directory (system property "java.io.tmpdir").
		 * null value is permitted. It means use random filename.
		 * Supply a filename if you want the stored message to be sent even after application restart.
		 */
		public String filenameForPersistentBuffer = null;

		/**
		 * Only useful when using persistent buffer.
		 * An estimation of how often messages are published.
		 * Used to calculate dynamic waiting duration before subsequent peek into the persistent buffer.
		 * - If the buffer was empty, the waiting duration is increased.
		 * - If an item was removed before, the waiting duration is set to this property.
		 */
		public int intervalOfIncMessagesInMillis = 1000;	//1 seconds

		public Parameters(MQTTConfigParam config, int messageFreshnessInSeconds) {
			this.config = config;
			this.messageFreshnessInSeconds = messageFreshnessInSeconds;
		}

		public Parameters(MQTTConfigParam config) {
			this.config = config;
		}

		public Parameters(MQTTConfigParam config, boolean usePersistentBuffer) {
			this(config, usePersistentBuffer, null);
		}

		public Parameters(MQTTConfigParam config, boolean usePersistent, String filenameForPersistentBuffer) {
			this.config = config;
			this.usePersistentBuffer = true;
			this.filenameForPersistentBuffer = filenameForPersistentBuffer;
		}

		public Parameters setWaitDurationToReconnectInMillis(int waitDurationToReconnectInMillis) {
			this.waitDurationToReconnectInMillis = waitDurationToReconnectInMillis;
			return this;
		}

		public Parameters useExponentialBackOffReconnect() {
			this.useExponentialBackoffReconnect = true;
			return this;
		}

		public Parameters useExponentialBackOffReconnect(boolean useExponentialBackOffReconnect) {
			this.useExponentialBackoffReconnect = useExponentialBackOffReconnect;
			return this;
		}
		
		public Parameters setMessageFressnessInSeconds(int messageFreshnessInSeconds) {
			this.messageFreshnessInSeconds = messageFreshnessInSeconds;
			return this;
		}
		
		public Parameters usePersistentBuffer(String filenameForPersistentBuffer) {
			this.usePersistentBuffer = true;
			this.filenameForPersistentBuffer = filenameForPersistentBuffer;
			return this;
		}

		public Parameters usePersistentBuffer(boolean usePersistentBuffer, String filenameForPersistentBuffer) {
			this.usePersistentBuffer = usePersistentBuffer;
			this.filenameForPersistentBuffer = filenameForPersistentBuffer;
			return this;
		}
		
		public Parameters setIntervalOfIncMessagesInMillis(int intervalOfIncMessagesInMillis) {
			this.intervalOfIncMessagesInMillis = intervalOfIncMessagesInMillis;
			return this;
		}
	}

	public MQTTConfigParam getConfig() {
		return p.config;
	}

	public PublishManager.Parameters getPublishParam() {
		if (p.usePersistentBuffer) {
			return new PublishManager.Parameters(client, p.filenameForPersistentBuffer, p.intervalOfIncMessagesInMillis);
		} else {
			return new PublishManager.Parameters(client, p.messageFreshnessInSeconds);
		}
	}

	public Subscriber.Parameters getSubscribeParam() {
		return new Subscriber.Parameters(client, p.config.getSubscribeTopics(), p.config.getSubscribeQoS());
	}

	public ConnHandler.Parameters startConn(MqttCallback callback) {
		return new ConnHandler.Parameters(p.config, callback,
				p.waitDurationToReconnectInMillis, p.useExponentialBackoffReconnect);
	}

	public void connLost(String e) {
		logger.warn(e);
	}

}
