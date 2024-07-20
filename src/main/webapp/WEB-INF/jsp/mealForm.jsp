<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Meal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>
    <jsp:include page="fragments/bodyHeader.jsp"/>

    <section>
        <hr>
        <h2><c:out value="${meal.id == null ? 'Create' : 'Update'}"/></h2>

        <form method="post"  action="${meal.id == null ? '/topjava/meals/create' : '/topjava/meals/update'}" >
            <input type="hidden" name="id" value="${meal.id}">
            <dl>
                <dt>DateTime:</dt>
                <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
            </dl>
            <dl>
                <dt>Description:</dt>
                <dd><input type="text" value="${meal.description}" size=40 name="description" required></dd>
            </dl>
            <dl>
                <dt>Calories:</dt>
                <dd><input type="number" value="${meal.calories}" name="calories" required></dd>
            </dl>
            <button type="submit">Save</button>
            <button onclick="window.history.back()" type="button">Cancel</button>
        </form>
    </section>
</body>

<jsp:include page="fragments/footer.jsp"/>

</html>
