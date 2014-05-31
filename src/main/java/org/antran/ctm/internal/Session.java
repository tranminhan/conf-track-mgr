package org.antran.ctm.internal;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITalk;
import org.antran.ctm.api.TalkDetail;

public class Session implements ISession
{
    
    private final List<ITalk> talks;
    private final LocalTime startTime;
    
    public Session(LocalTime startTime, ITalk[] proposalTalks)
    {
        this.startTime = startTime;
        this.talks = Arrays.asList(proposalTalks);
    }
    
    public Iterator<ITalk> iterator()
    {
        return talks.iterator();
    }
    
    public TalkDetail[] talkDetails()
    {
        List<TalkDetail> talkDetails = new ArrayList<TalkDetail>();
        
        LocalTime talkStart = startTime;
        for (ITalk talk : talks)
        {
            talkDetails.add(new TalkDetail(talkStart, talk.title()));
            
            talkStart = talkStart.plusMinutes(talk.minutes());
        }
        return talkDetails.toArray(new TalkDetail[0]);
    }
    
    public LocalTime endTime()
    {
        LocalTime endTime = startTime;
        for (ITalk talk : talks)
        {
            endTime = endTime.plusMinutes(talk.minutes());
        }
        return endTime;
    }
    
}
