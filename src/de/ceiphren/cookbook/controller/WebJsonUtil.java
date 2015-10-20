package de.ceiphren.cookbook.controller;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class WebJsonUtil {
	private static GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();
	private static Gson gson = builder.create();

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
