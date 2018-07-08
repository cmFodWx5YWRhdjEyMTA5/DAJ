package com.tinnovat.app.daj.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Anjali on 08-07-2018.
 */

public class CommonUtils {

    public static CommonUtils getInstance() {
        return new CommonUtils();
    }

    public String getDateMonth(Calendar calendar) {
        SimpleDateFormat month_date = new SimpleDateFormat("dd\nMMM", Locale.ENGLISH);
        return month_date.format(calendar.getTime());
    }


    public String getDateMonthShrinked(Calendar calendar) {
        SimpleDateFormat month_date = new SimpleDateFormat("dd\nMMMM", Locale.ENGLISH);
        return month_date.format(calendar.getTime());
    }
}
