<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Available Cars</title>
</head>
<body>
<h2>Available Cars</h2>
<table>
    <tr>
        <th>Model</th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <li>${car}</li>>
    </c:forEach>
</table>
<%--<a href="addCar.jsp">Add New Car</a>--%>
</body>
</html>
