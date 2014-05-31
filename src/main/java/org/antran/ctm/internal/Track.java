package org.antran.ctm.internal;

import java.time.LocalTime;

import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITrack;

public class Track implements ITrack
{
    private String id;
    private ISession morningSession;
    private ISession afternoonSession;
    
    private Track()
    {
    }
    
    public Track(String id, ISession morningSession, ISession afternoonSession)
    {
        this();
        this.id = id;
        this.morningSession = morningSession;
        this.afternoonSession = afternoonSession;
    }
    
    public int numberOfSessionWithTalks()
    {
        int result = 0;
        if (morningSession().hasTalks())
        {
            result++;
        }
        if (afternoonSession().hasTalks())
        {
            result++;
        }
        return result;
    }
    
    public String id()
    {
        return id;
    }
    
    public ISession afternoonSession()
    {
        return afternoonSession;
    }
    
    public ISession morningSession()
    {
        return morningSession;
    }
    
    public LocalTime networkingStartTime()
    {
        if (afternoonSession().endTime().isBefore(EARLY_NETWORKING_START_4_PM))
        {
            return EARLY_NETWORKING_START_4_PM;
        }
        return afternoonSession().endTime();
    }
}
