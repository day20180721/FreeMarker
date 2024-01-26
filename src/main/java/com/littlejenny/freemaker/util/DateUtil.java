package com.littlejenny.freemaker.util;
/**
 * @author 王洪棟 - Lin
 * @created date 2023/10/24
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
    private static DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDateTime getDateTimeFromString(String date) {
        return LocalDateTime.parse(date, dateTimeformatter);
    }
    public static String getStringFromDateTime(LocalDateTime date) {
        return dateTimeformatter.format(date);
    }
    public static LocalDate getDateFromString(String dateString) {
        return LocalDate.parse(dateString, dateformatter);
    }
    public static String getStringFromDate(LocalDate date) {
        return dateformatter.format(date);
    }

    public static LocalDate copy(LocalDate date){
        return getDateFromString(getStringFromDate(date));
    }
}

