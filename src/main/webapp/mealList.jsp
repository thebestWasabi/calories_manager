<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="https://max_khamzin.ru/functions" %>

<html>
    <head>
        <title>Title</title>
        <style>
            .excess-false {background-color: #e8f5e9}
            .excess-true {background-color: #ffebee;}
        </style>
    </head>
    <body>
        <h3><a href="index.html">Home</a></h3>
        <hr>
        <h2>Meals</h2>

        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Description</th>
                    <th>Calories</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.mealsList}" var="meal">
                    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo" />
                        <tr class="excess-${meal.excess}">
                        <td>${f:formatLocalDateTime(meal.dateTime)}</td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
