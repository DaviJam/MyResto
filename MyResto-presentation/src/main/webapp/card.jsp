<%@ page pageEncoding="UTF-8" %>
<%@ page session="true" %>
<!-- Modal -->
    <div id="modal_card">
        <h3 class="main-title">Mon panier</h3>
        <div class="relative px" style="max-height: calc(100% - 232px); overflow-y: auto;" id="containerCardProduct">
            <c:forEach items="${cards}" var="card" varStatus="loop">
                <div class="product no-float">
                    <div class="product-body">
                        <img src="${pageContext.request.contextPath}/${ card.image }" alt="">
                        <span>${ card.name } <b> x1</b></span>
                    </div>
                    <img src="${pageContext.request.contextPath}/img/delete.svg" alt="" class="delete-product" data="${loop.index}" onclick="card_delete(this)">
                </div>
                <hr>
            </c:forEach>
        </div>
        <h3 class="main-title" style="text-align: right; margin: 0;">Total : ${sessionScope.sum}€ TTC</h3>
        <div class="relative px">
            <a href="order_create" class="btn" style="margin-top: 25px;">Valider ma commande</a>
        </div>
    </div>