package org.antran.ctm.api;

public interface IConferencePrinter
{
    
    String print(IConference conference);
    
    String print(ITrack track);
    
    String print(ITalkDetail talkDetail);
    
}
