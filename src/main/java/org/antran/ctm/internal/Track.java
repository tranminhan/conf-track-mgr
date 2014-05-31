package org.antran.ctm.internal;

import java.time.LocalTime;

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
        this.sessions = sessions.clone();
    }
    
    public int numberOfSessions()
    {
        return sessions.length;
    }
    
    public String id()
    {
        return id;
    }
    
    public ISession afternoonSession()
    {
        return sessions[1];
    }
    
    public ISession morningSession()
    {
        return sessions[0];
    }
    
    public LocalTime networkingStartTime()
    {
        if (afternoonSession().endTime().isBefore(EARLY_NETWORKING_START))
        {
            return EARLY_NETWORKING_START;
        }
        return afternoonSession().endTime();
    }
}
