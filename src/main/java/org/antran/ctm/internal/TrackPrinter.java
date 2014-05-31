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
		for (ISession aSession : sessions) {
			ITalkDetail[] talkDetails = aSession.getTalkDetails();
			for (ITalkDetail talkDetail : talkDetails) {
				content.append(this.print(talkDetail));
				content.append("\n");
			}
		}
		return content.toString();
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
