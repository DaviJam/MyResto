<%@ page pageEncoding="UTF-8" %>
<header>
    <img src="img/menu.svg" alt="" onclick="document.querySelector('header').classList.toggle('active');">
    <img src="img/card.svg" alt="" id="card" onclick="document.querySelector('#modal_card').classList.toggle('active');">
    <span class="cardCount">0</span>
    <ul class="nav">
        <li class="active"><a href="home">La carte</a></li>
        <% if (session.getAttribute("role") != null) { %>
            <% String str = (String) session.getAttribute("role"); %>
            <% if (str.equals("1")) { %>
                <li><a href="orders_show">Commandes à préparer</a></li>
                <li><a href="manage_stock">Gérer les stocks</a></li>
            <% } else { %>
                <li><a href="orders_show">Mes commandes</a></li>
            <% } %>
            <li><a href="profile">Mon compte</a></li>
            <li class="btn"><a href="logout">Deconnexion</a></li>
        <% } else { %>
            <li><a href="login">Login</a></li>
            <li><a href="register">Créer un compte</a></li>
        <% } %>
    </ul>
</header>