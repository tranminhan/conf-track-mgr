package org.antran.ctm.internal;

import java.time.LocalTime;

import org.antran.ctm.api.ITalkDetail;

public class TalkDetail implements ITalkDetail {

	private LocalTime start;
	private String title;

	public TalkDetail(LocalTime start, String title) {
		this.start = start;
		this.title = title;
	}

	public LocalTime start() {
		return start;
	}

	public String title() {
		return title;
	}

}
