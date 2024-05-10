package com.bochi.kito.timbernotejava.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTools {
    public static String convertMillisToDate(Long dateMillis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstString.DATE_MEDIUM_FORMAT, Locale.getDefault());
        return dateFormat.format(new Date(dateMillis));
    }

//    public static Long getDateReminderFromMillsNew(){
//
//    }

}
