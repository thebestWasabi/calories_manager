package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        final User reference = em.getReference(User.class, userId);
        meal.setUser(reference);

        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        }
        else {
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createQuery("DELETE FROM Meal m WHERE m.id = :id " +
                              "AND m.user.id = :userId")
                       .setParameter("id", id)
                       .setParameter("userId", userId)
                       .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        try {
            return em.createQuery("SELECT m FROM Meal m WHERE m.id = :id " +
                                  "AND m.user.id = :userId", Meal.class)
                    .setParameter("id", id)
                    .setParameter("userId", userId)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createQuery("SELECT m FROM Meal m JOIN m.user u WHERE u.id = :userId " +
                              "ORDER BY m.dateTime DESC", Meal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createQuery("SELECT m FROM Meal m JOIN m.user u WHERE u.id = :userId " +
                              "AND m.dateTime >= :startDateTime AND m.dateTime < :endDateTime " +
                              "ORDER BY m.dateTime DESC", Meal.class)
                .setParameter("userId", userId)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .getResultList();
    }
}