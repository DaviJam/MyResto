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
            <li><a href="index.html">La carte</a></li>
            <li><a href="profile.html">Mon compte</a></li>
            <li><a href="manage_orders.html">Commandes à préparer</a></li>
            <li><a href="manage_stock.html">Gérer les stocks</a></li>
            <li><a href="login.html">Login</a></li>
            <li class="btn">Deconnexion</li>
        </ul>
    </header>
    
    <h1 class="main-title"><i></i> Mes informations personnelles</h1>
    <div class="relative px" id="profile">
        <p>Nom : <span>Morvan</span></p>
        <p>Prénom : <span>Steven</span></p>
        <p>Email : <span>pro.morvan.steven@gmail.com</span></p>
        <p>Adresse : <span>37 rue de la vallée, 78970, Mézières sur seine</span></p>
        <a href="#" class="btn fixed">Deconnexion</a>
    </div>
    
</body>
</html>