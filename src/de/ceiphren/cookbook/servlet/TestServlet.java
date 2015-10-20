package de.ceiphren.cookbook.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.ceiphren.cookbook.AppContext;
import de.ceiphren_Inc.context.exception.ContextLoaderException;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = -6367148215463060767L;

	private Map<String, Object> controllerMap = new HashMap<>();

	@Override
	public void init() throws ServletException {

		super.init();

		AppContext context = new AppContext();
		try {
			context.setup();

			// register the controller in a map.
			for (Object object : context._components) {
				JsonController annotation = object.getClass().getAnnotation(JsonController.class);

				if (annotation != null) {
					String value = annotation.value();
					controllerMap.put(value, object);
				}
			}

		} catch (ContextLoaderException e) {

			e.printStackTrace();
		}
	}

	private String getBody(HttpServletRequest request) throws IOException {

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		doPost(request, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String object = request.getParameter("object");
		String action = request.getParameter("action");

		String body = getBody(request);

		Map<String, String[]> parameterMap = request.getParameterMap();

		JsonObject parameterObject = new JsonObject();

		for (Entry<String, String[]> entry : parameterMap.entrySet()) {
			parameterObject.addProperty(entry.getKey(), entry.getValue()[0]);
		}

		JsonElement el = new JsonParser().parse(body);

		JsonObject obj = new JsonObject();
		obj.add("parameter", parameterObject);
		obj.add("data", el);

		Object controller = this.controllerMap.get(object);

		String result = null;

		try {
			Method method = controller.getClass().getDeclaredMethod(action, JsonObject.class);

			result = (String) method.invoke(controller, obj);
		} catch (Exception e) {
			e.printStackTrace();
			log("method call for action " + action + " and object " + object + " failed.");
		}

		response.getWriter().append(result);
	}

}
