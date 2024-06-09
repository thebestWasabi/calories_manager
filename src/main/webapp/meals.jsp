<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Meals</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <div class="header">
            <h1>Meals</h1>
            <a href="${pageContext.request.contextPath}/index.html" class="home-link">Home</a>
        </div>

        <table>
            <thead>
                <tr>
                    <th>Date and Time</th>
                    <th>Description</th>
                    <th>Calories</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="meal" items="${requestScope.meals}">
                    <tr class="excess-${meal.excess}">
                        <td>${meal.formattedDateTime}</td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <form method="post" action="${pageContext.request.contextPath}/meals">
            <label for="dateTime">Date and Time:</label>
            <input type="datetime-local" id="dateTime" name="dateTime" required><br>
            <label for="description">Description:</label>
            <input type="text" id="description" name="description" required><br>
            <label for="calories">Calories:</label>
            <input type="number" id="calories" name="calories" required><br>
            <button type="submit">Add Meal</button>
        </form>
    </body>
</html>
