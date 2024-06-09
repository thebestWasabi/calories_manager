package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.constant.MealsConst;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.constant.MealsConst.*;

public class MealsUtil {

    public static void main(String[] args) {
        final List<Meal> meals = MealsConst.meals;

        final List<MealTo> mealsTo = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), CALORIES_PER_DAY);
        mealsTo.forEach(System.out::println);
    }

    public static List<MealTo> filteredByStreams(
            final List<Meal> meals,
            final LocalTime startTime, final LocalTime endTime,
            final int caloriesPerDay
    ) {
        final Map<LocalDate, Integer> caloriesForDay = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                        Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> createMeal(meal, caloriesForDay.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<MealTo> toDto(final List<Meal> meals) {
        final Map<LocalDate, Integer> caloriesForDay = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .map(meal -> createMeal(meal, caloriesForDay.get(meal.getDate()) > CALORIES_PER_DAY))
                .collect(Collectors.toList());
    }

    private static MealTo createMeal(final Meal meal, final boolean excess) {
        return new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}
