package org.antran.ctm.api;

import java.util.List;

public interface IConference
{
    
    int numberOfTracks();
    
    ITrack[] tracks();
    
    ITrack track(int index);
    
}
