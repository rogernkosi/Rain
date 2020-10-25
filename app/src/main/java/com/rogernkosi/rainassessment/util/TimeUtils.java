package com.rogernkosi.rainassessment.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String dateTimeToHour(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        try {
            Date parseDate = dateFormat.parse(date);
            return hourFormat.format(parseDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "00:00";
        }
    }

    public static String dateTimeToHourStripMinutes(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
        try {
            Date parseDate = dateFormat.parse(date);
            return new StringBuilder(hourFormat.format(parseDate)).append(":00").toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return "00:00";
        }
    }

}
