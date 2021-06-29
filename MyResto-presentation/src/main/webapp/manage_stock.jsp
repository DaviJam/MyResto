<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <title>Document</title>
</head>
<body>
    <header>
        <img src="img/menu.svg" alt="" onclick="document.querySelector('header').classList.toggle('active');">
        <ul class="nav">
            <li><a href="index.jsp">La carte</a></li>
            <li><a href="profile">Mon compte</a></li>

            <li><a href="manage_orders.jsp">Commandes à préparer</a></li>
            <li><a href="manage_stock.jsp">Gérer les stocks</a></li>
            <li><a href="login.jsp">Login</a></li>
            <li class="btn">Deconnexion</li>
        </ul>
    </header>
    
    <h1 class="main-title">Actualise ton stock</h1>
    <div class="relative px">
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
    <a href="" class="btn" style="position: fixed; bottom: 25px; right: 25px; box-shadow: 0 5px 10px rgb(154 160 185 / 45%), 0 15px 40px rgb(166 173 201 / 20%);">Enregistrer le stock</a>
</body>
</html>