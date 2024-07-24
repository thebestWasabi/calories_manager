<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!doctype html>
<html lang="en">

    <jsp:include page="fragments/headTag.jsp"/>

    <body>
        <jsp:include page="fragments/bodyHeader.jsp"/>

        <section>
            <hr>
            <h2><spring:message code="meal.form.title.${meal.id == null ? 'create' : 'update'}"/></h2>

            <c:set var="createPath" value="${pageContext.request.contextPath}/meals/create"/>
            <c:set var="updatePath" value="${pageContext.request.contextPath}/meals/update"/>
            <c:set var="formAction" value="${meal.id == null ? createPath : updatePath}"/>

            <form method="post" action="${formAction}">
                <input type="hidden" name="id" value="${meal.id}">
                <dl>
                    <dt><label for="dateTime"><spring:message code="meal.form.datetime"/></label></dt>
                    <dd><input id="dateTime" type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
                </dl>
                <dl>
                    <dt><label for="description"><spring:message code="meal.form.description"/></label></dt>
                    <dd><input id="description" type="text" value="${meal.description}" size=40 name="description" required></dd>
                </dl>
                <dl>
                    <dt><label for="calories"><spring:message code="meal.form.calories"/></label></dt>
                    <dd><input id="calories" type="number" value="${meal.calories}" name="calories" required></dd>
                </dl>
                <button type="submit"><spring:message code="meal.form.save"/></button>
                <button onclick="window.history.back()" type="button"><spring:message code="meal.form.cancel"/></button>
            </form>
        </section>

        <jsp:include page="fragments/footer.jsp"/>
    </body>

</html>
