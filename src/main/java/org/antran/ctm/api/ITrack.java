package org.antran.ctm.api;

import java.time.LocalTime;

public interface ITrack
{
    public static final LocalTime LUNCH_TIME = LocalTime.of(12, 0);
    public static final LocalTime EARLY_NETWORKING_START = LocalTime.of(16, 0);
    
    int numberOfSessions();
    
    String id();
    
    LocalTime networkingStartTime();
    
    ISession afternoonSession();
    
    ISession morningSession();
    
}
