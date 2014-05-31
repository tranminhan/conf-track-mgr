package org.antran.ctm.internal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	static Date morningStart;
	static Date afternoonStart;
	static Date lunchTime;

	static {
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		try {
			morningStart = sdf.parse("09:00 AM");
			afternoonStart = sdf.parse("01:00 PM");
			lunchTime = sdf.parse("12:00 PM");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
