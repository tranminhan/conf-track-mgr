package org.antran.ctm.internal;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateTimeTest {

	@Test
	public void test() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		Date date = sdf.parse("09:00 AM");
		System.out.println(date);
	}
	
	@Test
	public void test2() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		Date date = sdf.parse("09:00 AM");
		System.out.println(date);
		date.setMinutes(date.getMinutes() + 120);
		System.out.println(date);
	}

}
