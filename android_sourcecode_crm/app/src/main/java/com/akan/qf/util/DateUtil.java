package com.akan.qf.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static final String FORMAT = "yyyy-MM-dd";

    public static Date getLastMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    public static String getFormatDate(String format, Date date) {
        return new SimpleDateFormat(format, Locale.CHINA).format(date);
    }

}
