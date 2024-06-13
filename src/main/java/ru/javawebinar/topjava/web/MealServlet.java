package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MealServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        log.info("getAll");

        final List<Meal> mealsStartValue = MealsUtil.MEALS_START_VALUE;
        final List<MealTo> withExcess = MealsUtil.getWithExcess(mealsStartValue, 2000);

        request.setAttribute("mealsList", withExcess);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
