<%@ page pageEncoding="UTF-8" %>
<header>
    <img src="img/menu.svg" alt="" onclick="document.querySelector('header').classList.toggle('active');">
    <img src="img/shopping-cart-solid.svg" width="24" height="24" alt="" id="card" onclick="document.querySelector('#modal_card').classList.toggle('active');">
    <span class="cardCount">0</span>
    <ul class="nav">
        <% String foo = request.getRequestURI().toString(); %>

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
            <li class="<%if(foo.equals("/myresto/login.jsp")){%>active<%}%> right"><a href="login">Login</a></li>
            <li class="<%if(foo.equals("/myresto/register.jsp")){%>active<%}%> right"><a href="register">Créer un compte</a></li>
        <% } %>
    </ul>
</header>

<div class="alert alert-success">
    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" clip-rule="evenodd" d="M18.7071 8.79633C18.7071 10.0523 19.039 10.7925 19.7695 11.6456C20.3231 12.2741 20.5 13.0808 20.5 13.956C20.5 14.8302 20.2128 15.6601 19.6373 16.3339C18.884 17.1417 17.8215 17.6573 16.7372 17.747C15.1659 17.8809 13.5937 17.9937 12.0005 17.9937C10.4063 17.9937 8.83505 17.9263 7.26375 17.747C6.17846 17.6573 5.11602 17.1417 4.36367 16.3339C3.78822 15.6601 3.5 14.8302 3.5 13.956C3.5 13.0808 3.6779 12.2741 4.23049 11.6456C4.98384 10.7925 5.29392 10.0523 5.29392 8.79633V8.3703C5.29392 6.68834 5.71333 5.58852 6.577 4.51186C7.86106 2.9417 9.91935 2 11.9558 2H12.0452C14.1254 2 16.2502 2.98702 17.5125 4.62466C18.3314 5.67916 18.7071 6.73265 18.7071 8.3703V8.79633ZM9.07367 20.0608C9.07367 19.5573 9.53582 19.3266 9.96318 19.2279C10.4631 19.1222 13.5093 19.1222 14.0092 19.2279C14.4366 19.3266 14.8987 19.5573 14.8987 20.0608C14.8738 20.5402 14.5926 20.9653 14.204 21.2352C13.7001 21.628 13.1088 21.8767 12.4906 21.9664C12.1487 22.0107 11.8128 22.0117 11.4828 21.9664C10.8636 21.8767 10.2723 21.628 9.76938 21.2342C9.37978 20.9653 9.09852 20.5402 9.07367 20.0608Z" fill="#200E32"/></svg>
    <p><span>Genial ! </span>Test message de validation</p>
    <svg class="alertClose" width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" clip-rule="evenodd" d="M2.34315 13.6569C-0.781049 10.5327 -0.781049 5.46734 2.34315 2.34315C5.46734 -0.781049 10.5327 -0.781049 13.6569 2.34315C16.781 5.46734 16.781 10.5327 13.6569 13.6569C10.5327 16.781 5.46734 16.781 2.34315 13.6569ZM6.03032 4.96967C5.73743 4.67678 5.26256 4.67678 4.96966 4.96967C4.67677 5.26256 4.67677 5.73744 4.96966 6.03033L6.93933 8L4.96966 9.96967C4.67677 10.2626 4.67677 10.7374 4.96966 11.0303C5.26256 11.3232 5.73743 11.3232 6.03032 11.0303L7.99999 9.06066L9.96966 11.0303C10.2626 11.3232 10.7374 11.3232 11.0303 11.0303C11.3232 10.7374 11.3232 10.2626 11.0303 9.96967L9.06065 8L11.0303 6.03034C11.3232 5.73744 11.3232 5.26257 11.0303 4.96968C10.7374 4.67678 10.2626 4.67678 9.96966 4.96968L7.99999 6.93934L6.03032 4.96967Z" fill="#24292E"/></svg>
</div>