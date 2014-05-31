package org.antran.ctm.api;

import java.time.LocalTime;

public class TalkDetail
{
    
    private final LocalTime start;
    private final String title;
    
    public TalkDetail(LocalTime start, String title)
    {
        this.start = start;
        this.title = title;
    }
    
    public LocalTime start()
    {
        return start;
    }
    
    public String title()
    {
        return title;
    }
    
}
