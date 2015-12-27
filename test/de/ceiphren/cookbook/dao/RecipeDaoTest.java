package de.ceiphren.cookbook.dao;

import org.junit.Assert;
import org.junit.Test;

import de.ceiphren.cookbook.AbstractTest;
import de.ceiphren.cookbook.model.Recipe;

public class RecipeDaoTest extends AbstractTest {

	@Test
	public void testCRUD() {
		RecipeDao recipeDao = context.getComponentByExactClass(RecipeDao.class);

		Recipe unsavedRecipe = new Recipe();
		unsavedRecipe.setName("burger");
		unsavedRecipe.setDescription("lorem ipsum dolor sit burger");

		Assert.assertNull(unsavedRecipe.getRecordId());

		// create
		Recipe savedRecipe = recipeDao.save(unsavedRecipe);
		Assert.assertNotNull(savedRecipe.getRecordId());
		Assert.assertEquals(unsavedRecipe.getName(), savedRecipe.getName());
		Assert.assertEquals(unsavedRecipe.getDescription(), savedRecipe.getDescription());

		// read
		Recipe readedRecipe = recipeDao.getByName("burger");
		Assert.assertEquals(savedRecipe.getRecordId(), readedRecipe.getRecordId());
		Assert.assertEquals(savedRecipe.getName(), readedRecipe.getName());
		Assert.assertEquals(savedRecipe.getDescription(), readedRecipe.getDescription());

		// update
		savedRecipe.setDescription("Hanna hat das bier gestohlen.");
		Recipe updatedRecipe = recipeDao.save(savedRecipe);
		Assert.assertEquals(savedRecipe.getRecordId(), updatedRecipe.getRecordId());
		Assert.assertEquals(savedRecipe.getDescription(), updatedRecipe.getDescription());

		// //delete
		recipeDao.delete(updatedRecipe);
	}

}
