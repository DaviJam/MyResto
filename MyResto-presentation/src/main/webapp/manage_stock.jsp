<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@include file="head.jsp" %>
</head>
<body>
    <%@include file="card.jsp" %>
    <%@include file="header.jsp" %>
    
    <h1 class="main-title">Actualise ton stock</h1>
    <form method="POST" action="manage_stock">
    <div id="listProduct" class="relative px">
        <div class="product">
            <div class="product-body">
                <img src="img/burger.png" alt="">
                <span>Famous burger</span>  
            </div>
            <div class="product-action">
                <div class="action-stock">
                    <button>-</button>
                    <input type="text" value="12">
                    <button>+</button>    
                </div>
            </div>
        </div>
        <div class="product">
            <div class="product-body">
                <img src="img/burger.png" alt="">
                <span>Famous burger</span>  
            </div>
            <div class="product-action">
                <div class="action-stock">
                    <button>-</button>
                    <input type="text" value="12">
                    <button>+</button>    
                </div>
            </div>
        </div>
        <div class="product">
            <div class="product-body">
                <img src="img/burger.png" alt="">
                <span>Famous burger</span>  
            </div>
            <div class="product-action">
                <div class="action-stock">
                    <button>-</button>
                    <input type="text" value="12">
                    <button>+</button>    
                </div>
            </div>
        </div>
        <div class="product">
            <div class="product-body">
                <img src="img/burger.png" alt="">
                <span>Famous burger</span>  
            </div>
            <div class="product-action">
                <div class="action-stock">
                    <button>-</button>
                    <input type="text" value="12">
                    <button>+</button>    
                </div>
            </div>
        </div>
    </div>
    <input type="submit" class="btn" style="position: fixed; bottom: 25px; right: 25px; box-shadow: 0 5px 10px rgb(154 160 185 / 45%), 0 15px 40px rgb(166 173 201 / 20%);" value="Enregistrer le stock"/>

    </form>
</body>
</html>