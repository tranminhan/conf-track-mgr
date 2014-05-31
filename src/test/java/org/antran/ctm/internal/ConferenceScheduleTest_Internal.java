package org.antran.ctm.internal;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITalk;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;

public class ConferenceScheduleTest_Internal {
	static final String[] TWO_PROPOSALS = {
			"Writing Fast Tests Against Enterprise Rails 60min",
			"Rails for Python Developers 120min",
			"Rails for Python Developers 10min" };

	ConferenceScheduler conferenceSchedule = new ConferenceScheduler();

	@Test
	public void shouldAssignToMorningSession() {
		List<ITalk> proposalTalks = Arrays.asList(TalkBuilder
				.from(TWO_PROPOSALS));

		ISession morningSession = conferenceSchedule
				.assignMorningSession(proposalTalks);

		assertNotNull(morningSession);
		assertOneTalksLeft(proposalTalks);
	}

	private void assertOneTalksLeft(List<ITalk> proposalTalks) {
		boolean talkLeftFound = false;
		for (ITalk talk : proposalTalks) {
			if (talk != null) {
				talkLeftFound = true;
				System.out.println(ReflectionToStringBuilder.toString(talk));
				break;
			}
		}
		if (talkLeftFound == false) {
			fail("no talk left");
		}
	}
}
