package ru.javawebinar.topjava.repository;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

@ActiveProfiles("dataJpa")
public class DataJpaMealRepositoryTest extends AbstractMealRepositoryTest{

    @Override
    protected Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "New meal",  500);
    }

    @Override
    protected MealRepository getRepository() {
        return repository;
    }
}
