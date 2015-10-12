package de.ceiphren.cookbook.service;

import org.junit.Test;

import de.ceiphren.cookbook.AbstractTest;

public class DbServiceTest extends AbstractTest {

	
	@Test
	public void testRollback() {
		DBService service = context.getComponentByExactClass(DBService.class);
		
		service.executeQuery("begin");
	}
}
