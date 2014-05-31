package org.antran.ctm.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antran.ctm.api.IConference;
import org.antran.ctm.api.IConferenceScheduler;
import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITalk;
import org.antran.ctm.api.ITrack;

public class ConferenceScheduler implements IConferenceScheduler {

	private static final int AFTERNOON_TIME_ALLOCATION = 60 * 4;
	private static final int MORNING_ALLOCATION = 60 * 3;

	public IConference schedule(String[] proposals) {
		IConference conference = null;
		if (proposals.length == 0) {
			return new Conference();
		}
		if (proposals.length > 0) {
			List<ITalk> proposalTalks = Arrays.asList(TalkBuilder
					.from(proposals));

			List<ITrack> tracks = new ArrayList<ITrack>();

			int id = 0;
			while (moreTalksToAllocate(proposalTalks)) {
				ITrack track = assignTalksToTrack(id, proposalTalks);
				tracks.add(track);
				id++;
			}

			return new Conference(tracks);
		}
		return conference;
	}

	private boolean moreTalksToAllocate(List<ITalk> proposalTalks) {
		for (ITalk talk : proposalTalks) {
			if (talk != null) {
				return true;
			}
		}
		return false;
	}

	private ITrack assignTalksToTrack(int id, List<ITalk> proposalTalks) {
		List<ISession> sessions = new ArrayList<ISession>();

		ISession morningSession = assignMorningSession(proposalTalks);
		if (morningSession != null) {
			sessions.add(morningSession);
		}
		// TODO: add a session as Lunch

		ISession afternoonSession = assignAfternoonSession(proposalTalks);
		if (afternoonSession != null) {
			sessions.add(afternoonSession);
		}
		// TODO: check and add a session as NETWORKING

		ITrack track = new Track(Integer.toString(id),
				sessions.toArray(new ISession[0]));
		return track;
	}

	ISession assignAfternoonSession(List<ITalk> proposalTalks) {
		int timeAllocation = AFTERNOON_TIME_ALLOCATION;

		List<ITalk> talks = new ArrayList<ITalk>();

		int index = 0;
		do {
			if (proposalTalks.get(index) != null
					&& timeAllocation >= proposalTalks.get(index).minutes()) {
				talks.add(proposalTalks.get(index));
				timeAllocation -= proposalTalks.get(index).minutes();
				proposalTalks.set(index, null);
			}
			index++;
		} while (timeAllocation > 0 && index < proposalTalks.size());

		if (talks.isEmpty()) {
			return null;
		}

		return new Session(TimeUtils.afternoonStart,
				talks.toArray(new ITalk[0]));
	}

	ISession assignMorningSession(List<ITalk> proposalTalks) {
		int timeAllocation = MORNING_ALLOCATION;
		List<ITalk> talks = new ArrayList<ITalk>();

		int index = 0;
		do {
			if (proposalTalks.get(index) != null
					&& timeAllocation >= proposalTalks.get(index).minutes()) {
				talks.add(proposalTalks.get(index));
				timeAllocation -= proposalTalks.get(index).minutes();
				proposalTalks.set(index, null);
			}

			index++;
		} while (timeAllocation > 0 && index < proposalTalks.size());

		if (talks.isEmpty()) {
			return null;
		}

		return new Session(TimeUtils.morningStart, talks.toArray(new ITalk[0]));
	}
}
