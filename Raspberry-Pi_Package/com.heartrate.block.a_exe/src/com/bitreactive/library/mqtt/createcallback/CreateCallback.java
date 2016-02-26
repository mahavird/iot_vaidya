package com.bitreactive.library.mqtt.createcallback;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;

import no.ntnu.item.arctis.runtime.Block;

public class CreateCallback extends Block {
	private boolean isStoped = false;
	
	public MqttCallback create(final MQTTConfigParam config) {
		isStoped = false;
		return new MqttCallback() {
			
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				logger.debug("Got message, topic=" + topic + " , size = " + message.getPayload().length);
				if (!isStoped) {
					MQTTMessage msg = new MQTTMessage(message.getPayload(), topic);
					msg.setQoS(message.getQos()).setRetained(message.isRetained());
					sendToBlock("MSG_ARRIVED", msg);
				}
			}
			
			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {
			}
			
			@Override
			public void connectionLost(Throwable cause) {
				String errMsg = "Lost Connection to " + config.getUrl() + ", cause: " + cause.toString();
				if (!isStoped) {
					logger.debug(errMsg);
					sendToBlock("CONN_LOST", errMsg);
				}
			}
		};
	}

	public void stop() {
		isStoped = true;
	}
}
