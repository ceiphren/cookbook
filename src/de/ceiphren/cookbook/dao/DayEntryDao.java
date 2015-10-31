package de.ceiphren.cookbook.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import de.ceiphren.cookbook.model.DayEntry;
import de.ceiphren.cookbook.model.DayEntryWithRecipeName;
import de.ceiphren.cookbook.service.DBService;
import de.ceiphren_Inc.context.Needed;

public class DayEntryDao {

	@Needed
	public DBService dbService;

	public List<DayEntryWithRecipeName> getByPeriodWithRecipeData(Date begin, Date end) {

		String stringBegin = DaoJsonUtil.createDateString(begin);
		String stringEnd = DaoJsonUtil.createDateString(end);

		String query = "select *, in('cooked') as recipeId, in('cooked').name as recipeName from dayentry where date < '" + stringEnd + "' and date >= '" + stringBegin + "'";

		JsonArray e = dbService.executeQuery(query);
		List<DayEntryWithRecipeName> result = new ArrayList<>();

		for (JsonElement element : e) {
			DayEntryWithRecipeName r = DaoJsonUtil.fromJson(element, DayEntryWithRecipeName.class);
			JsonElement recipeArray = element.getAsJsonObject().get("recipeId");
			
			if(recipeArray != null && recipeArray.isJsonArray())
			{
				JsonArray array = recipeArray.getAsJsonArray();
				if(array.size() > 0){
					r.setRecipeId(array.get(0).getAsString());
				}
			}
			
			JsonElement recipeName = element.getAsJsonObject().get("recipeName");
			
			if(recipeName != null && recipeName.isJsonArray())
			{
				JsonArray array = recipeName.getAsJsonArray();
				if(array.size() > 0){
					r.setRecipeName(array.get(0).getAsString());
				}
			}
			
			result.add(r);
		}
		return result;
	}

	public DayEntry save(DayEntry dayEntry) {

		String json = DaoJsonUtil.toJson(dayEntry);

		String query = "";

		if (dayEntry.getRecordId() != null && dayEntry.getRecordId().startsWith("#")) {
			if (dayEntry.getRecipeId() != null) {
				query += "delete edge cooked where in = " + dayEntry.getRecordId() + "\n";
			}

			query += "let entry = update dayentry content " + json + " return after @rid where @rid = '" + dayEntry.getRecordId()+";";

			if (dayEntry.getRecipeId() != null) {
				query += "create edge cooked from " + dayEntry.getRecipeId() + " to $entry\n ";
			}
		} else {
			query = "let entry = insert into dayentry content " + json + "\n";

			if (dayEntry.getRecipeId() != null) {
				query += "create edge cooked from " + dayEntry.getRecipeId() + " to $entry\n ";
			}
		}

		query += "return $entry";

		JsonArray resultList = dbService.executeBatch(query);

		if (resultList.size() > 0) {
			DayEntry entry = DaoJsonUtil.fromJson(resultList.get(0), DayEntry.class);
			entry.setRecipeId(dayEntry.getRecipeId());
			
			return entry;
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
