package de.ceiphren.cookbook.service;

import de.ceiphren.cookbook.dao.RecipeDao;
import de.ceiphren_Inc.context.Needed;

/**
 * performs some general tasks regarding data
 *
 */
public class DataService {

	@Needed
	public DBService dbService;

	@Needed
	public RecipeDao recipeDao;

	public void createTestData() {

		// TODO: implement me
	}

	public void setupConstraints() {
		String query = "create constraint on (recipe:Recipe) assert recipe.name is unique";
		dbService.executeQuery(query);
	}
}
