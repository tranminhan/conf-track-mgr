package org.antran.ctm.internal;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.LocaleUtils;
import org.junit.Test;

public class DateTimeTest
{
    
    @Test
    public void test() throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        Date date = sdf.parse("09:00 AM");
        System.out.println(date);
    }
    
    @Test
    public void test2() throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        Date date = sdf.parse("09:00 AM");
        System.out.println(date);
        date.setMinutes(date.getMinutes() + 120);
        System.out.println(date);
        
    }
    
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
