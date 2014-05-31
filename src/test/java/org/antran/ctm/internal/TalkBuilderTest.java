package org.antran.ctm.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.antran.ctm.api.ITalk;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TalkBuilderTest
{
    
    @Test
    public void shouldBuildTalkFromTalkTextWithMinutes()
    {
        ITalk aTalk = TalkBuilder
                .from("Writing Fast Tests Against Enterprise Rails 60min");
        
        assertNotNull(aTalk);
        assertEquals("Writing Fast Tests Against Enterprise Rails 60min",
                aTalk.title());
        assertEquals(60, aTalk.minutes());
    }
    
    @Test
    public void shouldBuildTalkFromTalkTextWithLightning()
    {
        ITalk aTalk = TalkBuilder.from("Rails for Python Developers lightning");
        
        assertNotNull(aTalk);
        assertEquals("Rails for Python Developers lightning", aTalk.title());
        assertEquals(5, aTalk.minutes());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenTalkTitleContainsNumber()
    {
        TalkBuilder.from("I want to go 1st 10min");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenTalkContainsHours()
    {
        TalkBuilder.from("Lengthy introduction to Spring 1hour");
    }
    
    static final String[] TWO_PROPOSALS = {
            "Writing Fast Tests Against Enterprise Rails 60min",
            "Rails for Python Developers lightning" };
    
    @Test
    public void shouldBuildMultipleTalks()
    {
        ITalk[] proposals = TalkBuilder.from(TWO_PROPOSALS);
        
        assertNotNull(proposals);
        assertEquals(2, proposals.length);
    }
    
    @Test
    public void testString()
    {
        assertTrue(StringUtils.containsAny("I want to go 1st", "123"));
    }
}
