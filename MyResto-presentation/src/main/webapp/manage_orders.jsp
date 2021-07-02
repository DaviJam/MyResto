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
    
    <h1 class="main-title">Commandes à préparer</h1>
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

                <form method="POST" action="order_cancel">
                    <input type="hidden" name="id_order" value="${orders.id}">
                    <input type="submit" class="btn" style="border: none; position: fixed; bottom: 25px; right: 25px; box-shadow: 0 5px 10px rgb(154 160 185 / 45%), 0 15px 40px rgb(166 173 201 / 20%);" value="Annuler cette commande"/>
                </form>
                <form method="POST" action="order_close">
                    <input type="hidden" name="id_order" value="${orders.id}">
                    <input type="submit" class="btn" style="border: none; position: fixed; bottom: 25px; right: 25px; box-shadow: 0 5px 10px rgb(154 160 185 / 45%), 0 15px 40px rgb(166 173 201 / 20%);" value="Terminer"/>
                </form>
                <form method="POST" action="order_inprogress">
                    <input type="hidden" name="id_order" value="${orders.id}">
                    <input type="submit" class="btn" style="border: none; position: fixed; bottom: 25px; right: 25px; box-shadow: 0 5px 10px rgb(154 160 185 / 45%), 0 15px 40px rgb(166 173 201 / 20%);" value="En cours de préparation"/>
                </form>
                <a href="" class="btn btn-unset">Annuler</a>
                <a href="" class="btn">Terminer</a>
            </div>
        </c:forEach>
    </div>
</body>
</html>