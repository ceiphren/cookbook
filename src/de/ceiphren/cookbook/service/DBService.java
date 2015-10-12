package de.ceiphren.cookbook.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.ceiphren.cookbook.controller.JsonUtil;

/**
 * handles connection and sessions to the database.
 * <P>
 * Can fire queries
 *
 */
public class DBService {

	private final static String URL_SQL = "http://localhost:2480/command/cookbook/sql";
	private final static String URL_BATCH = "http://localhost:2480/batch/cookbook/";
	private final static String USER = "root";
	private final static String PASSWORD = "63FA854597E41ABDA8FE9E1E5E920F83A3BC60DC789BE975DE749CE0263C41A9";

	private static HttpClient client = new DefaultHttpClient();

	/**
	 * execute a single sql query
	 */
	public JsonArray executeQuery(String query) {

		HttpPost post = new HttpPost(URL_SQL);
		BasicHttpEntity entity = new BasicHttpEntity();

		ByteArrayInputStream s = new ByteArrayInputStream(query.getBytes());
		post.addHeader("Accept-Encoding", "gzip,deflate");
		post.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials(USER, PASSWORD), "UTF-8", false));

		entity.setContent(s);
		entity.setContentLength(query.length());
		entity.setContentType("application/json");
		entity.setContentEncoding("charset=UTF-8");
		post.setEntity(entity);

		try {
			HttpResponse response = client.execute(post);

			InputStream s1 = response.getEntity().getContent();
			InputStreamReader isr = new InputStreamReader(s1);

			JsonElement element = JsonUtil.toJsonElement(isr);

			JsonArray result = element.getAsJsonObject().get("result").getAsJsonArray();
			return result;
		} catch (Exception e1) {

			e1.printStackTrace();
			return null;
		}
	}
	
	/**
	 * execute a batch of queries 
	 * @param batch
	 * @return
	 */
	public JsonArray executeBatch(String batch){
		
		HttpPost post = new HttpPost(URL_BATCH);
		BasicHttpEntity entity = new BasicHttpEntity();

		JsonObject operation = new JsonObject();
		operation.addProperty("type", "script");
		operation.addProperty("language", "sql");
		operation.addProperty("script", batch);
		JsonArray ops = new JsonArray();
		ops.add(operation);
		
		JsonObject o = new JsonObject();
		o.addProperty("transaction", false);
		o.add("operations", ops);
		
		String payload = o.toString();
		
		ByteArrayInputStream s = new ByteArrayInputStream(payload.getBytes());
		post.addHeader("Accept-Encoding", "gzip,deflate");
		post.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials(USER, PASSWORD), "UTF-8", false));

		entity.setContent(s);
		entity.setContentLength(payload.length());
		entity.setContentType("application/json");
		entity.setContentEncoding("charset=UTF-8");
		post.setEntity(entity);

		try {
			HttpResponse response = client.execute(post);

			InputStream s1 = response.getEntity().getContent();
			InputStreamReader isr = new InputStreamReader(s1);

			JsonElement element = JsonUtil.toJsonElement(isr);

			JsonArray result = element.getAsJsonObject().get("result").getAsJsonArray();
			return result;
		} catch (Exception e1) {

			e1.printStackTrace();
			return null;
		}
	}

}
