<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <title>MyResto</title>
</head>
<body>
    <!-- Modal -->
    <div id="modal_card">
        <h3 class="main-title">Mon panier</h3>

        <div class="relative px" style="max-height: calc(100% - 232px); overflow-y: auto;">
            <div class="product no-float">
                <div class="product-body">
                    <img src="img/burger.png" alt="">
                    <span>Famous burger <b> x1</b></span>
                </div>
                <img src="img/delete.svg" alt="" class="delete-product">
            </div>
            <hr>
            <div class="product no-float">
                <div class="product-body">
                    <img src="img/burger.png" alt="">
                    <span>Famous burger <b> x1</b></span>
                </div>
                <img src="img/delete.svg" alt="" class="delete-product">
            </div>
            <hr>
            <div class="product no-float">
                <div class="product-body">
                    <img src="img/burger.png" alt="">
                    <span>Famous burger <b> x1</b></span>
                </div>
                <img src="img/delete.svg" alt="" class="delete-product">
            </div>
            <hr>
            <div class="product no-float">
                <div class="product-body">
                    <img src="img/burger.png" alt="">
                    <span>Famous burger <b> x1</b></span>
                </div>
                <img src="img/delete.svg" alt="" class="delete-product">
            </div>
            <hr>
            <div class="product no-float">
                <div class="product-body">
                    <img src="img/burger.png" alt="">
                    <span>Famous burger <b> x1</b></span>
                </div>
                <img src="img/delete.svg" alt="" class="delete-product">
            </div>
        </div>
        <h3 class="main-title" style="text-align: right; margin: 0;">Total : 123€ TTC</h3>
        <div class="relative px">
            <a href="#" class="btn" style="margin-top: 25px;">Valider ma commande</a>
        </div>
    </div>

    <header>
        <img src="img/menu.svg" alt="" onclick="document.querySelector('header').classList.toggle('active');">
        <img src="img/card.svg" alt="" id="card" onclick="document.querySelector('#modal_card').classList.toggle('active');">
        <ul class="nav">
            <li class="active"><a href="index.html">La carte</a></li>
            <li><a href="profile">Mon compte</a></li>
            <li><a href="manage_orders.jsp">Commandes à préparer</a></li>
            <li><a href="manage_stock.jsp">Gérer les stocks</a></li>
            <li><a href="login.jsp">Login</a></li>
            <li class="btn">Deconnexion</li>
        </ul>
    </header>
    
    <h1 class="main-title">Découvre nos produits</h1>
    <input class="form-control form-full-w" type="text" placeholder="Recherche">
    <div class="relative">
        <ul class="main-carrousel">
            <li class="active">Burger</li>
            <li>Pizza</li>
            <li>Désert</li>
            <li>Boisson</li>
            <li>Vin</li>
        </ul>
    </div>

    <div class="relative center">
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
        </div>
    </div>
</body>
</html>