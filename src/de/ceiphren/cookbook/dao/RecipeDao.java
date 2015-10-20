package de.ceiphren.cookbook.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import de.ceiphren.cookbook.model.Recipe;
import de.ceiphren.cookbook.model.RecipeComboEntry;
import de.ceiphren.cookbook.service.DBService;
import de.ceiphren_Inc.context.Needed;

public class RecipeDao {

	@Needed
	public DBService dbService;

	public Collection<Recipe> get() {

		String query = "select from recipe";
		JsonArray e = dbService.executeQuery(query);
		List<Recipe> result = new ArrayList<Recipe>();
		
		for(JsonElement element: e){
			Recipe r = DaoJsonUtil.fromJson(element, Recipe.class);
			result.add(r);
		}
		return result;
	}

	public Recipe get(String name) {

		String query = "select from recipe where name = '" + name + "';";
		JsonArray resultList = dbService.executeQuery(query);

		if (resultList.size() > 0) {
			return DaoJsonUtil.fromJson(resultList.get(0), Recipe.class);
		} else {
			return null;
		}
	}

	public List<RecipeComboEntry> getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public Recipe save(Recipe recipe) {

		String json = DaoJsonUtil.toJson(recipe);

		String query = null;

		if (recipe.getRecordId() != null) {
			query = "update recipe content " + json + " return after @rid where @rid = '" + recipe.getRecordId() + "';";
		} else {
			query = "insert into recipe content " + json + ";";
		}

		JsonArray resultList = dbService.executeQuery(query);

		if (resultList.size() > 0) {
			return DaoJsonUtil.fromJson(resultList.get(0), Recipe.class);
		} else {
			return null;
		}
	}

	public void delete(Recipe recipe) {

		String query = "delete vertex recipe where @rid = '" + recipe.getRecordId() + "';";
		dbService.executeQuery(query);

	}

}
