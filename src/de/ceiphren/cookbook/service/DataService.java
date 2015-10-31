package de.ceiphren.cookbook.service;

import java.util.Date;

import de.ceiphren.cookbook.dao.DayEntryDao;
import de.ceiphren.cookbook.dao.RecipeDao;
import de.ceiphren.cookbook.model.DayEntry;
import de.ceiphren.cookbook.model.Recipe;
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

	@Needed
	public DayEntryDao dayEntryDao;
	
	public void createTestData() {

		Recipe recipe = new Recipe();
		recipe.setName("Pizza");
		recipe.setDescription("Pizza mit Teig und Tomaten");
		recipe.setPortionsMin(1);
		recipe.setPortionsMax(2);
		recipe.setComment("war nix spektakuläres");

		recipe = recipeDao.save(recipe);
		
	
		DayEntry entry = new DayEntry();
		entry.setDate(new Date());
		entry.setText("content");
		
		dayEntryDao.save(entry);
	}

	public void setupConstraints() {

		// TODO: implement me
	}
}
