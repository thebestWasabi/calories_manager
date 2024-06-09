<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
</head>
    <body>
        <div class="header">
            <h1>Meals</h1>
            <a href="index.html" class="home-link">Home</a>
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
    </body>
</html>
