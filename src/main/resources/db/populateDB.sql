DELETE FROM user_role;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('user', 100000),
       ('admin', 100001);

INSERT INTO meal (date_time, description, calories, user_id)
VALUES
    ('2024-06-23 08:00:00', 'Завтрак', 500, 100000),
    ('2024-06-23 13:00:00', 'Обед', 800, 100000),
    ('2024-06-23 18:00:00', 'Ужин', 600, 100000),
    ('2024-06-24 09:00:00', 'Завтрак', 550, 100000),
    ('2024-06-24 13:30:00', 'Обед', 700, 100000),
    ('2024-06-24 19:30:00', 'Ужин', 650, 100000),
    ('2024-06-23 12:30:00', 'Обед', 750, 100001),
    ('2024-06-23 20:00:00', 'Ужин', 700, 100001),
    ('2024-06-24 08:30:00', 'Завтрак', 480, 100001),
    ('2024-06-24 14:00:00', 'Обед', 720, 100001),
    ('2024-06-24 19:00:00', 'Ужин', 600, 100001);
