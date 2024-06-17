<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>User Form</title>
    </head>

    <body>
        <h3><a href="index.html">Home</a></h3>
        <hr>
        <h2>Create user</h2>

        <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User" scope="request"/>

        <div>
            <form method="post" action="users">
                <input type="hidden" name="id" value="${user.id}"/>
                <dl>
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" value="${user.name}"/>
                </dl>
                <dl>
                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email" value="${user.email}"/>
                </dl>
                <dl>
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" value="${user.password}"/>
                </dl>
                <dl>
                    <label for="calories">Calories per day</label>
                    <input type="number" id="calories" name="calories" value="${user.caloriesPerDay}"/>
                </dl>

                <button type="submit" class="button">add</button>
                <button onclick="window.history.back()">back</button>
            </form>
        </div>
    </body>
</html>
