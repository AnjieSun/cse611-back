package com.demo.demo.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

import java.util.Date;

//this is an utils that can used to converse data
public class DateUtils {
    public static final String STANDARD = "yyyy-MM-dd HH:mm:ss";

    //String -> Date
    public static Date string2Date(String strDate)
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD);
        DateTime dateTime = dateTimeFormatter.parseDateTime(strDate);
        return dateTime.toDate();
    }

    public static Date string2Date(String strDate,String format)
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = dateTimeFormatter.parseDateTime(strDate);
        return dateTime.toDate();
    }

    //Date -> String
    public static String date2String(Date date)
    {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString();
    }
}
