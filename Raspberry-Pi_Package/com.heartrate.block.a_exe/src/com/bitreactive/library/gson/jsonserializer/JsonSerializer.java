package com.bitreactive.library.gson.jsonserializer;

import java.lang.reflect.Type;

import no.ntnu.item.arctis.runtime.Block;

import org.joda.time.DateTime;

import com.bitreactive.library.gson.DateTimeTypeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonSerializer<T> extends Block {
	GsonBuilder gsonb = null;
	Gson gson = null;

	public String serialize(T t) {
		
		if (gsonb == null) {
			logDebug("Creating GSON serializer");
			gsonb = new GsonBuilder();
			gsonb.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());
			gson = gsonb.create();			
		}
		
		Type tType = new TypeToken<T>() {}.getType();

		return gson.toJson(t, tType);
	}
}