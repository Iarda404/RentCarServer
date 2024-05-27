<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="users" method="post">
    <input type="hidden" name="action" value="login">
    <label>Username:</label>
    <input type="text" id="username" name="username" required><br><br>
    <label>Password:</label>
    <input type="password" id="password" name="password" required><br><br>
    <label for="role">Role:</label>
    <input type="text" id="role" name="role" required><br><br>
    <button type="submit">Login</button>
</form>
</body>
</html>
