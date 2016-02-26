package com.bitreactive.library.mqtt;

public class SubscribeTopic {
	public String topic;
	public int qos;
	
	public SubscribeTopic(String topic, int qos) {
		this.topic = topic;
		this.qos = qos;
	}
	
	public SubscribeTopic(String topic) {
		this.topic = topic;
		this.qos = 1;
	}
}
