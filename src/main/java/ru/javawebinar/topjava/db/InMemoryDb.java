package ru.javawebinar.topjava.db;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryDb implements ICrud {

    private static final ConcurrentMap<Integer, Meal> MEALS = new ConcurrentHashMap<>();
    private static final AtomicInteger NEXT_ID = new AtomicInteger(1);

    @Override
    public void add(final Meal meal) {
        final int id = NEXT_ID.getAndIncrement();
        meal.setId(id);
        MEALS.put(id, meal);
    }

    @Override
    public Meal get(final Integer id) {
        return MEALS.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(MEALS.values());
    }

    @Override
    public void update(final Integer id, final Meal meal) {
        MEALS.put(id, meal);
    }

    @Override
    public void delete(final Integer id) {
        MEALS.remove(id);
    }
}
