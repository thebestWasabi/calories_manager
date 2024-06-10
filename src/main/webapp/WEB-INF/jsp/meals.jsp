<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Meals</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div class="header">
            <h3><a href="${pageContext.request.contextPath}/index.html" class="home-link">Home</a></h3>
        </div>

        <h3><a href="${pageContext.request.contextPath}/meals/add" class="add-meal-link button">+</a></h3>

        <div class="table-container">
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
        </div>
    </body>
</html>
