package org.antran.ctm.internal;

import static org.junit.Assert.*;

import org.antran.ctm.api.IConference;
import org.antran.ctm.api.IConferencePrinter;
import org.antran.ctm.api.IConferenceScheduler;
import org.antran.ctm.api.ITrack;
import org.junit.Before;
import org.junit.Test;

public class ConferenceScheduleTest_Sample
{
    static final String[] SAMPLE_PROPOSALS = {
            "Writing Fast Tests Against Enterprise Rails 60min",
            "Overdoing it in Python 45min",
            "Lua for the Masses 30min",
            "Ruby Errors from Mismatched Gem Versions 45min",
            "Common Ruby Errors 45min",
            "Rails for Python Developers lightning",
            "Communication over Distance 60min",
            "Accounting-Driven Development 45min",
            "Woah 30min",
            "Sit Down and Write 30min",
            "Pair Programming with Noise 45min",
            "Rails Magic 60min",
            "Ruby on Rails: Why We Should Move On 60min",
            "Clojure Ate Scala (on my project) 45min",
            "Programming in the Boondocks of Seattle 30min",
            "Ruby on Rails Legacy App Maintenance 60min",
            "A World Without HackerNews 30min",
            "User Interface CSS in Rails Apps 30min",
    };
    IConferencePrinter printer;
    IConferenceScheduler conferenceScheduler;
    
    @Before
    public void setup()
    {
        printer = new ConferencePrinter();
        conferenceScheduler = new NaiveConferenceScheduler();
    }
    
    @Test
    public void shouldCreateConfWithSampleProposals()
    {
        // when
        IConference conference = conferenceScheduler.schedule(SAMPLE_PROPOSALS);
        String flyoutContent = printer.print(conference);
        
        // then
        System.out.println(flyoutContent);
        assertTrue(flyoutContent.contains("Track 1:"));
        assertTrue(flyoutContent.contains("Track 2:"));
    }
    
}
