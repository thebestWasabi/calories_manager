package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealsUtilWithRecursion {

    static Map<LocalDate, Integer> mapDay = new HashMap<>();

    public static void main(String[] args) {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<MealTo> mealsTo = getFilteredWithRecursion(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
    }

    public static List<MealTo> getFilteredWithRecursion(
            final List<Meal> meals,
            final LocalTime startTime, final LocalTime endTime,
            final int caloriesPerDay
    ) {
        final List<MealTo> result = new ArrayList<>();
        mapDay.merge(meals.get(0).getDate(), meals.get(0).getCalories(), Integer::sum);

        if (meals.size() > 1) {
            result.addAll(getFilteredWithRecursion(meals.subList(1, meals.size()), startTime, endTime, caloriesPerDay));
        }

        final LocalTime time = meals.get(0).getTime();
        if (TimeUtil.isBetweenHalfOpen(time, startTime, endTime)) {
            result.add(createMealDto(meals.get(0), mapDay.get(meals.get(0).getDate()) <= caloriesPerDay));
        }

        return result;
    }

    private static MealTo createMealDto(Meal meal, boolean excess) {
        return new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}
