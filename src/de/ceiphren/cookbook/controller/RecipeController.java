package de.ceiphren.cookbook.controller;

import java.util.ArrayList;
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

@JsonController("recipe")
public class RecipeController {

	@Needed
	public RecipeDao recipeDao;

	@Needed
	public DBService dbService;
	
	public String load(JsonObject object) {

		JsonObject json = object.get("parameter").getAsJsonObject();
		JsonElement idEl = json.get("id");

		Recipe recipe = recipeDao.getByRId(idEl.getAsString());

		return WebJsonUtil.toJson(recipe);
	}

	public String save(JsonObject object) {

		JsonElement json = object.get("data");

		Recipe recipe = WebJsonUtil.fromJson(json, Recipe.class);

		recipe = recipeDao.save(recipe);

		return WebJsonUtil.toJson(recipe);
	}

	public void delete(JsonObject object) {

		JsonElement json = object.get("data");
	}

	public String getList(JsonObject object) {

		Collection<Recipe> list = recipeDao.get();

		return WebJsonUtil.toJson(list);
	}

	public String getComboValues(JsonObject object) {

		List<Recipe> list = recipeDao.get();

		List<RecipeComboEntry> comboList = new ArrayList<>();

		for (Recipe r : list) {

			RecipeComboEntry entry = new RecipeComboEntry();
			entry.setRecordId(r.getRecordId());
			entry.setName(r.getName());
			comboList.add(entry);
		}

		return WebJsonUtil.toJson(comboList);
	}
}
