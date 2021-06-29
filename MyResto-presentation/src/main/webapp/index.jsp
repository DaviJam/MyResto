<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page session="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <title>Gestion d'établissement Ensup</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>nom du produit</th>
                    <th>description du produit</th>
                </tr>
            </thead>
            <tbody>
                <% out.println("Bip bip !"); %>

                ${fn:length(listProduct)}
                <c:forEach items="${listProduct}" var="product">
                    <tr>
                        <td>${ product.name }</td>
                        <td>${ product.description }</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
