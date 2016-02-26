package org.reactiveblocks.ibmiot.iotfoundation;

import org.reactiveblocks.ibmiot.IOTParams;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.arctis.runtime.SingletonData;

public class IoTFoundation extends Block {

	public SingletonData wrap(IOTParams params) {
		SingletonData d = new SingletonData();
		d.setData(params);
		return d;
	}
	
	public SingletonData wrapStop() {
		return new SingletonData();
	}

}
