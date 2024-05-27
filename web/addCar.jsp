<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new car</title>
</head>
<body>
<h1>Add New Car</h1>
<form action="cars" method="post">
    <label for="model">Model:</label>
    <input type="text" id="model" name="model" required><br>

    <label for="available">Available:</label>
    <input type="checkbox" id="available" name="available" value="true"><br>

    <button type="submit">Add Car</button>

    <a href="cars.jsp">View available cars</a>
</form>
</body>
</html>
