package com.grisaf.mqttsample;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTUtils {

	private static MqttClient client;

	public static MqttClient getClient() {
		return client;
	}

	public static boolean connect(String url) {
		try {
			MemoryPersistence persistance = new MemoryPersistence();
			client = new MqttClient("tcp://" + url + ":1883", "client1", persistance);
			client.connect();
			return true;
		} catch (MqttException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean pub(String topic, String payload) {
		MqttMessage message = new MqttMessage(payload.getBytes());
		try {
			client.publish(topic, message);
			return true;
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
