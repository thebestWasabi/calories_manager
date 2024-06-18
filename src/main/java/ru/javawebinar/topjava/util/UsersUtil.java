package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {

    public static final List<User> users = Arrays.asList(
            new User("Максим", "maxim@mail.ru", "123", true, Role.USER),
            new User("Андрей", "andrey@mail.ru", "456", true, Role.USER),
            new User("Дарья", "daria@mail.ru", "321", true, Role.USER)
    );

//    public static List<User> sortByName(List<User> users) {
//        return users.stream()
//                .sorted(Comparator.comparing(User::getName))
//                .collect(Collectors.toList());
//    }
}
