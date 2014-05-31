package org.antran.ctm.api;

import java.time.LocalTime;

public interface ISession extends Iterable<ITalk>
{
    
    TalkDetail[] talkDetails();
    
    LocalTime endTime();
}
