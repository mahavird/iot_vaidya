package com.bitreactive.library.flowlogic.limiter;

import no.ntnu.item.arctis.runtime.Block;

public class Limiter<T> extends Block {

	// Instance parameter. Edit only in overview page.
	public final int minInterval;

	public int overrideMinInterval = -1;
	
	// Do not edit this constructor.
	public Limiter(int minInterval) {
	    this.minInterval = minInterval;
	}

	public int getInterval() {
		int retVal =  overrideMinInterval > 0 ? this.overrideMinInterval : this.minInterval;
		return retVal;
	}

	public void setInterval(int overrideMinInterval) {
		this.overrideMinInterval = overrideMinInterval;
	}
}
