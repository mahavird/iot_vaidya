package com.bitreactive.library.mqtt.publishmanager;

import no.ntnu.item.arctis.runtime.Block;

import org.eclipse.paho.client.mqttv3.MqttClient;

import com.bitreactive.library.mqtt.publishhandler2.PublishHandler2;
import com.bitreactive.library.mqtt.publishpolicy.PublishPolicy;

public class PublishManager extends Block {

	public PublishManager.Parameters p;

	public static class Parameters {
		boolean usePersistentBuffer;
		MqttClient client;
		int messageFreshnessInSeconds;	//don't (re)send messages older than, i.e., 5 seconds, -1 means keep them all
		String filename;
		int intervalOfIncMessagesInMillis;

		public Parameters(MqttClient client, int messageFreshnessInSeconds) {
			this.usePersistentBuffer = false;
			this.client = client;
			this.messageFreshnessInSeconds = messageFreshnessInSeconds;
		}
		
		public Parameters(MqttClient client, String filename, int intervalOfIncMessagesInMillis) {
			this.usePersistentBuffer = true;
			this.client = client;
			this.filename = filename;
			this.intervalOfIncMessagesInMillis = intervalOfIncMessagesInMillis;
		}
	}

	public boolean usePersistent() {
		return p.usePersistentBuffer;
	}

	public PublishPolicy.Parameters createParam() {
		return new PublishPolicy.Parameters(p.client, p.messageFreshnessInSeconds);
	}

	public PublishHandler2.Parameters createParam2() {
		return new PublishHandler2.Parameters(p.client, p.filename, 
				p.intervalOfIncMessagesInMillis, p.messageFreshnessInSeconds);
	}
	
}
