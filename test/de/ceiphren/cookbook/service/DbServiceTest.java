package de.ceiphren.cookbook.service;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import de.ceiphren.cookbook.AbstractTest;

public class DbServiceTest extends AbstractTest {

	@Test
	public void testRollback() {

		Gson g = new Gson();
		
		JsonObject o = g.fromJson("{hans : 'peter'}", JsonObject.class);
		System.out.println(o);
	}
}
