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

		List<Ingredient> list = new ArrayList<>();

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setAmount(200);
		ingredient1.setUnit("ml");
		ingredient1.setType("milk");
		ingredient1.setRecipeId(recipe.getRecordId());
		list.add(ingredient1);

		Ingredient ingredient2 = new Ingredient();
		ingredient2.setAmount(300);
		ingredient2.setUnit("g");
		ingredient2.setType("sugar");
		ingredient2.setRecipeId(recipe.getRecordId());
		list.add(ingredient2);

		List<Ingredient> list2 = ingredientDao.saveAll(list);

		compareObjects(list, list2, false);
		
		List<Ingredient> list3 = ingredientDao.getByRecipeId(recipe.getRecordId());
		
		compareObjects(list2, list3, true);
		
		int countOfDeleted = ingredientDao.deleteAll(list3);
		
		recipeDao.delete(recipe);
		Assert.assertEquals(2, countOfDeleted );
	}

	private void compareObjects(List<Ingredient> expectedList, List<Ingredient> newList, boolean compareRecordIds) {

		Assert.assertEquals(expectedList.size(), newList.size());

		for (int i = 0; i < expectedList.size(); i++) {

			Ingredient expected = expectedList.get(i);
			Ingredient newI = newList.get(i);


			Assert.assertNotNull(newI.getRecordId());

			//TODO: we have to add the recordID somehow to the ingredient
			
			if(compareRecordIds){
				Assert.assertEquals(expected.getRecordId(), newI.getRecordId());
			}
			Assert.assertEquals(expected.getRecipeId(), newI.getRecipeId());
			Assert.assertEquals(expected.getAmount(), newI.getAmount());
			Assert.assertEquals(expected.getType(), newI.getType());
			Assert.assertEquals(expected.getUnit(), newI.getUnit());
		}
	}
}
