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

    <% String str = (String) session.getAttribute("role"); %>
    <% if (str.equals("1")) { %>
        <h1 class="main-title">Commandes à préparer</h1>
    <%} else {%>
        <h1 class="main-title">Mes commandes</h1>
    <%}%>
    <div class="relative px orders-list">
        <c:forEach items="${orderlist}" var="orders">
            <div class="orders">
                <p class="orders-time">Il y a <c:if test="${orders.value.hour > 0}" >${orders.value.hour} h et</c:if> ${orders.value.minute} min <span class="product-status">${orders.key.status.name}</span></p>
                <p class="orders-id">N° ${orders.key.id} - ${orders.key.user.surname} ${orders.key.user.firstname}</p>
                <hr>
                <table>
                    <c:set var="somme" value="<%=Double.valueOf(0)%>" />
                    <c:forEach items="${orders.key.product}" var="product">
                        <tr>
                            <td>${product.name}</td>
                            <td>x ${product.stock}</td>
                            <c:set var="somme" value="${somme + product.price * product.stock}" />
                        </tr>
                    </c:forEach>
                </table>
                <hr>
                <p class="orders-price"><c:out value="${somme}"/> € TTC</p>

                <% if (str.equals("1")) { %>
                    <form method="POST" action="order_cancel" style="display:${orders.key.status.name == 'Terminé' || orders.key.status.name == 'Annulé' || orders.key.status.name == 'En cours' ? 'none;' : 'initial;'}">
                        <input type="hidden" name="id_order" value="${orders.key.id}">
                        <input type="submit" class="btn btn-unset order" value="Annuler cette commande"/>
                    </form>
                    <form method="POST" action="${orders.key.status.name == 'En attente' ? 'order_inprogress' : 'order_close'}" style="display:${orders.key.status.name == 'Terminé' || orders.key.status.name == 'Annulé' ? 'none;' : 'initial;'}">
                        <input type="hidden" name="id_order" value="${orders.key.id}">
                        <input type="submit" class="btn order" value="${orders.key.status.name == 'En attente' ? 'Je prépare cette commande' : 'Commande finalisée'}"/>
                    </form>
                <% } else { %>
                    <form method="POST" action="order_cancel" style="display:${orders.key.status.name == 'Terminé' || orders.key.status.name == 'Annulé' || orders.key.status.name == 'En cours' ? 'none;' : 'initial;'}">
                        <input type="hidden" name="id_order" value="${orders.key.id}">
                        <input type="submit" class="btn order" value="Je n'ai plus faim"/>
                    </form>
                <% } %>
            </div>
        </c:forEach>
    </div>
</body>
</html>