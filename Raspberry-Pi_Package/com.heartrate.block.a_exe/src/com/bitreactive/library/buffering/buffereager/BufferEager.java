package com.bitreactive.library.buffering.buffereager;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.item.arctis.runtime.Block;

public class BufferEager<Item> extends Block {
	public Item add;
	public java.util.List<Item> addAll;
	public Item out;

	public List<Item> buffer = new ArrayList<Item>();
	public java.util.List<Item> overflow = new ArrayList<Item>();
	// Instance parameter. Edit only in overview page.
	public final boolean allowDuplicates;
	// Instance parameter. Edit only in overview page.
	public final int capacity;
	// Instance parameter. Edit only in overview page.
	public final boolean keepNewest;
	
	public boolean isEmpty() {
		return buffer.isEmpty();
	}
	
	public boolean hasRoom() {
		if( ((capacity <= 0) || (buffer.size() < capacity))){
			//no overflow
			return true;
		}
		else{
			//overflow
			overflow.clear();
			if(keepNewest){
				overflow.add(buffer.get(0));
				buffer.remove(0);
				buffer.add(add);
				
			} else {
				overflow.add(add);
			}
			return false;
		}
	}
	
	public boolean hasRoomForAll(){
		if( ((capacity <= 0) || (buffer.size() + addAll.size() <= capacity))){
			//no overflow
			return true;
		}
		else{
			//overflow
			overflow.clear();
			if(keepNewest){
				int numberToRemove = buffer.size() + addAll.size() - capacity;
				for(int i = 0; i < numberToRemove; i++){
					overflow.add(buffer.get(0));
					buffer.remove(0);
				}
				//now there is enough space to add addAll to the buffer:
				assert(buffer.size() + addAll.size() <= capacity);
				buffer.addAll(addAll);
				
			} else {
				overflow.addAll(addAll);
			}
			
			return false;
		}
	}
	
	public boolean addAllIsEmpty(){
		return addAll.isEmpty();
	}
	
	public void addToBuffer() {
		if (allowDuplicates || !buffer.contains(add)) {
			buffer.add(add);
		}
	}
	
	private void addToBuffer(Item i){
		if (allowDuplicates || !buffer.contains(i)) {
			buffer.add(i);
		}
	}
	
	public void addAllToBuffer() {
		if(addAll != null){
			for(int i = 0; i < addAll.size(); i++){
				addToBuffer(addAll.get(i));
			}
		}
	}
	
	public void getFromBuffer() {
		out = buffer.remove(0);
	}
	
	public void emptyBuffer(){
		if(buffer != null) {
			buffer.clear();
		}
	}

	// Do not edit this constructor.
	public BufferEager(boolean allowDuplicates, int capacity, boolean keepNewest) {
	    this.allowDuplicates = allowDuplicates;
	    this.capacity = capacity;
	    this.keepNewest = keepNewest;
	}
}

