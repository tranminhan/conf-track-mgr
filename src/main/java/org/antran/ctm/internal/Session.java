package org.antran.ctm.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITalk;
import org.antran.ctm.api.ITalkDetail;

public class Session implements ISession {

	final List<ITalk> talks;
	private Date startTime;

	public Session(Date startTime, ITalk[] proposalTalks) {
		this.startTime = (Date) startTime.clone();
		this.talks = Arrays.asList(proposalTalks);
	}

	public Iterator<ITalk> iterator() {
		return talks.iterator();
	}

	public ITalkDetail[] getTalkDetails() {
		List<ITalkDetail> talkDetails = new ArrayList<ITalkDetail>();

		Date talkStart = (Date) startTime.clone();
		for (ITalk talk : talks) {
			talkDetails.add(new TalkDetail((Date) talkStart.clone(), talk
					.title()));

			talkStart.setMinutes(talkStart.getMinutes() + talk.minutes());
		}
		return talkDetails.toArray(new ITalkDetail[0]);
	}

	public Date endTime() {
		Date talkStart = (Date) startTime.clone();
		for (ITalk talk : talks) {
			talkStart.setMinutes(talkStart.getMinutes() + talk.minutes());
		}
		return (Date) talkStart.clone();
	}

}
