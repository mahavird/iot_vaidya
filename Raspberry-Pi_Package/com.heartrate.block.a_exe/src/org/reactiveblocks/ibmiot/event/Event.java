package org.reactiveblocks.ibmiot.event;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.arctis.runtime.SingletonData;

public class Event<Data> extends Block {
	
	// Instance parameter. Edit only in overview page.
	public final java.lang.String eventId;

	public SingletonData wrap(Data d) {
		SingletonData s = new SingletonData();
		s.setData(new org.reactiveblocks.ibmiot.Event(d, eventId));
		return s;
	}

	// Do not edit this constructor.
	public Event(java.lang.String eventId) {
	    this.eventId = eventId;
	}
}
