<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <title>Gestion d'établissement Ensup</title>
    </head>
    <body>
        <form id="btn_login" action="login" method="get">
            <button class="btn btn-primary" type="submit">Login</button>
        </form>
        <form id="btn_logout" action="logout" method="get">
            <button class="btn btn-warning" type="submit">Logout</button>
        </form>
        <c:choose>
          <c:when test="${not empty sessionScope}">
            <h1>LOGGED</h1>
          </c:when>
          <c:otherwise>
            <h1>NOT LOGGED</h1>
          </c:otherwise>
        </c:choose>
    </body>
</html>
