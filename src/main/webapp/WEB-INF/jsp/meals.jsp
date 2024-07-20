<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>

<head>
    <title>Meals</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <hr>

    <section>
        <form method="get" action="meals">
            <input type="hidden" name="action" value="filter">
            <dl>
                <dt>From Date (inclusive):</dt>
                <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
            </dl>
            <dl>
                <dt>To Date (inclusive):</dt>
                <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
            </dl>
            <dl>
                <dt>From Time (inclusive):</dt>
                <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
            </dl>
            <dl>
                <dt>To Time (exclusive):</dt>
                <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
            </dl>
            <button type="submit">Filter</button>
        </form>

        <hr/>
        <a href="meals/create">Add Meal</a>
        <br><br>

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
                <c:forEach items="${requestScope.meals}" var="meal">
                    <tr data-meal-excess="${meal.excess}">
                        <td>${fn:formatDateTime(meal.dateTime)}</td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>
                        <td><a href="meals/update/${meal.id}">Update</a></td>
                        <td><a href="meals/delete/${meal.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>
</body>
<jsp:include page="fragments/footer.jsp"/>

</html>