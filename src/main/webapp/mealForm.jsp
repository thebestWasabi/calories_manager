<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Meal Form</title>
    </head>

    <body>
        <h3><a href="index.html">Home</a></h3>
        <hr>
        <h2>Edit meal</h2>

        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request" />

        <div class="form-container">
            <form method="post" action="meals">
                <input type="hidden" name="id" value="${meal.id}"/>
                <dl>
                    <label for="dateTime">Date and Time:</label>
                    <input type="datetime-local" value="${meal.dateTime}" id="dateTime" name="dateTime"/>
                </dl>
                <dl>
                    <label for="description">Description:</label>
                    <input type="text" value="${meal.description}" id="description" name="description"/>
                </dl>
                <dl>
                    <label for="calories">Calories:</label>
                    <input type="number" value="${meal.calories}" id="calories" name="calories"/>
                </dl>
                <input type="hidden" name="userId" value="${meal.userId}">

                <button type="submit" class="button">add</button>
                <button onclick="window.history.back()">cancel</button>
            </form>
        </div>
    </body>
</html>
