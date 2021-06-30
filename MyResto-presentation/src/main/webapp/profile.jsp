<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp" %>
</head>
<body>
    <%@include file="card.jsp" %>
    <%@include file="header.jsp" %>
    
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