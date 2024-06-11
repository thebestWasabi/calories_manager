package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.db.MealsConst;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class MealsUtil {

    private static final int CALORIES_PER_DAY = 2000;

    public static void main(String[] args) {
        final List<Meal> meals = MealsConst.meals;

        final List<MealTo> mealsTo = withTimeFilter(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), CALORIES_PER_DAY);
        mealsTo.forEach(System.out::println);
    }

    public static List<MealTo> withTimeFilter(
            final List<Meal> meals,
            final LocalTime startTime, final LocalTime endTime,
            final int caloriesPerDay
    ) {
        final Map<LocalDate, Integer> caloriesForDay = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> createMeal(meal, caloriesForDay.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<MealTo> withoutFilter(final List<Meal> meals, final int caloriesPerDay) {
        final Map<LocalDate, Integer> caloriesForDay = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .map(meal -> createMeal(meal, caloriesForDay.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static MealTo createMeal(final Meal meal, final boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}
