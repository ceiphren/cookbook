package de.ceiphren.cookbook.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import de.ceiphren.cookbook.model.Ingredient;
import de.ceiphren.cookbook.model.Recipe;
import de.ceiphren.cookbook.service.DBService;
import de.ceiphren_Inc.context.Needed;

public class IngredientDao {

	private static final Log LOG = LogFactory.getLog(IngredientDao.class);

	@Needed
	public DBService dbService;

	private static final Type TYPE = new TypeToken<List<Ingredient>>() {
	}.getType();

	public List<Ingredient> saveAll(List<Ingredient> ingredientList) {

		String query = "begin\n";

		String returns = "";
		int counter = 0;
		for (Ingredient i : ingredientList) {

			if (i.getRecipeId() == null) {
				LOG.error("FATAL BUMBSERROR: ZE INGREDIENT CANNOT HAVE NO RECIPEID");
			}

			if (i.getRecordId() != null && i.getRecordId().startsWith("#")) {
				query += "update edge content " + DaoJsonUtil.toJson(i) + " where @rid =" + i.getRecordId()
						+ "\n ";
				query += "let i" + counter + " = select from " + i.getRecordId() + "\n ";
			} else {
				query += "let i" + counter + " = insert into ingredient content " + DaoJsonUtil.toJson(i) + "\n ";
				query += "create edge has from " + i.getRecipeId() + " to $i" + counter + "\n ";
			}

			if (counter > 0) {
				returns += (",$i" + counter);
			} else {
				returns += ("$i" + counter);
			}

			counter++;
		}

		query += "commit\n ";
		query += "return [ " + returns + " ]";

		JsonArray resultList = dbService.executeBatch(query);

		if (resultList.size() > 0) {

			List<Ingredient> list = DaoJsonUtil.fromJsonList(resultList, TYPE);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setRecipeId(ingredientList.get(i).getRecipeId());
			}

			return list;
		} else {
			return new ArrayList<>();
		}
	}

	public int deleteAll(Collection<Ingredient> ingredientList) {

		String query = "delete vertex ingredient where @rid in [ ";
		boolean first = true;
		for (Ingredient i : ingredientList) {

			if (!first) {
				query += ",";
			} else {
				first = false;
			}

			query += i.getRecordId();
		}
		query += "]";

		JsonArray resultList = dbService.executeQuery(query);
		int countOfDeletedObjects = resultList.get(0).getAsJsonObject().get("value").getAsInt();

		return countOfDeletedObjects;
	}

	public int deleteAll() {

		String query = "delete vertex ingredient";

		JsonArray resultList = dbService.executeQuery(query);
		int countOfDeletedObjects = resultList.get(0).getAsJsonObject().get("value").getAsInt();

		return countOfDeletedObjects;
	}

	public List<Ingredient> getByRecipeId(String recipeId) {

		String query = "select from ( traverse out('has') from " + recipeId + " ) where @class='ingredient'";

		JsonArray resultList = dbService.executeQuery(query);

		if (resultList.size() > 0) {

			List<Ingredient> list = DaoJsonUtil.fromJsonList(resultList, TYPE);
			for (Ingredient i : list) {
				i.setRecipeId(recipeId);
			}
			return list;
		} else {
			return new ArrayList<>();
		}
	}

	public Ingredient getByRecordId(String id) {
		String query = "traverse in('has') from " + id;

		JsonArray resultList = dbService.executeQuery(query);

		// expected 2 results
		Ingredient i = DaoJsonUtil.fromJson(resultList.get(0), Ingredient.class);
		Recipe r = DaoJsonUtil.fromJson(resultList.get(1), Recipe.class);
		i.setRecipeId(r.getRecordId());

		return i;
	}

}
