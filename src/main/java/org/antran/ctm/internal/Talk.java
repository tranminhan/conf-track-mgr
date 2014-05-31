package org.antran.ctm.internal;

import org.antran.ctm.api.ITalk;

public class Talk implements ITalk
{
    
    private final String title;
    private final int minutes;
    
    Talk(String title, Integer minutes)
    {
        
        if (minutes == null)
        {
            throw new IllegalArgumentException("minutes is null");
        }
        this.title = title;
        this.minutes = minutes;
    }
    
    public String title()
    {
        return title;
    }
    
    public int minutes()
    {
        return minutes;
    }
    
}
