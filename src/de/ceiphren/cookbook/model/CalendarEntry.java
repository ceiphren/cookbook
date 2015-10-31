package de.ceiphren.cookbook.model;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class CalendarEntry {
	/**
	 * day index
	 */
	@Expose(serialize = true, deserialize = true)
	private int date;

	@Expose(serialize = true, deserialize = true)
	private String previousMonth = "";

	@Expose(serialize = true, deserialize = true)
	private Date previousMonthDate;

	@Expose(serialize = true, deserialize = true)
	private String currentMonth = "";

	@Expose(serialize = true, deserialize = true)
	private Date currentMonthDate;

	@Expose(serialize = true, deserialize = true)
	private String nextMonth = "";

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

	public String getPreviousMonth() {
		return previousMonth;
	}

	public void setPreviousMonth(String previousMonth) {
		this.previousMonth = previousMonth;
	}

	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	public String getNextMonth() {
		return nextMonth;
	}

	public void setNextMonth(String nextMonth) {
		this.nextMonth = nextMonth;
	}

}
