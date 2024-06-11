<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>

<html>
    <head>
        <title>Meals</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div class="header">
            <h3><a href="${pageContext.request.contextPath}/index.jsp" class="home-link">Home</a></h3>
        </div>

        <h3><a href="${pageContext.request.contextPath}/meals?action=edit" class="add-meal-link button">+</a></h3>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Date and Time</th>
                        <th>Description</th>
                        <th>Calories</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="meal" items="${requestScope.meals}">
                        <tr class="excess-${meal.excess}">
                            <td>${f:formatLocalDateTime(meal.dateTime, 'dd-MM-yyyy HH:mm')}</td>
                            <td>${meal.description}</td>
                            <td>${meal.calories}</td>
                            <td><a href="${pageContext.request.contextPath}/meals?action=edit&id=${meal.id}">Update</a></td>
                            <td><a href="${pageContext.request.contextPath}/meals?action=delete&id=${meal.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
