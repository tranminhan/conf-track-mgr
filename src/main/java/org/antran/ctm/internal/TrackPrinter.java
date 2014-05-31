package org.antran.ctm.internal;

import java.text.SimpleDateFormat;

import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITalkDetail;
import org.antran.ctm.api.ITrack;
import org.antran.ctm.api.ITrackPrinter;

public class TrackPrinter implements ITrackPrinter {

	public String print(ITrack track) {
		StringBuilder content = new StringBuilder();
		content.append("Track " + track.id() + ":");
		content.append("\n");

		ISession[] sessions = track.sessions();
		ISession morningSession = sessions[0];
		ISession afterternoonSession = sessions[1];

		printSession(content, morningSession);

		content.append(sdf.format(TimeUtils.lunchTime) + " " + "Lunch");
		printSession(content, afterternoonSession);

		content.append(sdf.format(afterternoonSession.endTime()) + " "
				+ "Networking Event");

		return content.toString();
	}

	private void printSession(StringBuilder content, ISession morningSession) {
		ITalkDetail[] talkDetails = morningSession.getTalkDetails();
		for (ITalkDetail talkDetail : talkDetails) {
			content.append(this.print(talkDetail));
			content.append("\n");
		}
	}

	static SimpleDateFormat sdf;
	static {
		sdf = new SimpleDateFormat("hh:mma");
	}

	public String print(ITalkDetail talkDetail) {
		StringBuilder content = new StringBuilder();
		content.append(sdf.format(talkDetail.start()) + " "
				+ talkDetail.title());
		return content.toString();
	}

}
