package org.antran.ctm.internal;

import java.util.ArrayList;
import java.util.List;

import org.antran.ctm.api.IConference;
import org.antran.ctm.api.ITrack;

public class Conference implements IConference
{
    
    private final List<ITrack> tracks = new ArrayList<ITrack>();
    
    public Conference()
    {
    }
    
    public Conference(ITrack track)
    {
        this.tracks.add(track);
    }
    
    public Conference(List<ITrack> tracks)
    {
        this.tracks.addAll(tracks);
    }
    
    public ITrack[] tracks()
    {
        return tracks.toArray(new ITrack[0]);
    }
    
    public ITrack track(int index)
    {
        return tracks.get(index);
    }
    
}
