package de.ceiphren.cookbook.controller;

import com.google.gson.JsonObject;

import de.ceiphren.cookbook.dao.RecipeDao;
import de.ceiphren.cookbook.service.DBService;
import de.ceiphren.cookbook.servlet.JsonController;
import de.ceiphren_Inc.context.Needed;

/**
 * for Ext create the test data with
 * Ext.Ajax.request({url:'recipe?object=testdata&action=create'});
 */
@JsonController("testdata")
public class TestDataController {

	@Needed
	public RecipeDao recipeDao;

	@Needed
	public DBService dbService;

	public String create(JsonObject object) {


		return "yeeehar";
	}
}
