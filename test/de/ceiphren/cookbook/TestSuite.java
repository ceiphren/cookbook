package de.ceiphren.cookbook;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.ceiphren.cookbook.dao.IngredientDaoTest;
import de.ceiphren.cookbook.dao.RecipeDaoTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	RecipeDaoTest.class,
	IngredientDaoTest.class
})
public class TestSuite {

}
