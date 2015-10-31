package de.ceiphren.cookbook.model;

/**
 * A day entry can have a association to a recipe OR a plain text, not both
 */
public class DayEntryWithRecipeName extends DayEntry {

	String recipeName;

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getRecipeName() {
		return recipeName;
	}

}
