package org.reactiveblocks.ibmiot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
	
	protected final static DateFormat ISO8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
	public Object d; // the data
	public String ts; // timestamp
	
	private transient String eventId;
	
	public Event(Object d, String eventId) {
		this.eventId = eventId;
		this.d = d;
		this.ts = ISO8601_DATE_FORMAT.format(new Date());
	}
	
	public String getId() {
		return eventId;
	}

}
