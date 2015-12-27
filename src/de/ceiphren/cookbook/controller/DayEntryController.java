package de.ceiphren.cookbook.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.ceiphren.cookbook.dao.DaoJsonUtil;
import de.ceiphren.cookbook.dao.DayEntryDao;
import de.ceiphren.cookbook.model.CalendarEntry;
import de.ceiphren.cookbook.model.DayEntry;
import de.ceiphren.cookbook.model.DayEntryWithRecipeName;
import de.ceiphren.cookbook.servlet.JsonController;
import de.ceiphren_Inc.context.Needed;

@JsonController("dayentry")
public class DayEntryController {

	@Needed
	public DayEntryDao dao;

	public String save(JsonObject object) {

		JsonElement data = object.get("data");

		DayEntry entry = WebJsonUtil.fromJson(data, DayEntry.class);

		dao.save(entry);

		String result = DaoJsonUtil.toJson(entry);

		return result;
	}

	/**
	 * get 3 months of dayEntries
	 * 
	 * @param object
	 * @return
	 * @throws ParseException
	 */
	public String getCalendarEntries(JsonObject object) throws ParseException {

		JsonObject json = object.get("parameter").getAsJsonObject();
		String StrBegin = json.get("begin").getAsString();
		String StrEnd = json.get("end").getAsString();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date begin = format.parse(StrBegin);
		Date end = format.parse(StrEnd);

		int[] monthIndicet = { begin.getMonth(), (begin.getMonth() + 1) % 12, (begin.getMonth() + 2) % 12 };

		List<DayEntryWithRecipeName> entryList = dao.getByPeriodWithRecipeData(begin, end);

		List<CalendarEntry> list = new ArrayList<>();

		Calendar previousDate = Calendar.getInstance();
		previousDate.setTime(begin);

		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(begin);
		currentDate.add(Calendar.MONTH, 1);

		Calendar nextDate = Calendar.getInstance();
		nextDate.setTime(begin);
		nextDate.add(Calendar.MONTH, 2);

		for (int date = 0; date < 31; date++) {

			CalendarEntry cEntry = new CalendarEntry();
			cEntry.setDate(date + 1);

			if (previousDate.getActualMaximum(Calendar.DAY_OF_MONTH) > date) {
				cEntry.setPreviousMonthDate(previousDate.getTime());
				if (previousDate.getActualMaximum(Calendar.DAY_OF_MONTH) - 1 > date) {
					previousDate.add(Calendar.DAY_OF_MONTH, 1);
				}
			}

			if (currentDate.getActualMaximum(Calendar.DAY_OF_MONTH) > date) {
				cEntry.setCurrentMonthDate(currentDate.getTime());
				if (currentDate.getActualMaximum(Calendar.DAY_OF_MONTH) - 1 > date) {
					currentDate.add(Calendar.DAY_OF_MONTH, 1);
				}
			}

			if (nextDate.getActualMaximum(Calendar.DAY_OF_MONTH) > date) {
				cEntry.setNextMonthDate(nextDate.getTime());

				if (nextDate.getActualMaximum(Calendar.DAY_OF_MONTH) - 1 > date) {
					nextDate.add(Calendar.DAY_OF_MONTH, 1);
				}
			}

			for (DayEntryWithRecipeName dEntry : entryList) {
				if (dEntry.getDate().getDate() - 1 == date) {

					if (monthIndicet[0] == dEntry.getDate().getMonth()) {
						cEntry.addPreviousMonthEntry(dEntry);
					} else if (monthIndicet[1] == dEntry.getDate().getMonth()) {
						cEntry.addCurrentMonthEntry(dEntry);
					} else if (monthIndicet[2] == dEntry.getDate().getMonth()) {
						cEntry.addNextMonthEntry(dEntry);
					}
				}
			}

			list.add(cEntry);
		}

		return WebJsonUtil.toJson(list);
	}
}
