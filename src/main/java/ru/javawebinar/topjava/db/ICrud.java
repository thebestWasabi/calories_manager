package ru.javawebinar.topjava.db;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface ICrud {

    void add(Meal mealTo);

    Meal get(Integer id);

    List<Meal> getAll();

    void update(Integer id, Meal mealTo);

    void delete(Integer id);
}
