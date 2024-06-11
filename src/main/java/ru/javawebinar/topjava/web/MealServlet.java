package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.db.CrudMeal;
import ru.javawebinar.topjava.db.InMemoryDbMeal;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
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
    private static final String MEALS = "/jsp/meals.jsp";
    private static final String INSERT_OR_EDIT = "/jsp/addMeal.jsp";

    private final CrudMeal db;

    public MealServlet() {
        super();
        db = new InMemoryDbMeal();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        log.debug("Action: {}", action);

        if (action == null) action = "meals";

        if (action.equalsIgnoreCase("delete")) {
            forward = MEALS;
            final int id = Integer.parseInt(req.getParameter("id"));
            db.delete(id);
            req.setAttribute("meals", db.getAll());
        }
        else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            final String idStr = req.getParameter("id");
            if (idStr != null) {
                final int id = Integer.parseInt(idStr);
                final Meal meal = db.get(id);
                req.setAttribute("meal", meal);
            }
        }
        else if (action.equalsIgnoreCase("meals")) {
            forward = MEALS;
            final List<Meal> meals = db.getAll();
            final List<MealTo> mealTos = MealsUtil.withoutFilter(meals, 2000);
            req.setAttribute("meals", mealTos);
        }
        else {
            forward = INSERT_OR_EDIT;
        }

        final RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        final LocalDateTime date = LocalDateTime.parse(req.getParameter("dateTime"));
        final String description = req.getParameter("description");
        final int calories = Integer.parseInt(req.getParameter("calories"));

        final Meal meal = new Meal(date, description, calories);

        final String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            db.add(meal);
        }
        else {
            meal.setId(Integer.parseInt(id));
            db.update(meal);
        }
        resp.sendRedirect(req.getContextPath() + "/meals");
    }
}
