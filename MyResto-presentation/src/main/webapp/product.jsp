<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html lang="en">
<head>
    <%@include file="head.jsp" %>
</head>
<body>
    <%@include file="card.jsp" %>

    <header>
        <img src="img/menu.svg" alt="" onclick="document.querySelector('header').classList.toggle('active');">
        <img src="img/card.svg" alt="" id="card" onclick="document.querySelector('#modal_card').classList.toggle('active');">
        <span id="productCount" style="background-color: #fd734e;
        padding: 4px;
        border-radius: 20px;
        position: absolute;
        top: 15px;
        right: 15px;
        width: 10px;
        height: 10px;
        font-size: x-small;
        line-height: 12px;
        color: #fff;
        transition: 0.3s all cubic-bezier(0.74, 0.79, 1, 1.81);
        transform: scale(0); 
        text-align: center;">1</span>
        <ul class="nav">
            <li class="active"><a href="index.html">La carte</a></li>
            <li><a href="profile.html">Mon compte</a></li>
            <li><a href="manage_orders.html">Commandes à préparer</a></li>
            <li><a href="manage_stock.html">Gérer les stocks</a></li>
            <li><a href="login.html">Login</a></li>
            <li class="btn">Deconnexion</li>
        </ul>
    </header>
    
    <img src="img/burger.png" alt="" style="width: 100%; position: absolute; top: 0; left: -50%; transition: 0.8s all ease;" id="icon">
    
    <div class="relative center">
        <img src="img/burger.png" alt="" style="width: 100%; position: absolute; top: 0; left: -50%;" id="base">
        <h1 class="main-title" style="margin: 8rem 0; text-align: right;">Famous burger</h1>
        <!--
        <span class="defaut-title">4 produits trouvés</span>
        <div class="v-carrousel">
            <div class="card">
                <a class="link-product" href="">
                    <img src="img/burger.png" alt="">
                    <p>Famous burger</p>
                </a>
                <a class="link-add-card" href="">Ajouter au panier</a>
            </div>

            <div class="card">
                <a class="link-product" href="">
                    <img src="img/burger.png" alt="">
                    <p>Famous burger</p>
                </a>
                <a class="link-add-card" href="">Ajouter au panier</a>
            </div>

            <div class="card">
                <a class="link-product" href="">
                    <img src="img/burger.png" alt="">
                    <p>Famous burger</p>
                </a>
                <a class="link-add-card" href="">Ajouter au panier</a>
            </div>

            <div class="card">
                <a class="link-product" href="">
                    <img src="img/burger.png" alt="">
                    <p>Famous burger</p>
                </a>
                <a class="link-add-card" href="">Ajouter au panier</a>
            </div>
        </div>-->
    </div>

    <script defer type="text/javascript">
        let movable = document.getElementById("icon");
        let base = document.getElementById("base");
        let productCount = document.getElementById("productCount");
        movable.style.top = base.getBoundingClientRect().y + "px";
        movable.style.left = base.getBoundingClientRect().x + "px";

        let finish = document.getElementById('card');


        movable.style.width = "32px";
        movable.style.left = "100vw";
        movable.style.top = "25px";
        movable.style.transform = " translate(calc(-50% - 41px))";
        movable.style.opacity = "0";
        productCount.style.transform = "scale(1)";



        
        function addStyle(styleString) {
            const style = document.createElement('style');
            style.textContent = styleString;
            document.head.append(style);
        }

        addStyle(`
        .anim{-webkit-animation: banner 3s infinite ease; animation: banner 3s infinite linear; animation-delay: 2s;}
        @keyframes banner{
            from {
                top: ` + base.getBoundingClientRect().y + `px;
                /*left: ` + base.getBoundingClientRect().x + `px;*/
                opacity: 1;
                transform:scale(1);
            }
            to {
                top: 25px!important;
                /*left: ` + finish.getBoundingClientRect().x + `px;*/
                opacity: 0;
                transform:scale(0);
            }
        }
        `);

        // movable.classList.add("anim");
/*
        const eye = document.querySelector('#card');
        const { l, t, w, h } = eye.getBoundingClientRect();
        console.log(eye.getBoundingClientRect().x);
        console.log(t + " " + l);
*/
    </script>
</body>
</html>