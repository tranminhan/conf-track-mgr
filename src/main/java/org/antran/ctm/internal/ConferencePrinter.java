package org.antran.ctm.internal;

import java.time.format.DateTimeFormatter;

import org.antran.ctm.api.IConference;
import org.antran.ctm.api.IConferencePrinter;
import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITrack;
import org.antran.ctm.api.TalkDetail;

public class ConferencePrinter implements IConferencePrinter
{
    private static final String ENDLINE = System.getProperty("line.separator");
    
    public String print(ITrack track)
    {
        StringBuilder content = new StringBuilder();
        content.append("Track " + track.id() + ":");
        content.append(ENDLINE);
        
        printSession(content, track.morningSession());
        content.append(dateTimeFormatter.format(ITrack.LUNCH_TIME) + " " + "Lunch");
        content.append(ENDLINE);
        
        printSession(content, track.afternoonSession());
        content.append(dateTimeFormatter.format(track.networkingStartTime()) + " " + "Networking Event");
        content.append(ENDLINE);
        
        return content.toString();
    }
    
    private void printSession(StringBuilder content, ISession session)
    {
        TalkDetail[] talkDetails = session.talkDetails();
        for (TalkDetail talkDetail : talkDetails)
        {
            content.append(this.print(talkDetail));
            content.append(ENDLINE);
        }
    }
    
    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mma");
    
    public String print(TalkDetail talkDetail)
    {
        StringBuilder content = new StringBuilder();
        content.append(dateTimeFormatter.format(talkDetail.startTime()) + " " + talkDetail.title());
        return content.toString();
    }
    
    public String print(IConference conference)
    {
        StringBuilder content = new StringBuilder();
        for (ITrack track : conference.tracks())
        {
            content.append(this.print(track));
            content.append(ENDLINE);
        }
        return content.toString();
    }
    
}
