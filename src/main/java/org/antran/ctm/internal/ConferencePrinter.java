package org.antran.ctm.internal;

import java.time.format.DateTimeFormatter;

import org.antran.ctm.api.IConference;
import org.antran.ctm.api.IConferencePrinter;
import org.antran.ctm.api.ISession;
import org.antran.ctm.api.ITrack;
import org.antran.ctm.api.TalkDetail;

public class ConferencePrinter implements IConferencePrinter
{
    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mma");
    
    public String print(ITrack track)
    {
        StringBuilder content = new StringBuilder();
        content.append("Track " + track.id() + ":");
        content.append("\n");
        
        ISession[] sessions = track.sessions();
        ISession morningSession = sessions[0];
        ISession afterternoonSession = sessions[1];
        
        printSession(content, morningSession);
        
        content.append(dateTimeFormatter.format(TimeUtils.LUNCH_TIME) + " " + "Lunch");
        printSession(content, afterternoonSession);
        
        content.append(dateTimeFormatter.format(afterternoonSession.endTime()) + " " + "Networking Event");
        
        return content.toString();
    }
    
    private void printSession(StringBuilder content, ISession morningSession)
    {
        TalkDetail[] talkDetails = morningSession.getTalkDetails();
        for (TalkDetail talkDetail : talkDetails)
        {
            content.append(this.print(talkDetail));
            content.append("\n");
        }
    }
    
    public String print(TalkDetail talkDetail)
    {
        StringBuilder content = new StringBuilder();
        content.append(dateTimeFormatter.format(talkDetail.start()) + " "
                + talkDetail.title());
        return content.toString();
    }
    
    public String print(IConference conference)
    {
        StringBuilder content = new StringBuilder();
        for (ITrack track : conference.tracks())
            content.append(this.print(track));
        return content.toString();
    }
    
}
