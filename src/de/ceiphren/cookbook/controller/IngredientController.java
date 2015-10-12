package de.ceiphren.cookbook.controller;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import de.ceiphren.cookbook.dao.IngredientDao;
import de.ceiphren.cookbook.model.Ingredient;
import de.ceiphren.cookbook.servlet.JsonController;
import de.ceiphren_Inc.context.Needed;

@JsonController("ingredient")
public class IngredientController {

	@Needed
	public IngredientDao ingredientDao;

	public String saveList(JsonObject object) {

		String jsonList = object.get("data").getAsString();

		Type type = new TypeToken<List<Ingredient>>() {
		}.getType();

		List<Ingredient> ingredientList = JsonUtil.fromJsonList(jsonList, type);

		ingredientDao.saveAll(ingredientList);

		String result = JsonUtil.toJson(ingredientList);

		return result;
	}

	public String getList(JsonObject object) {

		JsonObject json = object.get("parameter").getAsJsonObject();

		String recipeName = json.get("recipeName").getAsString();

		List<Ingredient> list = ingredientDao.getByRecipeName(recipeName);

		return JsonUtil.toJson(list);
	}
}
