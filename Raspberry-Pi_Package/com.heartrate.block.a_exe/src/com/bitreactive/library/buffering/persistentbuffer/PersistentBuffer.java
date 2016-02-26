package com.bitreactive.library.buffering.persistentbuffer;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import no.ntnu.item.arctis.runtime.Block;

import com.gaborcselle.persistent.PersistentQueue;


public class PersistentBuffer<Item extends Serializable> extends Block {

	private PersistentQueue<Item> pq;
	
	public void init(String filename) {
		if (filename == null || filename.isEmpty()) {
			//random filename in java.io.tmpdir
			filename =  UUID.randomUUID().toString().substring(0, 20);
		}
		
		final String f = System.getProperty("java.io.tmpdir") + filename;
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				try {
					pq = new PersistentQueue<Item>(f);
					logger.info("Data for persistent buffer is stored in " + f);
				} catch (IOException e) {
					logger.error("Failed to create file for persistent buffer:" + f + 
							", error message:" + e.getLocalizedMessage());
				}
				sendToBlock("INIT");
			}
		};
		runAsync(r);
	}
	
	public void add(final Item i) {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				try {
					pq.add(i);
					logger.debug("added");
				} catch (IOException e) {
					logger.error(e.getLocalizedMessage());
				}
			}
		};
		runAsync(r);
	}

	public Item peek() {
		logger.debug("peeking");
		if (pq != null) {
			return pq.peek();
		}
		return null;
	}

	public void remove() {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				Item i = null;
				try {
					i = pq.remove();
					logger.debug("removed");
				} catch (IOException e) {
					logger.error(e.getLocalizedMessage());
				}
				sendToBlock("REMOVED", i);
			}
		};
		runAsync(r);
	}
}
