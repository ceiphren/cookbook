package de.ceiphren.cookbook.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.JsonArray;

import de.ceiphren.cookbook.controller.JsonUtil;
import de.ceiphren.cookbook.model.Ingredient;
import de.ceiphren.cookbook.service.DBService;
import de.ceiphren_Inc.context.Needed;

public class IngredientDao {

	@Needed
	public DBService dbService;

	public void saveAll(Collection<Ingredient> ingredientList) {

		String query = "begin\n";

		String returns = "";
		int counter = 0;
		for (Ingredient i : ingredientList) {

			query += " let i" + counter + " = insert into ingredient content " + JsonUtil.toJson(i) + ";\n ";
			query += " create edge has from " + i.getRecipeId() + " to $i" + counter + ";\n ";

			if (counter > 0) {
				returns += (",$i" + counter++);
			} else {
				returns += ("$i" + counter++);
			}
		}

		query += "commit\n ";
		query += "return [ " + returns + " ]";

		JsonArray array = dbService.executeBatch(query);

		System.out.println(array);
	}

	public Ingredient save(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Ingredient> getByRecipeName(String recipeName) {
		String query = "select from recipe where name = '" + recipeName + "';";
		JsonArray resultList = dbService.executeQuery(query);

		if (resultList.size() > 0) {
			return JsonUtil.fromJsonList(resultList, Ingredient.class);
		} else {
			return new ArrayList<>();
		}
	}

	public Ingredient getByRecordId(String id) {
		// TODO
		return null;
	}

}
