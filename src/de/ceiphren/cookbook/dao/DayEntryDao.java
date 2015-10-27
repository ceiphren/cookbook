package de.ceiphren.cookbook.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import de.ceiphren.cookbook.model.DayEntry;
import de.ceiphren.cookbook.service.DBService;
import de.ceiphren_Inc.context.Needed;

public class DayEntryDao {

	@Needed
	public DBService dbService;

	public List<DayEntry> getByPeriod(Date begin, Date end) {

		String stringBegin = DaoJsonUtil.createDateString(begin);
		String stringEnd = DaoJsonUtil.createDateString(end);

		String query = "select from dayentry where date < '" + stringEnd + "' and date >= '" + stringBegin + "'";

		JsonArray e = dbService.executeQuery(query);
		List<DayEntry> result = new ArrayList<>();

		for (JsonElement element : e) {
			DayEntry r = DaoJsonUtil.fromJson(element, DayEntry.class);
			result.add(r);
		}
		return result;
	}

	public DayEntry save(DayEntry dayEntry) {

		String json = DaoJsonUtil.toJson(dayEntry);

		String query = null;

		if (dayEntry.getRecordId() != null && dayEntry.getRecordId().startsWith("#")) {
			query = "update dayentry content " + json + " return after @rid where @rid = '" + dayEntry.getRecordId()
					+ "';";
		} else {
			query = "insert into dayentry content " + json + ";";
		}

		JsonArray resultList = dbService.executeQuery(query);

		if (resultList.size() > 0) {
			return DaoJsonUtil.fromJson(resultList.get(0), DayEntry.class);
		} else {
			// TODO: hm. bad case. shouldn't happen
			return null;
		}
	}

	public void delete(DayEntry dayentry) {

		String query = "delete vertex dayentry where @rid = '" + dayentry.getRecordId() + "';";
		dbService.executeQuery(query);
	}
}
