package com.bitreactive.library.mqtt.publishpolicy;

import java.util.Date;

import no.ntnu.item.arctis.runtime.Block;

import org.eclipse.paho.client.mqttv3.MqttClient;

import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.publisher.Publisher;

public class PublishPolicy extends Block {
	
	public PublishPolicy.Parameters p;
	public MQTTMessage msg;

	public static class Parameters {
		MqttClient client;
		int messageFreshnessInSeconds;	//don't (re)send messages older than, i.e., 5 seconds, -1 means keep them all
		public Parameters(MqttClient client, int messageFreshnessInSeconds) {
			this.client = client;
			this.messageFreshnessInSeconds = messageFreshnessInSeconds;
		}
	}

	public boolean isConnected() {
		logger.debug("client connected: " + p.client.isConnected());
		if (p.client == null) {
			return false;
		}
		return p.client.isConnected();
	}

	public boolean discardDueToQos() {
		boolean discard = msg.getQoS() <= 0;
		if (discard) {
			logger.debug("Discard message due to qos=" + msg.getQoS());
		}
		return discard;
	}

	public boolean discardDueToFreshness() {
		if (p.messageFreshnessInSeconds == -1) {
			return false;
		}
		long diff = (new Date()).getTime() - msg.getTimestamp(); 
		if (diff <= p.messageFreshnessInSeconds * 1000) {
			return false;
		} else {
			logger.debug("Discard message due to freshness. diffInS=" + diff/1000 + ", oldestMsgsRuleInS=" + p.messageFreshnessInSeconds);
			return true;
		}
	}
	
	public Publisher.Parameters addClient() {
		return new Publisher.Parameters(p.client, msg);
	}

	public void buffer() {
		logger.debug("To buffer msg ");
	}

	public void toWait() {
		logger.debug("wait ");
	}
	
	public void getNext() {
		logger.debug("Get next from buffer");
	}
}
