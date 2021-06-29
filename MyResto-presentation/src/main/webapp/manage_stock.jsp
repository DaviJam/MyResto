<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>MyResto</title>
    </head>
    <body>
        <tbody>
            <c:forEach items="${tickets}" var="ticket">
            <tr>
                <td>${ticket.ticketID}</td>
                <td>${ticket.topic}</td>
                <td>${ticket.category}</td>
                <td>${ticket.status}</td>
            </tr>
            </c:forEach>
        </tbody>
    </body>
</html>
