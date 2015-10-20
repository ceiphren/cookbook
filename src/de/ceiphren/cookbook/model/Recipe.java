package de.ceiphren.cookbook.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe {

	/**
	 * unique technical ID
	 */
	@SerializedName("@rid")
	@Expose(serialize = false, deserialize = true)
	private String recordId;

	/**
	 * name of the recipe (like 'Pancakes', 'Pizza')
	 */
	@Expose(serialize = true, deserialize = true)
	private String name;

	/**
	 * description how to create the stuff
	 */
	@Expose(serialize = true, deserialize = true)
	private String description;

	@Expose(serialize = true, deserialize = true)
	private Integer portionsMin;

	@Expose(serialize = true, deserialize = true)
	private Integer portionsMax;

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordId() {
		return recordId;
	}

	private String comment;

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setPortionsMin(Integer portionsMin) {
		this.portionsMin = portionsMin;
	}

	public Integer getPortionsMin() {
		return portionsMin;
	}

	public void setPortionsMax(Integer portionsMax) {
		this.portionsMax = portionsMax;
	}

	public Integer getPortionsMax() {
		return portionsMax;
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
