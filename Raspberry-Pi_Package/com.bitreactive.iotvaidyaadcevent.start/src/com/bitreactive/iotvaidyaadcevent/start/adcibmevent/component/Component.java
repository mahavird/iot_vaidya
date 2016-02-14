package com.bitreactive.iotvaidyaadcevent.start.adcibmevent.component;

import org.reactiveblocks.ibmiot.IOTParams;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block 
{
	public static class EventData {
		String adc;
		//String temperature, humidity, objectTemperature;

		//public EventData(String temperature, String humidity,String objectTemperature)
		public EventData(String adc) 
		{
			this.adc=adc;
			//this.temperature = temperature;
			//this.humidity = humidity;
			//this.objectTemperature = objectTemperature;
		}
	}
	
	public void helloWorld() 
	{
		System.out.println("Hello World!");
	}

	public int samplinginterval() {
		int samplingIntervalInMs = 1000;
		return samplingIntervalInMs;
	}

	public String printoutput(double value) 
	{
		System.out.println(value);
		double total = value;
		String total2 = String.valueOf(total);
		return total2;
	}
	
	public EventData adcEvent(String adcvalue)
	{
		EventData d = new EventData(adcvalue);
		return d;
	}

	public void blockinitialized() 
	{
		System.out.println("Block initialized successfuly");
	
	}

	public IOTParams createParams() 
	{
		// TODO set the following variables after 
		// registering a new device at the IoT Foundation:
		String deviceId = "7ce9d311d2d2";
		String deviceType = "rpi";
		String organization = "ypeqnm";
		String authenticationToken = "5LYe6R*NhXKa0)1qK5";
		IOTParams p = new IOTParams(deviceId, deviceType, organization, authenticationToken);
		return p;
	}

}
