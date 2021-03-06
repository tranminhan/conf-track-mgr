package org.antran.ctm.internal;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITalk;
import org.antran.ctm.api.ITrack;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Before;
import org.junit.Test;

public class ConferenceScheduleTest_Internal
{
    static final String[] TWO_PROPOSALS = {
            "Writing Fast Tests Against Enterprise Rails 60min",
            "Rails for Python Developers 120min",
            "Rails for Python Developers 10min" };
    
    NaiveConferenceScheduler conferenceSchedule;
    private static final int MORNING_TIME_ALLOCATION = 60 * 3;
    
    @Before
    public void setup()
    {
        conferenceSchedule = new NaiveConferenceScheduler();
    }
    
    @Test
    public void shouldAssignToMorningSession()
    {
        // given
        List<ITalk> proposalTalks = Arrays.asList(TalkBuilder
                .from(TWO_PROPOSALS));
        
        // when
        ISession morningSession = conferenceSchedule.assignTalksToSession(
                proposalTalks, MORNING_TIME_ALLOCATION, ITrack.MORNING_START_9_AM);
        
        // then
        assertNotNull(morningSession);
        assertOneTalksLeft(proposalTalks);
    }
    
    private void assertOneTalksLeft(List<ITalk> proposalTalks)
    {
        boolean talkLeftFound = false;
        for (ITalk talk : proposalTalks)
        {
            if (talk != null)
            {
                talkLeftFound = true;
                System.out.println(ReflectionToStringBuilder.toString(talk));
                break;
            }
        }
        if (talkLeftFound == false)
        {
            fail("no talk left");
        }
    }
}
