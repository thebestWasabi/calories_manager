package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MealTo {

    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean excess;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getFormattedDateTime() {
        return dateTime.format(FORMATTER);
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return String.format("MealTo {dateTime=%s, description='%s', calories=%d, excess=%s}",
                dateTime, description, calories, excess);
    }
}
