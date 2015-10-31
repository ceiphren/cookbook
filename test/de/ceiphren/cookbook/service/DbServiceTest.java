package de.ceiphren.cookbook.service;

import java.nio.charset.Charset;

import org.junit.Test;

import de.ceiphren.cookbook.AbstractTest;
import de.ceiphren.cookbook.dao.RecipeDao;
import de.ceiphren.cookbook.model.Recipe;

public class DbServiceTest extends AbstractTest {

	@Test
	public void testRollback() {

		System.out.println(Charset.defaultCharset().name());
		
		RecipeDao rd = context.getComponentByExactClass(RecipeDao.class);
		
		Recipe r = new Recipe();
		r.setName("äöüäöü");
		
		rd.save(r);
	}
}
