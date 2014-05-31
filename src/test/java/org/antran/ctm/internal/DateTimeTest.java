package org.antran.ctm.internal;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class DateTimeTest
{
    
    @Test
    public void testNewDateTime() throws ParseException
    {
        LocalTime time = LocalTime.now();
        System.out.println(time);
        
        time = LocalTime.of(9, 0);
        System.out.println(time);
        
        time = LocalTime.of(16, 0);
        System.out.println(time);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");
        System.out.println(formatter.format(time));
    }
    
}
