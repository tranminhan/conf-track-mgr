package org.antran.ctm.internal;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITalk;
import org.antran.ctm.api.ITalkDetail;

public class Session implements ISession {

	final List<ITalk> talks;
	private LocalTime startTime;

	public Session(LocalTime startTime, ITalk[] proposalTalks) {
		this.startTime = startTime;
		this.talks = Arrays.asList(proposalTalks);
	}

	public Iterator<ITalk> iterator() {
		return talks.iterator();
	}

	public ITalkDetail[] getTalkDetails() {
		List<ITalkDetail> talkDetails = new ArrayList<ITalkDetail>();

		LocalTime talkStart = startTime;
		for (ITalk talk : talks) {
			talkDetails.add(new TalkDetail(talkStart, talk.title()));

			talkStart = talkStart.plusMinutes(talk.minutes());
		}
		return talkDetails.toArray(new ITalkDetail[0]);
	}

	public LocalTime endTime() {
		LocalTime endTime = startTime;
		for (ITalk talk : talks) {
			endTime = endTime.plusMinutes(talk.minutes());
		}
		return endTime;
	}

}
