package de.ceiphren.cookbook.controller;

import java.util.Collection;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.ceiphren.cookbook.dao.RecipeDao;
import de.ceiphren.cookbook.model.Recipe;
import de.ceiphren.cookbook.model.RecipeComboEntry;
import de.ceiphren.cookbook.service.DBService;
import de.ceiphren.cookbook.servlet.JsonController;
import de.ceiphren_Inc.context.Needed;

/**
 * 
 * 
 * @author sascha
 *
 */
@JsonController("recipe")
public class RecipeController {

	@Needed
	public RecipeDao recipeDao;

	@Needed
	public DBService dbService;

	public String save(JsonObject object) {

		JsonElement json = object.get("data");

		Recipe recipe = JsonUtil.fromJson(json.getAsString(), Recipe.class);

		recipeDao.save(recipe);

		return JsonUtil.toJson(recipe);
	}

	public void delete(JsonObject object) {

	}

	public String getList(JsonObject object) {

		Collection<Recipe> list = recipeDao.get();

		return JsonUtil.toJson(list);
	}

	public String getComboValues(JsonObject object) {

		List<RecipeComboEntry> list = recipeDao.getMap();

		return JsonUtil.toJson(list);
	}
}
