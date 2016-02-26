package com.heartrate.block.a.component;
import java.util.Arrays;

import org.reactiveblocks.ibmiot.IOTParams;
import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block 
{
	String total;
	int j=0;
	public IOTParams createParams() 
	{
		String deviceId = "0017c4a5db30";
		String deviceType = "lappy";
		String organization = "tbqwz5";
		String authenticationToken = "21wUt9dzHVS1mIBc__";
		IOTParams p = new IOTParams(deviceId, deviceType, organization, authenticationToken);
		return p;
	}

	public void ready() 
	{
		logger.info("READY!");
		System.out.println("Ready");
	}


	public int samprate() 
	{
		return 1000;
	}

	public void blockinitialized() 
	{
		System.out.println("block Initialized");
	}

	public String sendData(String value) 
	{
		logger.info("Application Block Value "+value);
		return value;

	}
	

}
