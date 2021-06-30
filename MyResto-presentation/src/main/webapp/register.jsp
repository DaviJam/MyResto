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

    <form class="form" method="POST" action="register" class="form">
        <div class="form-input-container">
            <label>Prénom :</label>
            <input class="form-control form-full-w" type="text" placeholder="Jean" name="surname">
        </div>
        <div class="form-input-container">
            <label>Nom :</label>
            <input class="form-control form-full-w" type="text" placeholder="Dupont" name="firstname">
        </div>
        <div class="form-input-container">
            <label>Email :</label>
            <input class="form-control form-full-w" type="text" placeholder="jeandupont@gmail.com" name="email">
        </div>
        <div class="form-input-container">
            <label>Mot de passe :</label>
            <input class="form-control form-full-w" type="password" name="pwd">
        </div>
        <div class="form-input-container form-input-container--long">
            <label>Adresse :</label>
            <textarea class="form-control form-full-w" type="text" placeholder="1 grande rue 78120 Rambouillet" name="address"></textarea>
        </div>
        <input class="btn" value="Créer mon compte" type="submit">
    </form>
</body>
</html>