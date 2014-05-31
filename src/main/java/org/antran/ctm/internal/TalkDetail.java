package org.antran.ctm.internal;

import java.util.Date;

import org.antran.ctm.api.ITalkDetail;

public class TalkDetail implements ITalkDetail {

	private Date start;
	private String title;

	public TalkDetail(Date start, String title) {
		this.start = (Date) start.clone();
		this.title = title;
	}

	public Date start() {
		return start;
	}

	public String title() {
		return title;
	}

}
