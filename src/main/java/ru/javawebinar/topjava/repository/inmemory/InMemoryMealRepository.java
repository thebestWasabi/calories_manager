package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {

    private final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    {
        MealsUtil.meals.forEach(this::save);
    }

    @Override
    public Meal save(final Meal meal) {
        log.info("save {}", meal);

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }

        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> {
            if (Objects.equals(oldMeal.getUserId(), meal.getUserId())) {
                return meal;
            }
            return oldMeal;
        });

    }

    @Override
    public boolean delete(final int id) {
        log.info("delete {}", id);

        AtomicBoolean removed = new AtomicBoolean(false);

        repository.entrySet().removeIf(entry -> {
            final Meal meal = entry.getValue();

            if (meal.getId() == id) {
                removed.set(true);
                return true;
            }
            return false;
        });
        return removed.get();
    }

    @Override
    public Meal get(final int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        log.info("getAll");
        return repository.values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    public Collection<Meal> getAllByUserId(final int userId) {
        log.info("getAllByUserId {}", userId);

        return repository.values().stream()
                .filter(meal -> meal.getUserId() != null && meal.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}
