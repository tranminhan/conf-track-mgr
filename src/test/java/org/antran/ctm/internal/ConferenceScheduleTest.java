package org.antran.ctm.internal;

import static org.junit.Assert.*;

import org.antran.ctm.api.IConference;
import org.antran.ctm.api.IConferenceScheduler;
import org.antran.ctm.api.ITrack;
import org.antran.ctm.api.IConferencePrinter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ConferenceScheduleTest
{
    
    static final String[] NO_PROPOSAL = {};
    
    static final String[] TWO_PROPOSALS = {
            "Writing Fast Tests Against Enterprise Rails 60min",
            "Rails for Python Developers lightning" };
    
    IConferenceScheduler confScheduler;
    
    @Before
    public void setup()
    {
        confScheduler = new NaiveConferenceScheduler();
    }
    
    @Test
    public void shouldCreateConfFromNoProposal()
    {
        // when
        IConference conference = confScheduler.schedule(NO_PROPOSAL);
        
        // then
        assertNotNull(conference);
        assertEquals(0, conference.numberOfTracks());
        assertNotNull(conference.tracks());
    }
    
    static final String[] ONE_PROPOSAL = { "Rails for Python Developers lightning" };
    
    @Test
    public void shouldCreateConfFromOneProposal()
    {
        // when
        IConference conference = confScheduler.schedule(ONE_PROPOSAL);
        
        // then
        assertNotNull(conference);
        assertEquals(1, conference.numberOfTracks());
        assertNotNull(conference.tracks());
        
        ITrack track = conference.track(0);
        assertNotNull(track);
        assertEquals(1, track.numberOfSessions());
    }
    
    static final String[] ONE_TRACK_TWO_SESSIONS_PROPOSALS = {
            "Writing Fast Tests Against Enterprise Rails 180min",
            "Rails for Python Developers lightning",
            "Overdoing it in Python 45min", };
    
    @Test
    public void shouldCreateConfWithOneTrackAndTwoSessions()
    {
        // when
        IConference conference = confScheduler
                .schedule(ONE_TRACK_TWO_SESSIONS_PROPOSALS);
        
        // then
        assertNotNull(conference);
        assertEquals(1, conference.numberOfTracks());
        assertNotNull(conference.tracks());
        
        ITrack track = conference.track(0);
        assertNotNull(track);
        assertEquals(2, track.numberOfSessions());
    }
    
    static final String[] TWO_TRACK_PROPOSALS = {
            "Writing Fast Tests Against Enterprise Rails 180min",
            "Rails for Python Developers lightning",
            "Overdoing it in Python 200min", "Overdoing it in Python 180min", };
    
    @Test
    public void shouldCreateConfWithTwoTracks()
    {
        // when
        IConference conference = confScheduler.schedule(TWO_TRACK_PROPOSALS);
        
        // then
        assertNotNull(conference);
        assertEquals(2, conference.numberOfTracks());
        assertNotNull(conference.tracks());
        
        ITrack track = conference.track(0);
        assertNotNull(track);
        assertEquals(2, track.numberOfSessions());
        
        track = conference.track(1);
        assertNotNull(track);
        assertEquals(1, track.numberOfSessions());
    }
    
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
    
    @Test
    public void shouldCreateConfWithSampleProposals()
    {
        // when
        IConference conference = confScheduler.schedule(SAMPLE_PROPOSALS);
        
        // then
        assertNotNull(conference);
        assertEquals(2, conference.numberOfTracks());
        assertNotNull(conference.tracks());
        
        ITrack track = conference.track(0);
        assertNotNull(track);
        assertEquals(2, track.numberOfSessions());
        
        track = conference.track(1);
        assertNotNull(track);
        assertEquals(2, track.numberOfSessions());
        
        // print
        IConferencePrinter printer = new ConferencePrinter();
        String flyoutContent = printer.print(conference);
        System.out.println(flyoutContent);
    }
}
