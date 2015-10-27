package de.ceiphren.cookbook.model;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DayEntry {

	/**
	 * unique technical ID
	 */
	@SerializedName("@rid")
	@Expose(serialize = false, deserialize = true)
	private String recordId;

	@Expose(serialize = true, deserialize = true)
	private Date date;

	@Expose(serialize = true, deserialize = true)
	private String text;

	@Expose(serialize = false, deserialize = false)
	private String recipeId;

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

	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeId() {
		return recipeId;
	}
}
