package com.anadolstudio.nasalibrary.presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter {
    public static final String FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String MONTH_DAY_YEAR = "MMMM dd, yyyy";

    public static String convertToNewFormat(String dateStr, String oldFormat, String newFormat) {
        SimpleDateFormat form = new SimpleDateFormat(oldFormat);
        Date date;

        try {
            date = form.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Convert error";
        }
        SimpleDateFormat postFormatter = new SimpleDateFormat(newFormat);
        return postFormatter.format(date);
    }
}
