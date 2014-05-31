package org.antran.ctm.internal;

import static org.junit.Assert.*;

import org.antran.ctm.api.IConference;
import org.antran.ctm.api.IConferenceScheduler;
import org.antran.ctm.api.ITrack;
import org.junit.Ignore;
import org.junit.Test;

public class ConferenceScheduleTest {

	static final String[] NO_PROPOSAL = {};

	static final String[] TWO_PROPOSALS = {
			"Writing Fast Tests Against Enterprise Rails 60min",
			"Rails for Python Developers lightning" };

	// static final String[] THREE_TRACK_PROPOSALS = {
	// "Writing Fast Tests Against Enterprise Rails 60min",
	// "Rails for Python Developers lightning",
	// "Overdoing it in Python 45min", };

	@Test
	public void shouldCreateConfFromNoProposal() {
		// given
		IConferenceScheduler confScheduler = new NaiveConferenceScheduler();

		// when
		IConference conference = confScheduler.schedule(NO_PROPOSAL);

		// then
		assertNotNull(conference);
		assertEquals(0, conference.numberOfTracks());
		assertNotNull(conference.tracks());
	}

	static final String[] ONE_PROPOSAL = { "Rails for Python Developers lightning" };

	@Test
	public void shouldCreateConfFromOneProposal() {
		// given
		IConferenceScheduler confScheduler = new NaiveConferenceScheduler();

		// when
		IConference conference = confScheduler.schedule(ONE_PROPOSAL);

		// then
		assertNotNull(conference);
		assertEquals(1, conference.numberOfTracks());
		assertNotNull(conference.tracks());

		ITrack track = conference.track(0);
		assertNotNull(track);
		assertEquals(1, track.numberOfSessions());
	}

	static final String[] ONE_TRACK_TWO_SESSIONS_PROPOSALS = {
			"Writing Fast Tests Against Enterprise Rails 180min",
			"Rails for Python Developers lightning",
			"Overdoing it in Python 45min", };

	@Test
	public void shouldCreateConfWithOneTrackAndTwoSessions() {
		// given
		IConferenceScheduler confScheduler = new NaiveConferenceScheduler();

		// when
		IConference conference = confScheduler
				.schedule(ONE_TRACK_TWO_SESSIONS_PROPOSALS);

		// then
		assertNotNull(conference);
		assertEquals(1, conference.numberOfTracks());
		assertNotNull(conference.tracks());

		ITrack track = conference.track(0);
		assertNotNull(track);
		assertEquals(2, track.numberOfSessions());
	}
}
