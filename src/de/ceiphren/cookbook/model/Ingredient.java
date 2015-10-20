package de.ceiphren.cookbook.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient {

	@SerializedName("@rid")
	@Expose(serialize = false, deserialize = true)
	private String recordId;

	@Expose(serialize = true, deserialize = true)
	private Integer amount;

	@Expose(serialize = true, deserialize = true)
	private String unit;

	@Expose(serialize = false, deserialize = false)
	private String recipeId;

	/**
	 * examples: Salt, Pepper, Milk, etc.
	 */
	@Expose(serialize = true, deserialize = true)
	private String type;

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordId() {
		return recordId;
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

	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeId() {
		return recipeId;
	}

	@Override
	public String toString() {
		return "Ingredient [recordId=" + recordId + ", amount=" + amount + ", unit=" + unit + ", recipeId=" + recipeId
				+ ", type=" + type + "]";
	}
}
