package org.antran.ctm.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antran.ctm.api.IConference;
import org.antran.ctm.api.ITrack;

public class Conference implements IConference {

	private List<ITrack> tracks = new ArrayList<ITrack>();

	public Conference() {
	}

	public Conference(ITrack track) {
		this.tracks.add(track);
	}

	public Conference(List<ITrack> tracks) {
		this.tracks.addAll(tracks);
	}

	public int numberOfTracks() {
		return tracks.size();
	}

	public List<ITrack> tracks() {
		return Collections.unmodifiableList(tracks);
	}

	public ITrack track(int index) {
		return tracks.get(index);
	}

}
