package com.bitreactive.library.mqtt.publishhandler;

import java.util.Date;
import java.util.List;

import no.ntnu.item.arctis.runtime.Block;

import com.bitreactive.library.mqtt.MQTTMessage;


public class PublishHandler extends Block {

	public void overflow(List<MQTTMessage> m) {
		logger.debug("Discarded a message due to full buffer, bufferSize= 100, msgTimestamp:" + new Date(m.get(0).getTimestamp()));
	}

}
