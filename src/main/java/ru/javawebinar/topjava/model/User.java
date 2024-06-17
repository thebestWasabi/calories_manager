package ru.javawebinar.topjava.model;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class User extends AbstractNamedEntity {

    private String email;
    private String password;
    private boolean enabled;
    private Date registered = new Date();
    private Set<Role> roles;
    private int caloriesPerDay;

    public User(final Integer id, final String name, final String email, final String password,
                final int caloriesPerDay, final boolean enabled, final Collection<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.caloriesPerDay = caloriesPerDay;
        this.enabled = enabled;
        setRoles(roles);
    }

    public User(final Integer id, final String name, final String email,
                final String password, final Role... roles) {
        this(id, name, email, password, DEFAULT_CALORIES_PER_DAY, true, Arrays.asList(roles));
    }

    public User(final String name, final String email, final String password,
                final boolean enabled, final Role... roles) {
        this(null, name, email, password, DEFAULT_CALORIES_PER_DAY, true, Arrays.asList(roles));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", email=" + email +
               ", name=" + name +
               ", enabled=" + enabled +
               ", roles=" + roles +
               ", caloriesPerDay=" + caloriesPerDay +
               '}';
    }
}