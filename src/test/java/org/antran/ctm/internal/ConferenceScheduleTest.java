package org.antran.ctm.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.antran.ctm.api.IConference;
import org.antran.ctm.api.IConferenceScheduler;
import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITrack;
import org.junit.Before;
import org.junit.Test;

public class ConferenceScheduleTest
{
    
    static final String[] NO_PROPOSAL = {};
    
    IConferenceScheduler conferenceScheduler;
    
    @Before
    public void setup()
    {
        conferenceScheduler = new NaiveConferenceScheduler();
    }
    
    @Test
    public void shouldCreateConfFromNoProposal()
    {
        // when
        IConference conference = conferenceScheduler.schedule(NO_PROPOSAL);
        
        // then
        assertNotNull(conference);
        assertNotNull(conference.tracks());
        assertEquals(0, conference.tracks().length);
        
    }
    
    static final String[] ONE_PROPOSAL = { "Rails for Python Developers lightning" };
    
    @Test
    public void shouldCreateConfFromOneProposal()
    {
        // when
        IConference conference = conferenceScheduler.schedule(ONE_PROPOSAL);
        
        // then
        assertNotNull(conference);
        assertNotNull(conference.tracks());
        assertEquals(1, conference.tracks().length);
        
        ITrack track = conference.track(0);
        assertNotNull(track);
        assertEquals(1, track.numberOfSessionWithTalks());
    }
    
    static final String[] ONE_TRACK_ONE_SESSION_TWO_PROPOSALS = {
            "Writing Fast Tests Against Enterprise Rails 60min",
            "Rails for Python Developers lightning" };
    
    @Test
    public void shouldCreateConfFromTwoProposals()
    {
        // when
        IConference conference = conferenceScheduler.schedule(ONE_TRACK_ONE_SESSION_TWO_PROPOSALS);
        
        // then
        assertNotNull(conference);
        assertNotNull(conference.tracks());
        assertEquals(1, conference.tracks().length);
        
        ITrack track = conference.track(0);
        assertNotNull(track);
        assertEquals(1, track.numberOfSessionWithTalks());
        
        assertEquals(2, track.morningSession().talkDetails().length);
        assertEquals(0, track.afternoonSession().talkDetails().length);
    }
    
    static final String[] ONE_TRACK_TWO_SESSIONS_PROPOSALS = {
            "Writing Fast Tests Against Enterprise Rails 180min",
            "Rails for Python Developers lightning",
            "Overdoing it in Python 45min", };
    
    @Test
    public void shouldCreateConfWithOneTrackAndTwoSessions()
    {
        // when
        IConference conference = conferenceScheduler
                .schedule(ONE_TRACK_TWO_SESSIONS_PROPOSALS);
        
        // then
        assertNotNull(conference);
        assertNotNull(conference.tracks());
        assertEquals(1, conference.tracks().length);
        
        ITrack track = conference.track(0);
        assertNotNull(track);
        assertEquals(2, track.numberOfSessionWithTalks());
    }
    
    static final String[] TWO_TRACK_PROPOSALS = {
            // 3 hours
            "Writing Fast Tests Against Enterprise Rails 180min",
            "Rails for Python Developers lightning",
            // 3 hours 20 min
            "Overdoing it in Python 200min",
            // 3 hours
            "Overdoing it in Python 180min" };
    
    @Test
    public void shouldCreateConfWithTwoTracks()
    {
        // when
        IConference conference = conferenceScheduler.schedule(TWO_TRACK_PROPOSALS);
        
        // then
        assertNotNull(conference);
        assertNotNull(conference.tracks());
        assertEquals(2, conference.tracks().length);
        
        ITrack track = conference.track(0);
        assertNotNull(track);
        assertEquals(2, track.numberOfSessionWithTalks());
        
        track = conference.track(1);
        assertNotNull(track);
        assertEquals(1, track.numberOfSessionWithTalks());
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
        IConference conference = conferenceScheduler.schedule(SAMPLE_PROPOSALS);
        
        // then
        assertNotNull(conference);
        assertNotNull(conference.tracks());
        assertEquals(2, conference.tracks().length);
        
        ITrack track = conference.track(0);
        assertNotNull(track);
        assertEquals(2, track.numberOfSessionWithTalks());
        
        track = conference.track(1);
        assertNotNull(track);
        assertEquals(2, track.numberOfSessionWithTalks());
    }
    
    static final String[] TOO_LONG_PROPOSALS = {
            // 5 hours 1 min
            "Writing Fast Tests Against Enterprise Rails 301min"
    };
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenATalkIsBiggerThanASession()
    {
        conferenceScheduler.schedule(TOO_LONG_PROPOSALS);
    }
    
    static final String[] ONE_LONG_TALK_FOR_ATERNOON = {
            // 3 hours 20 min
            "Writing Fast Tests Against Enterprise Rails 200min",
            "Woah 30min"
    };
    
    @Test
    public void shouldScheduleLongTalkToTheAfternoonSession()
    {
        // when
        IConference conference = conferenceScheduler.schedule(ONE_LONG_TALK_FOR_ATERNOON);
        
        // then
        assertNotNull(conference);
        assertNotNull(conference.tracks());
        assertEquals(1, conference.tracks().length);
        
        ITrack track = conference.track(0);
        assertNotNull(track);
        assertEquals(2, track.numberOfSessionWithTalks());
        
        ISession morningSession = track.morningSession();
        assertEquals(1, morningSession.talkDetails().length);
        assertEquals("Woah 30min", morningSession.talkDetails()[0].title());
        
        ISession afternoonSession = track.afternoonSession();
        assertEquals(1, afternoonSession.talkDetails().length);
        assertEquals("Writing Fast Tests Against Enterprise Rails 200min", afternoonSession.talkDetails()[0].title());
    }
    
}
