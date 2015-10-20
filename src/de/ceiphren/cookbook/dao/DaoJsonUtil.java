package de.ceiphren.cookbook.dao;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Allows static access to gson functionality
 * 
 */
public class DaoJsonUtil {

	/**
	 * rules for the json-serialization to and from the db:
	 * - don't write the recordId to the db
	 */
	private static GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
	
	
	private static Gson gson = builder.create();

	private static JsonParser parser = new JsonParser();

	public static <T> T fromJson(String content, Class<T> clazz) {
		return gson.fromJson(content, clazz);
	}

	public static <T> T fromJson(JsonElement content, Class<T> clazz) {
		return gson.fromJson(content, clazz);
	}

	public static String toJson(Object content) {
		return gson.toJson(content);
	}

	public static JsonElement toJsonElement(Reader json) {
		return parser.parse(json);
	}

	public static <T> List<T> fromJsonList(String content, Type type) {

		if (content.startsWith("{")) {
			// not a json list, just one object. convert it to a json-list
			content = "[" + content + "]";
		}

		return gson.fromJson(content, type);
	}

	public static <T> List<T> fromJsonList(JsonArray content, Type type) {

		return gson.fromJson(content, type);
	}
}
