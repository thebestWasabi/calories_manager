package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {

    private Integer id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;

    public Meal(final Integer id, final LocalDateTime dateTime,
                final String description, final int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal(final LocalDateTime dateTime,
                final String description, final int calories) {
        this(null, dateTime, description, calories);
    }

    @Override
    public String toString() {
        return String.format("Meal {id=%d, dateTime=%s, description='%s', calories=%d}",
                id, dateTime, description, calories);
    }

    public boolean isNew() {
        return id == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
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

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
