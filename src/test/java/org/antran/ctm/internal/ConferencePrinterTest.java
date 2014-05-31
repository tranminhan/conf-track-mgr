package org.antran.ctm.internal;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.antran.ctm.api.IConference;
import org.antran.ctm.api.IConferencePrinter;
import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITalk;
import org.antran.ctm.api.ITrack;
import org.antran.ctm.api.TalkDetail;
import org.junit.Before;
import org.junit.Test;

public class ConferencePrinterTest
{
    static final String[] morningTalks = {
            "Writing Fast Tests Against Enterprise Rails 60min",
            "Rails for Python Developers lightning",
            "Overdoing it in Python 45min", };
    
    static final String[] afternoonTalks = {
            "Writing Fast Tests Against Enterprise Rails 180min",
            "Rails for Python Developers lightning",
            "Overdoing it in Python 45min", };
    
    IConferencePrinter printer;
    
    @Before
    public void setup()
    {
        printer = new ConferencePrinter();
    }
    
    @Test
    public void shouldPrintTextForTrack()
    {
        // given
        ISession morningSession = new Session(TimeUtils.MORNING_START,
                TalkBuilder.from(morningTalks));
        
        ISession afternoonSession = new Session(TimeUtils.AFTERNOON_START,
                TalkBuilder.from(afternoonTalks));
        
        ITrack track = new Track("1", new ISession[] { morningSession,
                afternoonSession });
        
        // when
        String flyoutContent = printer.print(track);
        
        // then
        assertTrue(flyoutContent.contains("Track 1:"));
        assertTrue(flyoutContent.contains("09:00AM Writing Fast Tests Against Enterprise Rails 60min"));
        assertTrue(flyoutContent.contains("Rails for Python Developers lightning"));
        assertTrue(flyoutContent.contains("12:00PM Lunch"));
    }
    
    @Test
    public void shouldPrintTextForConference()
    {
        // given
        List<ITrack> tracks = Arrays.asList(new ITrack[] {
                new Track("1", new ISession[] {
                        new Session(TimeUtils.MORNING_START, new ITalk[] {
                                new Talk("talk 1", 10)
                        }),
                        new Session(TimeUtils.AFTERNOON_START, new ITalk[] {
                                new Talk("talk 2", 15)
                        })
                }),
                new Track("2", new ISession[] {
                        new Session(TimeUtils.MORNING_START, new ITalk[] {
                                new Talk("talk 3", 10)
                        }),
                        new Session(TimeUtils.AFTERNOON_START, new ITalk[] {
                                new Talk("talk 4", 15)
                        })
                })
        
        });
        IConference conference = new Conference(tracks);
        
        // when
        String flyoutContent = printer.print(conference);
        System.out.println(flyoutContent);
        
        // then
        assertTrue(flyoutContent.contains("Track 1:"));
        assertTrue(flyoutContent.contains("Track 2:"));
    }
    
    @Test
    public void shouldPrintTalkDetail()
    {
        // given
        TalkDetail talkDetail = new TalkDetail(TimeUtils.MORNING_START, "a title");
        
        // when
        String flyoutContent = printer.print(talkDetail);
        
        // then
        assertTrue(flyoutContent.contains("09:00AM a title"));
    }
    
}
