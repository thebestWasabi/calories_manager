package ru.javawebinar.topjava.model;

import java.text.MessageFormat;
import java.time.LocalDateTime;

public class UserMealWithExcess {

    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean excess;

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    @Override
    public String toString() {
        return MessageFormat.format("UserMealWithExcess'{'dateTime={0}, description=''{1}'', calories={2}, excess={3}'}'",
                dateTime, description, calories, excess);
    }
}
