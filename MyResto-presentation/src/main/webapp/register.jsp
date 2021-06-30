<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp" %>
</head>
<body>
    <%@include file="card.jsp" %>
    <%@include file="header.jsp" %>

    <h1 class="main-title">Se créer un compte sur MyResto.fr</h1>
    <p class="sub-t">Vous devez vous créer un compte avant de pouvoir commander sur MyResto.fr</p>

    <form class="form">

        <div class="form-input-container">
            <label>Prénom :</label>
            <input class="form-control form-full-w" type="text" placeholder="Jean">
        </div>

        <div class="form-input-container">
            <label>Nom :</label>
            <input class="form-control form-full-w" type="text" placeholder="Dupont">
        </div>

        <div class="form-input-container">
            <label>Email :</label>
            <input class="form-control form-full-w" type="text" placeholder="jeandupont@gmail.com">
        </div>

        <div class="form-input-container">
            <label>Mot de passe :</label>
            <input class="form-control form-full-w" type="text" placeholder="123456">
        </div>

        <div class="form-input-container form-input-container--long">
            <label>Adresse :</label>
            <textarea class="form-control form-full-w" type="text" placeholder="1 grande rue 78120 Rambouillet"></textarea>
        </div>

        <a class="btn">Se connecter</a>

    </form>

</body>
</html>