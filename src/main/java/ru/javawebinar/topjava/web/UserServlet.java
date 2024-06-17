package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {

    private static final Logger log = getLogger(UserServlet.class);

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new InMemoryUserRepository();
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "create":
                log.debug("create user");
                final User user = new User("", "", "", true, Role.USER);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/userForm.jsp").forward(request, response);
                break;
            case "delete":
                log.debug("delete user");

            case "all":
            default:
                log.debug("redirect to users");
                final List<User> all = userRepository.getAll();
                request.setAttribute("users", all);
                request.getRequestDispatcher("/users.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        final String idStr = req.getParameter("id");
        final Integer id = idStr.isEmpty() ? null : Integer.valueOf(idStr);

        final String name = req.getParameter("name");
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");
        final int calories = Integer.parseInt(req.getParameter("calories"));

        final User user = new User(id, name, email, password, calories, true, Collections.singleton(Role.USER));
        userRepository.save(user);

        resp.sendRedirect("users");
    }
}
