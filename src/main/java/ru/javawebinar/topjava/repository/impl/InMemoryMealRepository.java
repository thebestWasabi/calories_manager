package ru.javawebinar.topjava.repository.impl;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepository implements MealRepository {

    private final Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal save(final Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        return meals.put(meal.getId(), meal);
    }

    @Override
    public Meal get(final int id) {
        return meals.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return meals.values();
    }

    @Override
    public void delete(final int id) {
        meals.remove(id);
    }
}
