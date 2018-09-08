package com.ebase.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date getStartDateByStr(String dateStr) {
        if (null != dateStr && !"".equals(dateStr)) {
            try {
                return simpleDateFormat.parse(dateStr + " 00:00:00");
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }

    public static Date getEndDateByStr(String dateStr) {
        if (null != dateStr && !"".equals(dateStr)) {
            try {
                return simpleDateFormat.parse(dateStr + " 23:59:59");
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }
}
