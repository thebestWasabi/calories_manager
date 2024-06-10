package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.db.ICrud;
import ru.javawebinar.topjava.db.InMemoryDb;
import ru.javawebinar.topjava.db.MealsConst;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);
    private final ICrud db = new InMemoryDb();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<Meal> meals = db.getAll();
//        final List<Meal> meals2 = MealsConst.meals;
        final List<MealTo> mealsDto = MealsUtil.toDto(meals);

        log.debug("redirect to meals");

        req.setAttribute("meals", mealsDto);
        req.getRequestDispatcher("/WEB-INF/jsp/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        final LocalDateTime date = LocalDateTime.parse(req.getParameter("dateTime"));
        final String description = req.getParameter("description");
        final int calories = Integer.parseInt(req.getParameter("calories"));

        final Meal meal = new Meal(date, description, calories);
        db.add(meal);

        resp.sendRedirect(req.getContextPath() + "/meals");
    }
}
