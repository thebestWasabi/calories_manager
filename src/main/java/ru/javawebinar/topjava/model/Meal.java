package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal extends AbstractBaseEntity {

    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private Integer userId;

    public Meal(final Integer id, final LocalDateTime dateTime,
                final String description, final int calories, final Integer userId) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.userId = userId;
    }

    public Meal(final LocalDateTime dateTime, final String description,
                final int calories, final Integer userId) {
        this(null, dateTime, description, calories, userId);
    }

    @Override
    public String toString() {
        return String.format("Meal {id=%d, dateTime=%s, description='%s', calories=%d}",
                id, dateTime, description, calories);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }
}
