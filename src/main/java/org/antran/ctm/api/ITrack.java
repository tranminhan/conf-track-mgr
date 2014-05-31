package org.antran.ctm.api;

import java.time.LocalTime;

public interface ITrack
{
    public static final LocalTime LUNCH_TIME_12_PM = LocalTime.of(12, 0);
    public static final LocalTime EARLY_NETWORKING_START_4_PM = LocalTime.of(16, 0);
    public static final LocalTime MORNING_START_9_AM = LocalTime.of(9, 0);
    public static final LocalTime AFTERNOON_START_1_PM = LocalTime.of(13, 0);
    
    int numberOfSessionWithTalks();
    
    String id();
    
    LocalTime networkingStartTime();
    
    ISession afternoonSession();
    
    ISession morningSession();
    
}
