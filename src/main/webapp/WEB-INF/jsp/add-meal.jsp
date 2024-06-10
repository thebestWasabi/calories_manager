<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Meal</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div class="form-container">
            <form method="post" action="${pageContext.request.contextPath}/meals">
                <label for="dateTime">Date and Time:</label>
                <input type="datetime-local" id="dateTime" name="dateTime" required><br>
                <label for="description">Description:</label>
                <input type="text" id="description" name="description" required><br>
                <label for="calories">Calories:</label>
                <input type="number" id="calories" name="calories" required><br>
                <button type="submit" class="button">add</button>
            </form>
        </div>
    </body>
</html>
