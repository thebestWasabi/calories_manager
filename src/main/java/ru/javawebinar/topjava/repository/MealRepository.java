package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {

    // null if updated meal does not belong to userId
    Meal save(final Meal meal);

    // false if meal does not belong to userId
    boolean delete(final int id);

    // null if meal does not belong to userId
    Meal get(final int id);

    // ORDERED dateTime desc
    Collection<Meal> getAll();

    Collection<Meal> getAllByUserId(final int id);
}
