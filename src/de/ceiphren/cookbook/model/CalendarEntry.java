package de.ceiphren.cookbook.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;

public class CalendarEntry {
	/**
	 * day index
	 */
	@Expose(serialize = true, deserialize = true)
	private int date;

	@Expose(serialize = true, deserialize = true)
	private List<DayEntry> previousMonth = new ArrayList<>();

	@Expose(serialize = true, deserialize = true)
	private Date previousMonthDate;

	@Expose(serialize = true, deserialize = true)
	private List<DayEntry> currentMonth = new ArrayList<>();

	@Expose(serialize = true, deserialize = true)
	private Date currentMonthDate;

	@Expose(serialize = true, deserialize = true)
	private List<DayEntry> nextMonth = new ArrayList<>();

	@Expose(serialize = true, deserialize = true)
	private Date nextMonthDate;

	public Date getPreviousMonthDate() {
		return previousMonthDate;
	}

	public void setPreviousMonthDate(Date previousMonthDate) {
		this.previousMonthDate = previousMonthDate;
	}

	public Date getCurrentMonthDate() {
		return currentMonthDate;
	}

	public void setCurrentMonthDate(Date currentMonthDate) {
		this.currentMonthDate = currentMonthDate;
	}

	public Date getNextMonthDate() {
		return nextMonthDate;
	}

	public void setNextMonthDate(Date nextMonthDate) {
		this.nextMonthDate = nextMonthDate;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public void setCurrentMonth(List<DayEntry> currentMonth) {
		this.currentMonth = currentMonth;
	}

	public List<DayEntry> getCurrentMonth() {
		return currentMonth;
	}

	public void setNextMonth(List<DayEntry> nextMonth) {
		this.nextMonth = nextMonth;
	}

	public List<DayEntry> getNextMonth() {
		return nextMonth;
	}

	public void setPreviousMonth(List<DayEntry> previousMonth) {
		this.previousMonth = previousMonth;
	}

	public List<DayEntry> getPreviousMonth() {
		return previousMonth;
	}
	
	public void addCurrentMonthEntry(DayEntry entry){
		this.currentMonth.add(entry);
	}

	public void addNextMonthEntry(DayEntry entry){
		this.nextMonth.add(entry);
	}
	
	public void addPreviousMonthEntry(DayEntry entry){
		this.previousMonth.add(entry);
	}
}
