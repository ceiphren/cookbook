package de.ceiphren.cookbook.model;

/**
 * Combo-Entry for the Recipe-Selection from a combobox
 */
public class RecipeComboEntry {
	private String recordId;

	private String name;

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
}
