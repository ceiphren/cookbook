package de.ceiphren.cookbook.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import de.ceiphren.cookbook.dao.IngredientDao;
import de.ceiphren.cookbook.model.Ingredient;
import de.ceiphren.cookbook.servlet.JsonController;
import de.ceiphren_Inc.context.Needed;

@JsonController("ingredient")
public class IngredientController {

	private static Log LOG = LogFactory.getLog(IngredientController.class);

	@Needed
	public IngredientDao ingredientDao;

	public String saveList(JsonObject object) {

		JsonElement e = object.get("data");
		List<Ingredient> ingredientList = new ArrayList<>();
		if (e.isJsonArray()) {
			JsonArray jsonList = e.getAsJsonArray();

			Type type = new TypeToken<List<Ingredient>>() {
			}.getType();

			ingredientList = WebJsonUtil.fromJsonList(jsonList, type);
		} else {
			Ingredient i = WebJsonUtil.fromJson(e, Ingredient.class);
			ingredientList.add(i);
		}

		ingredientList = ingredientDao.saveAll(ingredientList);

		String result = WebJsonUtil.toJson(ingredientList);

		return result;
	}

	public String getList(JsonObject object) {

		JsonObject json = object.get("parameter").getAsJsonObject();

		String recipeId = json.get("recipeId").getAsString();

		List<Ingredient> list = ingredientDao.getByRecipeId(recipeId);

		return WebJsonUtil.toJson(list);
	}

	public String deleteList(JsonObject object) {

		JsonElement e = object.get("data");

		List<Ingredient> rIdsToDelete = new ArrayList<>();
		if (e.isJsonArray()) {
			for (JsonElement rIdE : e.getAsJsonArray()) {
				Ingredient i = WebJsonUtil.fromJson(rIdE, Ingredient.class);
				rIdsToDelete.add(i);
			}
		} else {

			Ingredient i = WebJsonUtil.fromJson(e, Ingredient.class);
			rIdsToDelete.add(i);
		}

		ingredientDao.deleteAll(rIdsToDelete);
		LOG.info(rIdsToDelete);

		return "";
	}
}
