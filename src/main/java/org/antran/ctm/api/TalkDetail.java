package org.antran.ctm.api;

import java.time.LocalTime;

public class TalkDetail
{
    
    private final LocalTime startTime;
    private final String title;
    
    public TalkDetail(LocalTime startTime, String title)
    {
        this.startTime = startTime;
        this.title = title;
    }
    
    public LocalTime startTime()
    {
        return startTime;
    }
    
    public String title()
    {
        return title;
    }
    
}
