package org.antran.ctm.internal;

import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITrack;

public class Track implements ITrack
{
    
    private ISession[] sessions;
    private String id;
    
    private Track()
    {
    }
    
    public Track(String id, ISession[] sessions)
    {
        this();
        this.id = id;
        this.sessions = sessions;
    }
    
    public int numberOfSessions()
    {
        return sessions.length;
    }
    
    public String id()
    {
        return id;
    }
    
    public ISession[] sessions()
    {
        return sessions;
    }
}
