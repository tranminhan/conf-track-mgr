package org.antran.ctm.internal;

import org.antran.ctm.api.ITalk;
import org.apache.commons.lang3.StringUtils;

public class Talk implements ITalk {

	private static final String DIGITS = "123456789";
	private final String title;
	private final int minutes;

	Talk(String title, Integer minutes) {
		if (StringUtils.containsAny(title, DIGITS)) {
			throw new IllegalArgumentException("talk title contains nnumber: "
					+ title);
		}
		if (minutes == null) {
			throw new IllegalArgumentException("minutes is null");
		}
		this.title = title;
		this.minutes = minutes;
	}

	public String title() {
		return title;
	}

	public int minutes() {
		return minutes;
	}

}
