package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMealsUtil {

    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(
            final List<UserMeal> meals, final LocalTime startTime, final LocalTime endTime, final int caloriesPerDay
    ) {
        final Map<LocalDate, Integer> caloriesForEveryDay = new HashMap<>();
        for (UserMeal meal : meals) {
            final var mealDate = meal.getDateTime().toLocalDate();
            caloriesForEveryDay.put(mealDate, caloriesForEveryDay.getOrDefault(mealDate, 0) + meal.getCalories());
        }

        final List<UserMealWithExcess> result = new ArrayList<>();
        for (UserMeal meal : meals) {
            final var mealDateTime = meal.getDateTime();

            if (TimeUtil.isBetweenHalfOpen(mealDateTime.toLocalTime(), startTime, endTime)) {
                final var excessResult = caloriesForEveryDay.get(mealDateTime.toLocalDate()) > caloriesPerDay;
                result.add(new UserMealWithExcess(mealDateTime, meal.getDescription(), meal.getCalories(), excessResult));
            }
        }
        return result;
    }

    public static List<UserMealWithExcess> filteredByStreams(
            final List<UserMeal> meals, final LocalTime startTime, final LocalTime endTime, final int caloriesPerDay
    ) {
        final Map<LocalDate, Integer> caloriesForEveryDay = meals.stream()
                .collect(Collectors.groupingBy(meal ->
                        meal.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map(meal -> new UserMealWithExcess(
                        meal.getDateTime(),
                        meal.getDescription(),
                        meal.getCalories(),
                        caloriesForEveryDay.get(meal.getDateTime().toLocalDate()) > caloriesPerDay)
                )
                .collect(Collectors.toList());
    }
}
