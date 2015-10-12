package de.ceiphren.cookbook.model;

import com.google.gson.annotations.SerializedName;

public class Recipe {

	/**
	 * unique technical ID
	 */
	@SerializedName("@rid")
	private String recordId;

	/**
	 * name of the recipe (like 'Pancakes', 'Pizza')
	 */
	private String name;

	/**
	 * description how to create the stuff
	 */
	private String description;

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {

		return "recipe + " + name;
	}
}
