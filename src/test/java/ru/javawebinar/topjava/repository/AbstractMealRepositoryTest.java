package ru.javawebinar.topjava.repository;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@RunWith(SpringRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@Transactional
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public abstract class AbstractMealRepositoryTest {
    private static final Logger log = LoggerFactory.getLogger(AbstractMealRepositoryTest.class);

    @Autowired
    protected MealRepository repository;

    @BeforeClass
    public static void beforeClass() {
        log.info("\n\n\n---------------------------------");
    }

    @AfterClass
    public static void afterClass() {
        log.info("\n---------------------------------\n\n\n");
    }

    @Test
    public void save() {
        Meal newMeal = getNew();
        Meal created = repository.save(newMeal, USER_ID);
        Integer newId = created.id();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(repository.get(newId, USER_ID), newMeal);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        repository.delete(NOT_FOUND, USER_ID);
    }

    @Test
    public void delete() {
        repository.delete(MEAL1_ID, USER_ID);
        Assert.assertThrows(NotFoundException.class, () -> repository.get(MEAL1_ID, USER_ID));
    }

    @Test
    public void get() {
        Meal actual = repository.get(ADMIN_MEAL_ID, ADMIN_ID);
        MEAL_MATCHER.assertMatch(actual, adminMeal1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        repository.get(NOT_FOUND, 12);
    }

    @Test
    public void getAll() {
        List<Meal> all = repository.getAll(USER_ID);
        MEAL_MATCHER.assertMatch(all, meals);
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> between = repository.getBetweenHalfOpen(LocalDateTime.of(2020, 1, 30, 10, 0), LocalDateTime.of(2020, 1, 31, 10, 0), USER_ID);
        MEAL_MATCHER.assertMatch(between, meal3, meal2, meal1);
    }

    protected abstract Meal getNew();
    protected abstract MealRepository getRepository();

    protected int USER_ID = 100000;
    protected int ADMIN_ID = 100001;
}
