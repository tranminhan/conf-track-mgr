package org.antran.ctm.api;

public interface ITrack
{
    
    int numberOfSessions();
    
    String id();
    
    ISession[] sessions();
    
}
