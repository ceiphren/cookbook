package de.ceiphren.cookbook;

import org.junit.BeforeClass;

import de.ceiphren_Inc.context.exception.ContextLoaderException;

abstract public class AbstractTest {

	protected static AppContext context = null;

	@BeforeClass
	public static void setupContext() {
		try {

			context = new AppContext();
			context.setup();

		} catch (ContextLoaderException e) {

			e.printStackTrace();
		}
	}
}
