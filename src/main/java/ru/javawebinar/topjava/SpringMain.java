package ru.javawebinar.topjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SpringMain {

    private static final Logger log = LoggerFactory.getLogger(SpringMain.class);


    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            MealService mealService = appCtx.getBean(MealService.class);


            User newUser = new User(null, "userName", "email@mail.ru", "password", Role.ADMIN);
            adminUserController.create(newUser);
            log.info("Created user: {}", newUser);

            List<User> allUsers = adminUserController.getAll();
            for (User user : allUsers) {
                log.info("User: {}", user);
                Collection<Meal> meals = mealService.getByUserId(user.getId());
                for (Meal meal : meals) {
                    log.info("Meal: {}", meal);
                }
                System.out.println("\n\n");
            }
        }
        catch (Exception e) {
            log.error("Application context initialization failed", e);
        }
    }

}
