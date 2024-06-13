package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {

    public static final List<Meal> MEALS_START_VALUE = Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );


    public static void main(String[] args) {
        List<MealTo> mealsTo = getWithExcess(MEALS_START_VALUE, 2000);
        mealsTo.forEach(System.out::println);
    }


    public static List<MealTo> getWithExcess(Collection<Meal> meals, int caloriesPerDay) {
        return getFilteredWithExcess(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }


    public static List<MealTo> getFilteredWithExcess(
            Collection<Meal> meals,
            LocalTime startTime, LocalTime endTime,
            int caloriesPerDay
    ) {
        final Map<LocalDate, Integer> caloriesForDay = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExcess(meal, caloriesForDay.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }


    private static MealTo createWithExcess(final Meal meal, final boolean excess) {
        return new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }


    public static List<MealTo> getFilteredWithExcessByCycle(
            Collection<Meal> meals,
            LocalTime startTime, LocalTime endTime,
            int caloriesPerDay
    ) {
        final Map<LocalDate, Integer> caloriesForDay = new HashMap<>();
        for (final Meal meal : meals) {
            caloriesForDay.merge(meal.getDate(), meal.getCalories(), Integer::sum);
        }

        final List<MealTo> result = new LinkedList<>();
        for (final Meal meal : meals) {
            if (DateTimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime)) {
                result.add(createWithExcess(meal, caloriesForDay.get(meal.getDate()) > 2000));
            }
        }
        return result;
    }
}
