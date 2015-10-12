package de.ceiphren.cookbook.servlet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JsonController {

	/**
	 * the identifier the annotated controller class should listen to.
	 * 
	 * @return
	 */
	String value();
}
