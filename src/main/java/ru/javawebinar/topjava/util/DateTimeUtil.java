package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtil {

    private DateTimeUtil() {
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, T startTime, T endTime) {
        return value.compareTo(startTime) >= 0 && value.compareTo(endTime) < 0;
    }

    public static boolean isBetweenHalfOpen(LocalDateTime ldt, LocalDateTime startTime, LocalDateTime endTime) {
        return !ldt.isBefore(startTime) && ldt.isBefore(endTime);
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime == null ? "" : localDateTime.format(DATE_TIME_FORMATTER);
    }
}
