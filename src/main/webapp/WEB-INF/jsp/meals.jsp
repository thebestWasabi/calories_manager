<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!doctype html>
<html>

    <jsp:include page="fragments/headTag.jsp"/>

    <body>
        <jsp:include page="fragments/bodyHeader.jsp"/>
        <hr>

        <section>
            <form method="get" action="meals">
                <input type="hidden" name="action" value="filter">
                <dl>
                    <dt><spring:message code="meals.filter.fromDate"/></dt>
                    <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
                </dl>
                <dl>
                    <dt><spring:message code="meals.filter.toDate" /></dt>
                    <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
                </dl>
                <dl>
                    <dt><spring:message code="meals.filter.fromTime"/></dt>
                    <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
                </dl>
                <dl>
                    <dt><spring:message code="meals.filter.toTime"/></dt>
                    <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
                </dl>
                <button type="submit"><spring:message code="meals.filter"/></button>
            </form>

            <hr/>
            <a href="meals/create"><spring:message code="meals.addMeal"/></a>
            <br><br>

            <table border="1" cellpadding="8" cellspacing="0">
                <thead>
                    <tr>
                        <th><spring:message code="meals.dateTime"/></th>
                        <th><spring:message code="meals.description"/></th>
                        <th><spring:message code="meals.calories"/></th>
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
                            <td><a href="meals/update/${meal.id}"><spring:message code="meals.update"/></a></td>
                            <td><a href="meals/delete/${meal.id}"><spring:message code="meals.delete"/></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <jsp:include page="fragments/footer.jsp"/>
    </body>

</html>