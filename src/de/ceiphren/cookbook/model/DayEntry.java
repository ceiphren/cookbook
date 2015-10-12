package de.ceiphren.cookbook.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class DayEntry {

	/**
	 * unique technical ID
	 */
	@SerializedName("@rid")
	private String recordId;

	private Date date;

	private String text;

	private Long recipeId;

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public Long getRecipeId() {
		return recipeId;
	}

}
