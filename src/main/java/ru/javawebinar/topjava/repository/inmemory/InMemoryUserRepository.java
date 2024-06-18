package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private final Map<Integer, User> repository = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    {
        UsersUtil.users.forEach(this::save);
    }

    @Override
    public User save(final User user) {
        log.info("save {}", user);

        if (user.isNew()) {
            user.setId(idCounter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }

        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(final int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User get(final int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public User getByEmail(final String email) {
        log.info("getByEmail {}", email);

        return repository.values().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

//        for (final Map.Entry<Integer, User> entry : repository.entrySet())
//            if (entry.getValue().getEmail().equals(email))
//                return entry.getValue();
//        return null;
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository.values().stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }
}
