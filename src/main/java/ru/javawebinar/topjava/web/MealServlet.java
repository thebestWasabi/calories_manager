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
    private static final String MEALS_JSP = "/meals.jsp";
    private static final String INSERT_OR_EDIT_JSP = "/mealForm.jsp";

    private final CrudMeal db;

    public MealServlet() {
        super();
        db = new InMemoryDbMeal();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String action = req.getParameter("action");

        switch (action) {
            case "new":
                showNewForm(req, resp);
                break;
            case "add":
                add(req, resp);
                break;
            case "meals":
                listMeal(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            default:
                listMeal(req, resp);
                break;
        }
    }

    private void listMeal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<Meal> meals = db.getAll();
        final List<MealTo> mealTos = MealsUtil.withoutFilter(meals, 2000);
        req.setAttribute("meals", mealTos);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MEALS_JSP);
        dispatcher.forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        final String description = req.getParameter("description");
        final int calories = Integer.parseInt(req.getParameter("calories"));

        final Meal newMeal = new Meal(dateTime, description, calories);

        db.add(newMeal);
        resp.sendRedirect(req.getContextPath() + "/meals?action=meals");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final int id = Integer.parseInt(req.getParameter("id"));
        final LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        final String description = req.getParameter("description");
        final int calories = Integer.parseInt(req.getParameter("calories"));

        final Meal meal = new Meal(dateTime, description, calories);

        meal.setId(id);
        db.update(meal);
        resp.sendRedirect(req.getContextPath() + "/meals?action=meals");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final int id = Integer.parseInt(req.getParameter("id"));
        db.delete(id);
        resp.sendRedirect(req.getContextPath() + "/meals?action=meals");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(INSERT_OR_EDIT_JSP).forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id = Integer.parseInt(req.getParameter("id"));
        final Meal maybeMeal = db.get(id);
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(INSERT_OR_EDIT_JSP);
        req.setAttribute("meal", maybeMeal);
        requestDispatcher.forward(req, resp);
    }
}
