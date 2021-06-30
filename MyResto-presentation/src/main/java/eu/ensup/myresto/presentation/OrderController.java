package eu.ensup.myresto.presentation;

import eu.ensup.myresto.business.Order;
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

@WebServlet(
        name = "OrderServlet",
        urlPatterns = "/orders"
)
public class OrderController extends HttpServlet {
    public OrderController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        requestDispatcher = req.getRequestDispatcher("manage_orders.jsp");

        HttpSession session = req.getSession();
        session.setAttribute("email", "youness@gmail.com"); // VALEUR DE TEST SANS SESSION
        session.setAttribute("role", "2"); // VALEUR DE TEST SANS SESSION
        String emailuser = (String) session.getAttribute("email");
        OrderService orderService = new OrderService();
        List<OrderDTO> orderlist = new ArrayList<OrderDTO>();

        try { // Récupération de toutes les commandes
            orderlist = orderService.getAll();
        } catch (ExceptionService exceptionService) {
            // exceptionService.printStackTrace();
        }
        // Si l'utilisateur est un client, on trie les commandes pour ne récupérer que les siennes
        if((String) session.getAttribute("role") == "1"){ // Si c'est un client, on trie les commandes
            List<OrderDTO> neworderlist = new ArrayList<OrderDTO>();
            for(OrderDTO o : orderlist){
                if(o.getUser().getEmail() == emailuser)
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

        // Récupération du prix total
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
