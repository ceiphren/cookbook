package de.ceiphren.cookbook.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.ceiphren.cookbook.AbstractTest;
import de.ceiphren.cookbook.model.Ingredient;
import de.ceiphren.cookbook.model.Recipe;

public class IngredientDaoTest extends AbstractTest {

	@Test
	public void testSaveAndLoadForManyIngredients() {
		IngredientDao ingredientDao = context.getComponentByExactClass(IngredientDao.class);
		RecipeDao recipeDao = context.getComponentByExactClass(RecipeDao.class);

		Recipe recipe = new Recipe();
		recipe.setDescription("lorem ipsum");
		recipe.setName("Kartoffelpuffer");

		recipe = recipeDao.save(recipe);

		Assert.assertNotNull(recipe.getRecordId());

		List<Ingredient> ingredientList = new ArrayList<>();

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setAmount(200);
		ingredient1.setUnit("ml");
		ingredient1.setType("milk");
		ingredient1.setRecipeId(recipe.getRecordId());
		ingredientList.add(ingredient1);

		Ingredient ingredient2 = new Ingredient();
		ingredient2.setAmount(300);
		ingredient2.setUnit("g");
		ingredient2.setType("sugar");
		ingredient2.setRecipeId(recipe.getRecordId());
		ingredientList.add(ingredient2);

		ingredientDao.saveAll(ingredientList);

		Assert.assertEquals(ingredient1.getRecipeId(), recipe.getRecordId());
		Assert.assertEquals(ingredient2.getRecipeId(), recipe.getRecordId());
	}
}
