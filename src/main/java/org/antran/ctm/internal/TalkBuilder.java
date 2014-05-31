package org.antran.ctm.internal;

import java.util.ArrayList;
import java.util.List;

import org.antran.ctm.api.ITalk;
import org.apache.commons.lang3.StringUtils;

public class TalkBuilder
{
    private static final int LIGHTNING_MINUTES = 5;
    private static final String DIGITS = "123456789";
    
    public static ITalk from(String text)
    {
        String cleanText = text.trim();
        String cleanTitle = cleanText.substring(0, cleanText.lastIndexOf(" "));
        if (StringUtils.containsAny(cleanTitle, DIGITS))
        {
            throw new IllegalArgumentException("talk title contains nnumber: "
                    + cleanTitle);
        }
        
        Integer minutes = null;
        String cleanTime = cleanText.substring(cleanText.lastIndexOf(" ") + 1);
        if (cleanTime.contains("min"))
        {
            cleanTime = cleanTime.substring(0, cleanTime.indexOf("min"));
            minutes = Integer.parseInt(cleanTime);
        }
        if (cleanTime.contains("lightning"))
        {
            minutes = LIGHTNING_MINUTES;
        }
        
        return new Talk(cleanText, minutes);
    }
    
    public static ITalk[] from(String[] proposals)
    {
        List<ITalk> proposalTalks = new ArrayList<ITalk>();
        for (String aProposal : proposals)
        {
            proposalTalks.add(from(aProposal));
        }
        return proposalTalks.toArray(new Talk[0]);
    }
    
}
