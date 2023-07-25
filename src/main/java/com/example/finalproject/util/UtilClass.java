package com.example.finalproject.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class UtilClass {
    public static String dateFormat(LocalDateTime dateTime){
        if (dateTime == null){
            return null;
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    public static String timeForToday(LocalDateTime value) {
        LocalDateTime now = LocalDateTime.now();

        long betweenTime = ChronoUnit.MINUTES.between(value, now);
        if (betweenTime < 1) return "방금전";
        if (betweenTime < 60) {
            return betweenTime + "분전";
        }

        long betweenTimeHour = ChronoUnit.HOURS.between(value, now);
        if (betweenTimeHour < 24) {
            return betweenTimeHour + "시간전";
        }

        long betweenTimeDay = ChronoUnit.DAYS.between(value, now);
        if (betweenTimeDay < 365) {
            return betweenTimeDay + "일전";
        }

        return betweenTimeDay / 365 + "년전";
    }
}
