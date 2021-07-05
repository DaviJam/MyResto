<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page session="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@include file="head.jsp" %>
</head>
<body>
    <%@include file="card.jsp" %>
    <%@include file="header.jsp" %>

    <h1 class="main-title">Découvre nos produits</h1>

    <input class="form-control form-full-w" type="text" placeholder="Recherche">
    <div class="relative">
        <ul class="main-carrousel">

            <li class="active filter" data-category="*">Tout</li>
            <c:forEach items="${listCategory}" var="category">
                <li data-category="${ category.getNum() }" class="filter">${ category.getName() }</li>
            </c:forEach>
        </ul>
    </div>
    <div class="relative center">
        <span class="defaut-title">${fn:length(listProduct)} produits trouvés</span>
        <div class="v-carrousel">
            <c:forEach items="${listProduct}" var="product">
                <div class="card" data-category="${ product.category.getNum() }">
                    <div class="link-product" href="product/${ product.id }">
                        <img class="home-img" src="${ product.image }" alt="">
                        <p>${ product.name }</p>
                        <span>${ product.price }€</span>
                    </div>
                    <hr noshade style="border-color: #292d30; margin: 8px 15px;">
                    <form method="POST" action="card_add" id="product${ product.id }" class="addToCard">
                        <input type="hidden" value="${ product.id }" name="product">
                    </form>
                    <input class="link-add-card" type="submit" form="product${ product.id }" value="Ajouter au panier">
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>