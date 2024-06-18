package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    @Autowired
    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal save(final Meal meal) {
        return repository.save(meal);
    }

    public void delete(final int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Meal get(final int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Collection<Meal> getAll() {
        return repository.getAll();
    }

    public void update(final Meal meal) {
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

    public Collection<Meal> getByUserId(final int userId) {
        return repository.getAllByUserId(userId);
    }
}
