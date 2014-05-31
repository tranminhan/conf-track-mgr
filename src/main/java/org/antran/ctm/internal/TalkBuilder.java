package org.antran.ctm.internal;

import java.util.ArrayList;
import java.util.List;

import org.antran.ctm.api.ITalk;

public class TalkBuilder {
	private static final int LIGHTNING_MINUTES = 5;

	public static ITalk from(String text) {
		String cleanText = text.trim();
		String cleanTitle = cleanText.substring(0, cleanText.lastIndexOf(" "));

		Integer minutes = null;
		String cleanTime = cleanText.substring(cleanText.lastIndexOf(" ") + 1);
		if (cleanTime.contains("min")) {
			cleanTime = cleanTime.substring(0, cleanTime.indexOf("min"));
			minutes = Integer.parseInt(cleanTime);
		}
		if (cleanTime.contains("lightning")) {
			minutes = LIGHTNING_MINUTES;
		}

		return new Talk(cleanTitle, minutes);
	}

	public static ITalk[] from(String[] proposals) {
		List<ITalk> proposalTalks = new ArrayList<ITalk>();
		for (String aProposal : proposals) {
			proposalTalks.add(from(aProposal));
		}
		return proposalTalks.toArray(new Talk[0]);
	}

}
