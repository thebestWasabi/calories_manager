<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="https://max_khamzin.ru/functions" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Meals</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>

    <body>
        <h3><a href="index.html" type="button">Home</a></h3>
        <hr>
        <h2>Meals</h2>
        <a href="meals?action=create&userId=${param.userId}">add</a>

        <div class="table-container">
            <table>
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
                        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealTo"/>

                        <tr class="excess-${meal.excess}">
                            <td>${f:formatLocalDateTime(meal.dateTime)}</td>
                            <td>${meal.description}</td>
                            <td>${meal.calories}</td>
                            <td><a href="meals?action=update&id=${meal.id}&userId=${param.userId}">update</a></td>
                            <td><a href="meals?action=delete&id=${meal.id}&userId=${param.userId}">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
