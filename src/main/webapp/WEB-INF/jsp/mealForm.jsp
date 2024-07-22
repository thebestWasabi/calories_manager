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

            <form method="post"  action="${meal.id == null ? '/topjava/meals/create' : '/topjava/meals/update'}" >
                <input type="hidden" name="id" value="${meal.id}">
                <dl>
                    <dt><spring:message code="meal.form.datetime"/></dt>
                    <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
                </dl>
                <dl>
                    <dt><spring:message code="meal.form.description"/></dt>
                    <dd><input type="text" value="${meal.description}" size=40 name="description" required></dd>
                </dl>
                <dl>
                    <dt><spring:message code="meal.form.calories"/></dt>
                    <dd><input type="number" value="${meal.calories}" name="calories" required></dd>
                </dl>
                <button type="submit"><spring:message code="meal.form.save"/></button>
                <button onclick="window.history.back()" type="button"><spring:message code="meal.form.cancel"/></button>
            </form>
        </section>

        <jsp:include page="fragments/footer.jsp"/>
    </body>

</html>
