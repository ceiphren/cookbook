package de.ceiphren.cookbook.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.ceiphren.cookbook.dao.DayEntryDao;
import de.ceiphren.cookbook.dao.DaoJsonUtil;
import de.ceiphren.cookbook.model.DayEntry;
import de.ceiphren.cookbook.servlet.JsonController;

@JsonController("dayEntry")
public class DayEntryController {


	public DayEntryDao dao;

	public String save(JsonObject object) {

		JsonElement data = object.get("data");

		DayEntry entry = DaoJsonUtil.fromJson(data.getAsString(), DayEntry.class);

		dao.save(entry);

		String result = DaoJsonUtil.toJson(entry);

		return result;
	}

}
