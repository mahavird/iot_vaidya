package com.bitreactive.library.mqtt.subscribehandler;

import java.util.Set;

import no.ntnu.item.arctis.runtime.Block;

import com.bitreactive.library.mqtt.SubscribeTopic;
import com.bitreactive.library.mqtt.subscriber.Subscriber;

public class SubscribeHandler extends Block {

	public Subscriber.Parameters p;

	public int getDuration() {
		return 1000;	//1 second
	}

	public boolean containTopic() {
		return p.topics.length > 0;
	}
	
	public void addTopics(Set<SubscribeTopic> topics) {
		if (topics == null) {
			return;
		}
		for (int i = 0; i < p.topics.length; i++) {
			topics.add(new SubscribeTopic(p.topics[i], p.qos[i]));
		}
		String[] t = new String[topics.size()];
		int[] q = new int[topics.size()];
		int i = 0;
		for (SubscribeTopic st : topics) {
			t[i] = st.topic;
			q[i++] = st.qos;
		}
		p.topics = t;
		p.qos = q;
	}
}
