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
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {

    private static final Logger log = getLogger(UserServlet.class);
    private static final String USERS_JSP = "/users.jsp";
    private static final String USER_FORM_JSP = "/userForm.jsp";

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
            case "update":
                log.debug("create user");
                final User user = action.equals("create")
                        ? new User("", "", "", true, Role.USER)
                        : userRepository.get(getId(request));
                request.setAttribute("user", user);
                request.getRequestDispatcher(USER_FORM_JSP).forward(request, response);
                break;
            case "delete":
                final int id = getId(request);
                log.debug("delete user");
                userRepository.delete(id);
                response.sendRedirect("users");
                break;
            case "all":
            default:
                log.debug("redirect to users");
                final List<User> all = userRepository.getAll();
                request.setAttribute("users", all);
                request.getRequestDispatcher(USERS_JSP).forward(request, response);
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

        final User user = new User(
                id, name, email, password, calories,
                true, Collections.singleton(Role.USER)
        );

        userRepository.save(user);

        resp.sendRedirect("users");
    }

    private int getId(final HttpServletRequest request) {
        final String idStr = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(idStr);
    }
}
