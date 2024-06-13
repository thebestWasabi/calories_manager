<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="https://max_khamzin.ru/functions" %>

<html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <h3><a href="index.html" type="button">Home</a></h3>
        <hr>
        <h2>Meals</h2>
        <a href="meals?action=create">add</a>

        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Description</th>
                    <th>Calories</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="meal" items="${requestScope.mealsList}">
                    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>

                    <tr class="excess-${meal.excess}">
                        <td>${f:formatLocalDateTime(meal.dateTime)}</td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>
                        <td><a href="meals?action=update&id=${meal.id}">update</a></td>
                        <td><a href="meals?action=delete&id=${meal.id}">delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
