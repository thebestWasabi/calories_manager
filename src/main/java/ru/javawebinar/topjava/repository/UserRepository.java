package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;

import java.util.List;

public interface UserRepository {

    // null if not found, when updated
    User save(final User user);

    // false if not found
    boolean delete(final int id);

    // null if not found
    User get(final int id);

    // null if not found
    User getByEmail(final String email);

    List<User> getAll();
}
