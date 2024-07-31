package ru.javawebinar.topjava.web;

import org.assertj.core.api.Assertions;
import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.javawebinar.topjava.UserTestData.USER_MATCHER;
import static ru.javawebinar.topjava.UserTestData.admin;
import static ru.javawebinar.topjava.UserTestData.guest;
import static ru.javawebinar.topjava.UserTestData.user;

class RootControllerTest extends AbstractControllerTest {

    @Test
    void getUsers() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users",
                        new AssertionMatcher<List<User>>() {
                            @Override
                            public void assertion(List<User> actual) throws AssertionError {
                                USER_MATCHER.assertMatch(actual, admin, guest, user);
                            }
                        }
                ));
    }

    @Test
    void getMeals() throws Exception {
        perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meals"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                .andExpect(model().attribute("meals", new AssertionMatcher<List<MealTo>>() {
                    @Override
                    public void assertion(final List<MealTo> actual) throws AssertionError {
                        MealTestData.MEAL_TO_MATCHER.assertMatch(actual, MealTestData.getMealTos());
                    }
                }));
    }

    // Я не совсем понял, зачем нам создавать MatcherFactory и MEAL_TO_MATCHER, если можно писать как в getMealsVersion2
    // Или я что-то не понимаю?
    @Test
    void getMealsVersion2() throws Exception {
        perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meals"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                .andExpect(model().attribute("meals", new AssertionMatcher<List<MealTo>>() {
                    @Override
                    public void assertion(final List<MealTo> actual) throws AssertionError {
                        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(MealTestData.getMealTos());
                    }
                }));
    }
}