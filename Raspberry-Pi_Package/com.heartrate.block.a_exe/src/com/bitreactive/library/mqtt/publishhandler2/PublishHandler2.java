package com.bitreactive.library.mqtt.publishhandler2;

import no.ntnu.item.arctis.runtime.Block;

import org.eclipse.paho.client.mqttv3.MqttClient;

import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.publisher.Publisher;
import com.bitreactive.library.mqtt.publishpolicy.PublishPolicy;

public class PublishHandler2 extends Block {

	public PublishHandler2.Parameters p;

	public static class Parameters {
		MqttClient client;
		String filename = null;
		int intervalOfIncMessagesInMillis;
		int messageFreshnessInSeconds;	//don't (re)send messages older than, i.e., 5 seconds, -1 means keep them all
		
		public Parameters(MqttClient client, String filename, 
				int intervalOfIncMessagesInMillis, int messageFreshnessInSeconds) {
			this.client = client;
			this.filename = filename;
			this.intervalOfIncMessagesInMillis = intervalOfIncMessagesInMillis;
			this.messageFreshnessInSeconds = messageFreshnessInSeconds;
		}
	}
	
	public PublishPolicy.Parameters getParams() {
		return new PublishPolicy.Parameters(p.client, p.messageFreshnessInSeconds);
	}
	
	private int duration;

	public String getFilename() {
		duration = p.intervalOfIncMessagesInMillis;
		return p.filename;
	}

	public int getDuration() {
		return duration;
	}

	public void empty() {
		duration += 100;
	}

	public void removed() {
		duration = p.intervalOfIncMessagesInMillis;
	}
}
