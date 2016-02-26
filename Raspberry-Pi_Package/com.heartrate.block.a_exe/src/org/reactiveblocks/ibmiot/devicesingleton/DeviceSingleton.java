package org.reactiveblocks.ibmiot.devicesingleton;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.reactiveblocks.ibmiot.Command;
import org.reactiveblocks.ibmiot.Event;
import org.reactiveblocks.ibmiot.IOTParams;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.arctis.runtime.SingletonData;

public class DeviceSingleton<Data> extends Block {

	private static Map<String, Set<SingletonData>> commandSubscriptions = new HashMap<String, Set<SingletonData>>();
	
	public void subscribe(SingletonData s) {
		Set<SingletonData> set = commandSubscriptions.get((String)s.getData());
		if(set==null) {
			set = new HashSet<SingletonData>();
			commandSubscriptions.put((String) s.getData(), set);
		}
		set.add(s);
	}
	
	public SingletonData[] distribute(Command c) {
		Set<SingletonData> set = commandSubscriptions.get(c.getCommand());
		if(set==null || set.isEmpty()) {
			return new SingletonData[0];
		} else {
			SingletonData[] dataSet = new SingletonData[set.size()];
			int i = 0;
			for(SingletonData next: set) {
				dataSet[i] = new SingletonData();
				dataSet[i].setSessionID(next.getSessionID());
				dataSet[i].setInstance(next.getInstance());
				dataSet[i].setData(c);
				i++;
			}
			return dataSet;
		}
	}

	public static final String CLIENT_ID_DELIMITER = ":";
	public static final String DOMAIN = "messaging.internetofthings.ibmcloud.com";
	public static final String COMMAND_TOPIC_PREFIX = "iot-2/cmd/";
	
	public static final String EVENT_TOPIC_PREFIX = "iot-2/";
	public static final String EVENT_TOPIC_SUFFIX = "/status/fmt/json";
			
	public static final int MQTT_PORT = 1883;
	public static final int MQTTS_PORT = 8883;
	public static final int MAXIMUM_PAYLOAD_SIZE = 4096;
	public no.ntnu.item.arctis.runtime.SingletonData stop;
	public no.ntnu.item.arctis.runtime.SingletonData start;

	public Parameters init(SingletonData s) {
		IOTParams params = (IOTParams) s.getData();
		checkParameter(params.getAuthenticationToken(), "authentication token");
		checkParameter(params.getOrganizationId(), "organization Id");
		checkParameter(params.getDeviceId(), "device Id");
		checkParameter(params.getDeviceType(), "device type");
		String clientId = "d" + CLIENT_ID_DELIMITER + params.getOrganizationId()
				+ CLIENT_ID_DELIMITER + params.getDeviceType() + CLIENT_ID_DELIMITER
				+ params.getDeviceId();
		MQTTConfigParam m = new MQTTConfigParam(params.getOrganizationId() + "."
				+ DOMAIN, MQTT_PORT, clientId);
		String commandType = "+";
		String format = "json";
		m.addSubscribeTopic(COMMAND_TOPIC_PREFIX + commandType + "/fmt/" + format);
		m.setUsername("use-token-auth");
		m.setPassword(params.getAuthenticationToken());
		Parameters p = new Parameters(m);
		return p;
	}
	
	private void checkParameter(String parameter, String parameterName) {
		if(parameter==null || parameter.trim().isEmpty()) {
			logError("The parameter for " + parameterName + " cannot be empty.");
		}
	}

	public Event unwrapEvent(SingletonData data) {
		return (Event) data.getData();
	}

	public MQTTMessage message(Event e, String out) {
		byte[] bytes = out.getBytes();
		if (bytes.length > MAXIMUM_PAYLOAD_SIZE) {
			return null;
		}
		logInfo("Payload size: " + bytes.length + " bytes. Max. 4096 bytes.");
		logInfo("Payload size: " + out);
		return new MQTTMessage(bytes, EVENT_TOPIC_PREFIX + e.getId() + EVENT_TOPIC_SUFFIX);
	}

	public void logOverload() {
		logInfo("An event was ignored because it was sent too quickly after the previous one.");
	}

	public void logTooLarge() {
		logWarn("An event was not sent because its payload was larger than "
				+ MAXIMUM_PAYLOAD_SIZE + " bytes.");
	}

	public Command command(MQTTMessage m) {
		String command = extractCommand(m.getTopic());
		if(command!=null) {
			Command c = new Command(command, m.getPayload());
			logInfo("Command " + command + " received.");
			return c;
		} else {
			return null;
		}
	}
	
	public static String extractCommand(String topic) {
		if(topic==null || topic.isEmpty() || topic.length() <= COMMAND_TOPIC_PREFIX.length()) return null;
		topic = topic.substring(COMMAND_TOPIC_PREFIX.length());
		return topic.substring(0, topic.indexOf('/'));
	}
	
	public static void main(String... args) {
		System.out.println(extractCommand(null));
		System.out.println(extractCommand(COMMAND_TOPIC_PREFIX + "frank" + "/fmt/json"));
		System.out.println(extractCommand("/"));
		System.out.println(extractCommand("/"));
		System.out.println(extractCommand("/"));
	}

}