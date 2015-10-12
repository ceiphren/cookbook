package de.ceiphren.cookbook.model;

/**
 * Combo-Entry for the Recipe-Selection from a combobox
 */
public class RecipeComboEntry {
	private Long id;

	private String name;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
