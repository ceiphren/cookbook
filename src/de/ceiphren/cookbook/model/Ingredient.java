package de.ceiphren.cookbook.model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

	@SerializedName("@rid")
	private String recordId;

	private String recipeId;

	private Integer amount;

	private String unit;

	/**
	 * examples: Salt, Pepper, Milk, etc.
	 */
	private String type;

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeId() {
		return recipeId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
