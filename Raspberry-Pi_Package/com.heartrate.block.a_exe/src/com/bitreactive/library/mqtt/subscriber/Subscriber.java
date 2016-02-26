package com.bitreactive.library.mqtt.subscriber;

import no.ntnu.item.arctis.runtime.Block;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber extends Block {
	public static class Parameters {
		MqttClient client;
		public String[] topics;
		public int[] qos;
		
		public Parameters(MqttClient client, String[] topics, int[] qos) {
			this.client = client;
			this.topics = topics;
			this.qos = qos;
		}
	}
	
	private boolean isStopped = false;

	public void subscribe(final Subscriber.Parameters p) {
		isStopped = false;
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				try {
					p.client.subscribe(p.topics, p.qos);
					if (!isStopped) {
						logger.debug("(Re)subscription sucessful");
						sendToBlock("SUBS_OK");
					}
				} catch (MqttException e) {
					String errMsg = "Error during subscribe: " + e + " " + e.getMessage() + " " + e.getCause() + " " + e.getStackTrace()[0].getMethodName();
					if (!isStopped) {
						logger.debug(errMsg);
						sendToBlock("SUBS_ERROR", errMsg);
					}				
				}
			}
		};
		runAsync(r);
	}
	
	public void stop() {
		isStopped = true;
	}
}
