package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.impl.InMemoryMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class MealServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private static final int CALORIES_PER_DAY = 2000;
    private static final String MEALS_JSP = "/meals.jsp";

    private MealRepository mealRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        mealRepository = new InMemoryMealRepository();
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final String action = request.getParameter("action");

        if (action == null) {
            log.info("getAll");
            request.setAttribute("mealsList", MealsUtil.getWithExcess(mealRepository.getAll(), CALORIES_PER_DAY));
            request.getRequestDispatcher(MEALS_JSP).forward(request, response);
        }
        else if (action.equals("delete")) {
            final int id = getId(request);
            log.info("delete: {}", id);
            mealRepository.delete(id);
            response.sendRedirect("meals");
        }
        else {
            final Meal meal = action.equals("create")
                    ? new Meal(LocalDateTime.now(), "", 1000)
                    : mealRepository.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        final String idStr = request.getParameter("id");
        final Integer id = idStr.isEmpty() ? null : Integer.valueOf(idStr);
        final LocalDateTime date = LocalDateTime.parse(request.getParameter("dateTime"));
        final String description = request.getParameter("description");
        final int calories = Integer.parseInt(request.getParameter("calories"));

        final Meal meal = new Meal(id, date, description, calories);

        log.info(meal.isNew() ? "Create new meal {}" : "Update meal {}", meal);

        mealRepository.save(meal);
        response.sendRedirect("meals");
    }

    private int getId(final HttpServletRequest request) {
        final String idStr = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(idStr);
    }
}
