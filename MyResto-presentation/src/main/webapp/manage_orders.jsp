<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp" %>
</head>
<body>
    <%@include file="card.jsp" %>
    <%@include file="header.jsp" %>
    
    <h1 class="main-title">Commandes a préparer</h1>
    <div class="relative px">
        <c:forEach items="${orderlist}" var="orders">
            <div class="orders">
                <p class="orders-time">Il y a 5 min <span class="product-status">${orders.status.name}</span></p>
                <p class="orders-id">N° ${orders.id} - ${orders.user.surname} ${orders.user.firstname}</p>
                <hr>
                <table>
                    <c:set var="somme" value="<%=Double.valueOf(0)%>" />
                    <c:forEach items="${orders.product}" var="product">
                        <tr>
                            <td>${product.name}</td>
                            <td>x ${product.stock}</td>
                            <c:set var="somme" value="${somme + product.price * product.stock}" />
                        </tr>
                    </c:forEach>
                </table>
                <hr>
                <p class="orders-price"><c:out value="${somme}"/> € TTC</p>
                <a href="" class="btn btn-unset">Annuler</a>
                <a href="" class="btn">Terminer</a>
            </div>
        </c:forEach>
    </div>
</body>
</html>