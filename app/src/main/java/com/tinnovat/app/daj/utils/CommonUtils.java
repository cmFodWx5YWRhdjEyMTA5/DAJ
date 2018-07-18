package com.tinnovat.app.daj.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Rahul on 08-07-2018.
 */

public class CommonUtils {

    public static CommonUtils getInstance() {
        return new CommonUtils();
    }

    public String getDateMonth(Calendar calendar) {
        SimpleDateFormat month_date = new SimpleDateFormat("dd\nMMM", Locale.US);
        return month_date.format(calendar.getTime()).toUpperCase();
    }


    public String getMonthWithYear(Calendar calendar) {
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM yyyy", Locale.US);
        return month_date.format(calendar.getTime());
    }

    public String getDate(String inputDate, boolean isDay) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        Date dt1 = null;
        try {
            dt1 = format1.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format2;
        if (isDay)
            format2 = new SimpleDateFormat("dd", Locale.US);
        else
            format2 = new SimpleDateFormat("MMM", Locale.US);

        return format2.format(dt1);
    }
}
