package org.antran.ctm.internal;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antran.ctm.api.IConference;
import org.antran.ctm.api.IConferenceScheduler;
import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITalk;
import org.antran.ctm.api.ITrack;

public class NaiveConferenceScheduler implements IConferenceScheduler
{
    // from 9:00 AM to 12:00 PM
    private static final int MORNING_TIME_ALLOCATION = 60 * 3;
    
    // from 1:00 PM to 5:00 PM
    private static final int AFTERNOON_TIME_ALLOCATION = 60 * 4;
    
    public IConference schedule(String[] proposals)
    {
        IConference conference = null;
        if (proposals.length == 0)
        {
            return new Conference();
        }
        
        if (proposals.length > 0)
        {
            List<ITalk> proposalTalks = Arrays.asList(TalkBuilder.from(proposals));
            for (ITalk aTalk : proposalTalks)
            {
                if (aTalk.minutes() > MORNING_TIME_ALLOCATION
                        && aTalk.minutes() > AFTERNOON_TIME_ALLOCATION)
                {
                    throw new IllegalArgumentException("this talk is too long, title " + aTalk.title());
                }
            }
            
            List<ITrack> tracks = new ArrayList<ITrack>();
            
            int trackId = 1;
            while (moreTalksToAssign(proposalTalks))
            {
                ITrack track = assignTalksToTrack(trackId, proposalTalks);
                tracks.add(track);
                trackId++;
            }
            
            return new Conference(tracks);
        }
        return conference;
    }
    
    private boolean moreTalksToAssign(List<ITalk> proposalTalks)
    {
        for (ITalk talk : proposalTalks)
        {
            if (talk != null)
            {
                return true;
            }
        }
        return false;
    }
    
    private ITrack assignTalksToTrack(int trackId, List<ITalk> proposalTalks)
    {
        List<ISession> sessions = new ArrayList<ISession>();
        
        ISession morningSession = assignTalksToSession(proposalTalks, MORNING_TIME_ALLOCATION, ITrack.MORNING_START_9_AM);
        sessions.add(morningSession);
        
        ISession afternoonSession = assignTalksToSession(proposalTalks, AFTERNOON_TIME_ALLOCATION, ITrack.AFTERNOON_START_1_PM);
        sessions.add(afternoonSession);
        
        return new Track(Integer.toString(trackId), morningSession, afternoonSession);
    }
    
    ISession assignTalksToSession(List<ITalk> proposalTalks, int timeAllocationInMinutes, LocalTime sessionStartTime)
    {
        int remainingTimeInMinutes = timeAllocationInMinutes;
        List<ITalk> talks = new ArrayList<ITalk>();
        
        int index = 0;
        do
        {
            if (proposalTalks.get(index) != null
                    && remainingTimeInMinutes >= proposalTalks.get(index).minutes())
            {
                talks.add(proposalTalks.get(index));
                
                remainingTimeInMinutes -= proposalTalks.get(index).minutes();
                proposalTalks.set(index, null);
            }
            
            index++;
        }
        while (remainingTimeInMinutes > 0 && index < proposalTalks.size());
        
        return new Session(sessionStartTime, talks.toArray(new ITalk[0]));
    }
}
