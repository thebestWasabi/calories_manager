package ru.javawebinar.topjava.db;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface CrudMeal {

    Meal add(final Meal meal);

    Meal get(final int id);

    List<Meal> getAll();

    Meal update(final Meal meal);

    void delete(final int id);
}
