package de.ceiphren.cookbook;

import de.ceiphren.cookbook.controller.DayEntryController;
import de.ceiphren.cookbook.controller.IngredientController;
import de.ceiphren.cookbook.controller.RecipeController;
import de.ceiphren.cookbook.controller.TestDataController;
import de.ceiphren.cookbook.dao.DayEntryDao;
import de.ceiphren.cookbook.dao.IngredientDao;
import de.ceiphren.cookbook.dao.RecipeDao;
import de.ceiphren.cookbook.service.DBService;
import de.ceiphren.cookbook.service.DataService;
import de.ceiphren_Inc.context.ComponentList;
import de.ceiphren_Inc.context.exception.ContextLoaderException;

public class AppContext extends de.ceiphren_Inc.context.AppContext {

	public void setup() throws ContextLoaderException {
		ComponentList componentList = new ComponentList();

		componentList.add(RecipeController.class);
		componentList.add(IngredientController.class);
		componentList.add(DayEntryController.class);
		componentList.add(TestDataController.class);

		componentList.add(RecipeDao.class);
		componentList.add(IngredientDao.class);
		componentList.add(DayEntryDao.class);

		componentList.add(DataService.class);

		componentList.add(DBService.class);

		this.registerComponents(componentList);

		this.wireComponents();

		this.addAndWireComponent(this);
	}

}
