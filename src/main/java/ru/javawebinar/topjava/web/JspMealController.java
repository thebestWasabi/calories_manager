package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/meals")
public class JspMealController {

    private final MealService mealService;

    @Autowired
    public JspMealController(final MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    public String getAll(final Model model, HttpServletRequest request) {
        final var userId = SecurityUtil.authUserId();

        LocalDate startDate = DateTimeUtil.parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = DateTimeUtil.parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = DateTimeUtil.parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = DateTimeUtil.parseLocalTime(request.getParameter("endTime"));
        List<Meal> mealsDateFiltered = mealService.getBetweenInclusive(startDate, endDate, userId);
        final var filteredTos = MealsUtil.getFilteredTos(mealsDateFiltered, SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);

        model.addAttribute("meals", filteredTos);
        return "meals";
    }

    @GetMapping("/create")
    public String getCreateForm(Model model) {
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        return "mealForm";
    }

    @PostMapping("/create")
    public String create(@RequestParam("dateTime") String dateTime,
                         @RequestParam("description") String description,
                         @RequestParam("calories") int calories) {
        final var userId = SecurityUtil.authUserId();
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);

        Meal meal = new Meal(null, localDateTime, description, calories);
        mealService.create(meal, userId);
        return "redirect:/meals";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable("id") int id, Model model) {
        final var userId = SecurityUtil.authUserId();
        Meal meal = mealService.get(id, userId);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id,
                         @RequestParam("dateTime") String dateTime,
                         @RequestParam("description") String description,
                         @RequestParam("calories") int calories) {
        final var userId = SecurityUtil.authUserId();
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);

        Meal meal = new Meal(id, localDateTime, description, calories);
        mealService.update(meal, userId);
        return "redirect:/meals";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        final var userId = SecurityUtil.authUserId();
        mealService.delete(id, userId);
        return "redirect:/meals";
    }
}
