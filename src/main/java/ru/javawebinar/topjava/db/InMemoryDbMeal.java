package ru.javawebinar.topjava.db;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryDbMeal implements CrudMeal {

    private final ConcurrentMap<Integer, Meal> meals = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    {
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public Meal add(final Meal meal) {
        final int id = nextId.getAndIncrement();
        meal.setId(id);
        meals.put(id, meal);
        return meal;
    }

    @Override
    public Meal get(final int id) {
        return meals.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal update(final Meal meal) {
        final Integer id = meal.getId();

        if (!meals.containsKey(id)) {
            return null; // Или бросить исключение, если прием пищи с указанным id не существует
        }

        return meals.put(id, meal);
    }

    @Override
    public void delete(final int id) {
        meals.remove(id);
    }
}