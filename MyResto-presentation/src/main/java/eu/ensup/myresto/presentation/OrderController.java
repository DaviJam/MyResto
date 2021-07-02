package eu.ensup.myresto.presentation;

import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.dto.OrderDTO;
import eu.ensup.myresto.service.ExceptionService;
import eu.ensup.myresto.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(
        name = "OrderServlet",
        urlPatterns = {
                "/order/create", // POST - create a basket
                "/order/validate", // POST - validate a basket which becomes an order
                "/order/update", // POST - update current basket
                "/order/cancel", // POST - cancel current basket
                "/orders/show" // GET - show all orders
        }
)
public class OrderController extends HttpServlet {
    public OrderController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleMethods(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleMethods(req, resp);
    }

    private void handleMethods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkUser(req, resp)) {
            return;
        }
        switch (req.getRequestURI()) {
            case "/myresto/order/create": {
                create(req, resp);
            }
            case "/myresto/order/validate": {
                validate(req, resp);
            }
            case "/myresto/order/cancel": {
                cancel(req, resp);
            }
            case "/myresto/order/update": {
                update(req, resp);
            }
            case "/myresto/orders/show": {
                show(req, resp);
            }
        }
    }

    boolean checkUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean res = true;
        HttpSession checkUserSession = req.getSession(false);
        if (checkUserSession.getAttribute("email") == null) {
            System.out.println(req.getMethod() + " Redirected to home no user logged in");
            resp.sendRedirect("/myresto/home");
            res = false;
        }
        return res;
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getMethod().equals("POST"))
        {
            // Create a basket in session
            OrderDTO currentOrder = new OrderDTO();
            req.getSession().setAttribute("basket", currentOrder);
            resp.sendRedirect("/myresto/order/show");
        }
    }


    private void validate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getMethod().equals("POST"))
        {
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getMethod().equals("POST"))
        {
        }
    }

    private void cancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getMethod().equals("POST"))
        {
        }
    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getMethod().equals("GET"))
        {
            System.out.println("GET Methods");
            HttpSession session = req.getSession();
            session.setAttribute("email", "youness@gmail.com"); //TODO VALEUR DE TEST SANS SESSION
            session.setAttribute("role", "1"); //TODO VALEUR DE TEST SANS SESSION

            String emailuser = (String) session.getAttribute("email");

            OrderService orderService = new OrderService();
            List<OrderDTO> orderlist = new ArrayList<OrderDTO>();

            try { // Récupération de toutes les commandes
                orderlist = orderService.getAll();
            } catch (ExceptionService exceptionService) {
                exceptionService.printStackTrace();
            }
            // Si l'utilisateur est un client, on trie les commandes pour ne récupérer que les siennes
            if((String) session.getAttribute("role") == "2"){ // Si c'est un client, on trie les commandes
                List<OrderDTO> neworderlist = new ArrayList<OrderDTO>();
                for(OrderDTO o : orderlist){
                    if(o.getUser().getEmail().equals(emailuser))
                    {
                        neworderlist.add(o);
                    }
                }
                req.setAttribute("orderlist", neworderlist);
            }
            else
            {
                req.setAttribute("orderlist", orderlist);
            }

            req.getRequestDispatcher("manage_orders.jsp").forward(req, resp);
        }

    }
}
