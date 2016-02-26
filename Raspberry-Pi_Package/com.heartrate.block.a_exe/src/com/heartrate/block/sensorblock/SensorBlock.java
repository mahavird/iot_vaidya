package com.heartrate.block.sensorblock;

import com.heartrate.block.mcp3008adc.MCP3008ADC;
import no.ntnu.item.arctis.runtime.Block;

public class SensorBlock extends Block {
	public MCP3008ADC.Parameters getParams(int samplingInterval) {
		MCP3008ADC.Parameters p = new MCP3008ADC.Parameters();
		p.numOfSampleForAverage = 1;
		p.samplingIntervalInMs = samplingInterval;
		return p;
	}

	public String heartbeat(String in) {
		logger.info("Heart Rate Sensor Block");
		logger.info("Concatenated Value" + in);
		return in;
	}
}
