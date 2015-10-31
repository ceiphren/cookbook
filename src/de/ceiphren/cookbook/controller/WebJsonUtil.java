package de.ceiphren.cookbook.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class WebJsonUtil {
	private final static TypeAdapter<String> stringAdapter = new TypeAdapter<String>() {

		@Override
		public void write(JsonWriter writer, String value) throws IOException {
			writer.value(value);
		}

		@Override
		public String read(JsonReader in) throws IOException {
			if (in.peek() == JsonToken.NULL) {
				in.nextNull();
				return null;
			}

			String result = in.nextString();
			if ("".equals(result)) {
				return null;
			} else {
				return result;
			}
		}
	};

	private final static GsonBuilder builder = new GsonBuilder()
			.disableHtmlEscaping()
			.setDateFormat("EEE MMM dd yyyy HH:mm:ss")
			.registerTypeAdapter(String.class, stringAdapter);
	
	private final static Gson gson = builder.create();

	public static String toJson(Object content) {
		return gson.toJson(content);
	}

	public static <T> T fromJson(String content, Class<T> clazz) {
		return gson.fromJson(content, clazz);
	}
	
	public static <T> T fromJson(JsonElement content, Class<T> clazz) {
		return gson.fromJson(content, clazz);
	}

	public static <T> List<T> fromJsonList(JsonArray content, Type type) {

		return gson.fromJson(content, type);
	}
	
	public static JsonObject convertString(String content){
		return gson.fromJson(content, JsonObject.class);
	}
}
