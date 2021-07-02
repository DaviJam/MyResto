<%@ page pageEncoding="UTF-8" %>
<!-- Modal -->
    <div id="modal_card">
        <h3 class="main-title">Mon panier</h3>
        <div class="relative px" style="max-height: calc(100% - 232px); overflow-y: auto;" id="containerCardProduct">
            <c:forEach items="${cards}" var="card">
                <div class="product no-float">
                    <div class="product-body">
                        <img src="${ card.image }" alt="">
                        <span>${ card.name } <b> x1</b></span>
                    </div>
                    <img src="img/delete.svg" alt="" class="delete-product">
                </div>
                <hr>
            </c:forEach>
        </div>
    </div>