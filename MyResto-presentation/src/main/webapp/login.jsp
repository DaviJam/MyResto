<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp" %>
</head>
<body>
    <%@include file="card.jsp" %>
    <%@include file="header.jsp" %>

    <h1 class="main-title">Accéder à MyResto.fr</h1>
    <p class="sub-t">Vous devez vous connecter avant de pouvoir commander sur MyResto.fr</p>

    <form method="POST" action="login" class="form">
        <div class="form-input-container">
            <label>Adresse email :</label>
            <input name="login" class="form-control form-full-w" type="text" placeholder="jeandupont@gmail.com">
        </div>

        <div class="form-input-container">
            <label>Mot de passe :</label>
            <input name="pwd" class="form-control form-full-w" type="text" placeholder="123456">
        </div>

        <input type="submit" class="btn" value="Se connecter">
    </form>
</body>
</html>