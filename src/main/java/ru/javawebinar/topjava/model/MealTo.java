package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo {

    private final Integer id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean excess;

    public MealTo(final Integer id, final LocalDateTime dateTime,
                  final String description, final int calories, final boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public MealTo(final LocalDateTime dateTime, final String description,
                  final int calories, final boolean excess) {
        this(null, dateTime, description, calories, excess);
    }

    @Override
    public String toString() {
        return String.format("MealTo {dateTime=%s, description='%s', calories=%d, excess=%s}",
                dateTime, description, calories, excess);
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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
}
