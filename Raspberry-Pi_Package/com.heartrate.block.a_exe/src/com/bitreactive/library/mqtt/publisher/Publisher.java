package com.bitreactive.library.mqtt.publisher;

import no.ntnu.item.arctis.runtime.Block;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import com.bitreactive.library.mqtt.MQTTMessage;

public class Publisher extends Block {
	
	public static class Parameters {
		MqttClient client;
		MQTTMessage message;
		public Parameters(MqttClient client, MQTTMessage message) {
			this.client = client;
			this.message = message;
		}
	}
	
	private boolean isStopped = false;

	
	public void publish(final Publisher.Parameters p) {
		isStopped = false;
		String topic = p.message.getTopic();

		final MqttTopic t = p.client.getTopic(topic);				
		if (p.message.getQoS() < 0) {  // TODO: MQTTConfigParam contains default QoS, should be used here also.
			p.message.setQoS(0);
		}
		p.client.setTimeToWait(3000);
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				try {
					// Publish can take time if the connection is down, and qos is 1 or 2.
					// then publish will first send some more messages, but then blocks,
					// until the connection is up again. 
					//The paho client allows a maximum of 10 in-flight messages at any one time.
					t.publish(p.message.getPayload(), p.message.getQoS(), p.message.getRetained());
					if (!isStopped) {
						logger.debug("Publish sucessful, payload length=" + p.message.getPayload().length);
						sendToBlock("PUB_OK", p.message);
					}
				} catch (MqttException e) {
					String errMsg = "Error during publish: " + e + " " + e.getMessage() + " " + e.getCause() + " " + e.getStackTrace()[0].getMethodName();
					if (!isStopped) {
						logger.debug(errMsg);
						sendToBlock("PUB_ERROR", p.message);
					}
				}
			}
		};
		runAsync(r);
	}

	public void stopped() {
		isStopped = true;
	}

}
