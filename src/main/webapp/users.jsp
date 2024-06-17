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
        <title>Users</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

    </head>

    <body>
        <h3><a href="index.html">Home</a></h3>
        <hr>
        <h2>Users</h2>
        <a href="users?action=create">add</a>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${requestScope.users}">
                        <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>

                        <tr>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                            <td>${user.roles}</td>
                            <td><a href="users?action=update&id=${user.id}">update</a></td>
                            <td><a href="users?action=delete&id=${user.id}">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
