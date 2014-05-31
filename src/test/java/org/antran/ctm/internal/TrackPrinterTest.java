package org.antran.ctm.internal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;

import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITalkDetail;
import org.antran.ctm.api.ITrack;
import org.antran.ctm.api.ITrackPrinter;
import org.junit.Test;

public class TrackPrinterTest {
	static final String[] morningTalks = {
			"Writing Fast Tests Against Enterprise Rails 60min",
			"Rails for Python Developers lightning",
			"Overdoing it in Python 45min", };

	static final String[] afternoonTalks = {
			"Writing Fast Tests Against Enterprise Rails 180min",
			"Rails for Python Developers lightning",
			"Overdoing it in Python 45min", };

	@Test
	public void shouldPrintText() {
		// given
		ISession morningSession = new Session(TimeUtils.morningStart,
				TalkBuilder.from(morningTalks));

		ISession afternoonSession = new Session(TimeUtils.afternoonStart,
				TalkBuilder.from(afternoonTalks));

		ITrack track = new Track("1", new ISession[] { morningSession,
				afternoonSession });

		// when
		ITrackPrinter printer = new TrackPrinter();
		String text = printer.print(track);

		// then
		System.out.println(text);
		assertThat(text, containsString("Track 1:"));
		assertTrue(text
				.contains("09:00AM Writing Fast Tests Against Enterprise Rails 60min"));
		assertTrue(text.contains("Rails for Python Developers lightning"));
	}

	@Test
	public void shouldPrintTalkDetail() {
		ITalkDetail talkDetail = new TalkDetail(TimeUtils.morningStart,
				"a title");
		ITrackPrinter printer = new TrackPrinter();

		String detail = printer.print(talkDetail);

		System.out.println(detail);
		assertTrue(detail.contains("09:00AM a title"));
	}

}
