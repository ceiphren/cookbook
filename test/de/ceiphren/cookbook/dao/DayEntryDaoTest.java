package de.ceiphren.cookbook.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.ceiphren.cookbook.AbstractTest;
import de.ceiphren.cookbook.model.DayEntry;

public class DayEntryDaoTest extends AbstractTest {

	@Test
	public void test() throws Exception {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date d1 = format.parse("2015-01-01");
		Date d2 = format.parse("2016-01-01");

		DayEntryDao dao = context.getComponentByExactClass(DayEntryDao.class);

		DayEntry entry1 = new DayEntry();
		entry1.setDate(d1);
		entry1.setText("entryText1");

		DayEntry afterCreate1 = dao.save(entry1);

		DayEntry entry2 = new DayEntry();
		entry2.setDate(d2);
		entry2.setText("entryText2");

		DayEntry afterCreate2 = dao.save(entry2);

		Assert.assertNotNull(afterCreate1.getRecordId());
		compare(entry1, afterCreate1, false);
		Assert.assertNotNull(afterCreate2.getRecordId());
		compare(entry2, afterCreate2, false);

		Date searchBegin = format.parse("2014-01-01");
		Date searchEnd = format.parse("2017-01-01");
		List<DayEntry> dayEntryList = dao.getByPeriod(searchBegin, searchEnd);

		compare(afterCreate1, dayEntryList.get(0), true);
		compare(afterCreate2, dayEntryList.get(1), true);

		dao.delete(afterCreate1);
		dao.delete(afterCreate2);
	}

	private void compare(DayEntry expected, DayEntry current, boolean compareRids) {
		
		if(compareRids){
			Assert.assertEquals(expected.getRecordId(), current.getRecordId());	
		}
		
		Assert.assertEquals(expected.getText(), current.getText());
		Assert.assertEquals(expected.getDate(), current.getDate());
	}

}
