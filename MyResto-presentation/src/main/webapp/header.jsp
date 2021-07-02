<%@ page pageEncoding="UTF-8" %>
<header>
    <img src="img/menu.svg" alt="" onclick="document.querySelector('header').classList.toggle('active');">
    <img src="img/shopping-cart-solid.svg" width="24" height="24" alt="" id="card" onclick="document.querySelector('#modal_card').classList.toggle('active');">
    <span class="cardCount">0</span>
    <ul class="nav">
        <%
            String foo = request.getRequestURI().toString();
        %>

        <li class="<%if(foo.equals("/myresto/index.jsp")){%>active<%}%>"><a href="home">La carte</a></li>
        <% if (session.getAttribute("role") != null) { %>
            <% String str = (String) session.getAttribute("role"); %>
            <% if (str.equals("1")) { %>
                <li class="<%if(foo.equals("/myresto/manage_orders.jsp")){%>active<%}%>"><a href="orders_show">Commandes à préparer</a></li>
                <li class="<%if(foo.equals("/myresto/manage_stock.jsp")){%>active<%}%>"><a href="manage_stock">Gérer les stocks</a></li>
            <% } else { %>
                <li class="<%if(foo.equals("/myresto/manage_orders.jsp")){%>active<%}%>"><a href="orders_show">Mes commandes</a></li>
            <% } %>
            <li class="<%if(foo.equals("/myresto/profile.jsp")){%>active<%}%>"><a href="profile">Mon compte</a></li>
            <li class="btn"><a href="logout">Deconnexion</a></li>
        <% } else { %>
            <li class="<%if(foo.equals("/myresto/login.jsp")){%>active<%}%>"><a href="login">Login</a></li>
            <li class="<%if(foo.equals("/myresto/register.jsp")){%>active<%}%>"><a href="register">Créer un compte</a></li>
        <% } %>
    </ul>
</header>