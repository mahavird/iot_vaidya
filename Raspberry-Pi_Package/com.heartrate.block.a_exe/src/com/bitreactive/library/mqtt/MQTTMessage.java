package com.bitreactive.library.mqtt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class MQTTMessage implements Serializable {

	private static final long serialVersionUID = 178632623432203247L;

	private byte[] payload;
	private String topic;
	private int QoS;
	private boolean retained = false;
	private long timestamp = (new Date()).getTime();

	/**
	 * Creates new MQTT Message object with content and topic
	 * See {@linkplain http://mqtt.org/wiki/doku.php/topic_format} about topic syntax
	 * Use setQoS() to set Quality Of Service for this message.
	 * @param payload
	 * @param topic
	 */

	public MQTTMessage(byte[] payload, String topic) {
		this.payload = payload;
		this.topic = topic;
		this.QoS = -1;   // Use default from MQTT publish block
	}

	public byte[] getPayload() {
		return payload;
	}

	public String getTopic() {
		return topic;
	}

	public MQTTMessage setTopic(String topic) {
		this.topic = topic;
		return this;
	}

	public int getQoS() {
		return QoS;
	}

	public MQTTMessage setQoS(int qoS) {
		QoS = qoS;
		return this;
	}

	public boolean getRetained() {
		return retained;
	}

	public MQTTMessage setRetained(boolean retained) {
		this.retained = retained;
		return this;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public MQTTMessage setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public byte[] serialize() {
		byte[] retVal = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(this);
			retVal = bos.toByteArray();
		} catch (IOException ex) {
			ex.printStackTrace();
			// ignore close exception
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
			try {
				bos.close();
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		return retVal;
	}

	public static MQTTMessage deserialize(byte[] b) {
		ByteArrayInputStream bis = new ByteArrayInputStream(b);
		MQTTMessage retVal = null;
		ObjectInput in = null;
		try {
			in = new ObjectInputStream(bis);
			retVal = (MQTTMessage) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
			} catch (IOException ex) {
				// ignore close exception
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		return retVal;
	}
}
