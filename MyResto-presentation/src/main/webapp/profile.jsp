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
        <img src="<%=request.getContextPath()%>/img/menu.svg" alt="" onclick="document.querySelector('header').classList.toggle('active');">
        <ul class="nav">
            <li><a href="index.jsp">La carte</a></li>
            <li><a href="profile">Mon compte</a></li>
            <li><a href="manage_orders.jsp">Commandes à préparer</a></li>
            <li><a href="manage_stock.jsp">Gérer les stocks</a></li>
            <li><a href="login.jsp">Login</a></li>
            <li class="btn">Deconnexion</li>
        </ul>
    </header>
    
    <h1 class="main-title"><i></i> Mes informations personnelles</h1>
    <div class="relative px" id="profile">
        <p>Nom : <span>${surname}</span></p>
        <p>Prénom : <span>${firstname}</span></p>
        <p>Email : <span>${email}</span></p>
        <p>Adresse : <span>${address}</span></p>
        <a href="#" class="btn fixed">Deconnexion</a>
    </div>
    
</body>
</html>