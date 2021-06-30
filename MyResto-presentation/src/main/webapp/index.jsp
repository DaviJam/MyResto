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
                    <a class="link-product" href="">
                        <img src="${ product.image }" alt="">
                        <p>${ product.name }</p>
                    </a>
                    <a class="link-add-card" href="">Ajouter au panier</a>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>