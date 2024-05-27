<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car Bookings</title>
</head>
<body>
<h2>Bookings for Car ID: ${idCar}</h2>
<table>
    <tr>
        <th>User ID</th>
        <th>Start Date</th>
        <th>End Date</th>
    </tr>
    <c:forEach var="booking" items="${bookings}">
        <tr>
            <td>${booking.idUser}</td>
            <td>${booking.idCar}</td>
            <td>${booking.from_date}</td>
            <td>${booking.to_Date}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
