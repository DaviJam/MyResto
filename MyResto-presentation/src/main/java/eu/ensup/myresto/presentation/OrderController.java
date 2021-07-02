package eu.ensup.myresto.presentation;

import eu.ensup.myresto.business.Category;
import eu.ensup.myresto.business.Order;
import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.business.Status;
import eu.ensup.myresto.dto.OrderDTO;
import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.dto.StatusDTO;
import eu.ensup.myresto.service.ExceptionService;
import eu.ensup.myresto.service.OrderService;
import eu.ensup.myresto.service.ProductService;
import eu.ensup.myresto.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(
        name = "OrderServlet",
        urlPatterns = {
                "/order_create", // GET - create a basket
                "/order_cancel", // POST - cancel current basket
                "/order_close", // POST - close current order
                "/orders_show", // GET - show all orders
                "/order_inprogress" // POST - order in progress
        }
)
public class OrderController extends HttpServlet {
    OrderService orderService = null;

    public OrderController() {
        super();
        orderService = new OrderService();
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
            case "/myresto/order_create": {
                create(req, resp);
            }
            case "/myresto/order_cancel": {
                cancel(req, resp);
            }
            case "/myresto/orders_show": {
                show(req, resp);
            }
            case "/myresto/order_close": {
                close(req, resp);
            }
            case "/myresto/order_inprogress": {
                inProgress(req, resp);
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
        if(req.getMethod().equals("GET"))
        {
            // list id_product List(1,2,2,2,2)
            // for(
            // id_user

            List<ProductDTO> list_product = (List<ProductDTO>) req.getSession(false).getAttribute("cards");
            HashMap<Integer, ProductDTO> productMap = new HashMap<>();
            for(ProductDTO p : list_product)
            {
                if(! productMap.containsKey(p.getId())) {
                    p.setStock(1);
                    productMap.put(p.getId(), p);
                } else {
                    p.setStock(productMap.get(p.getId()).getStock() + 1);
                    productMap.put(p.getId(), p);
                }
            }
            List<ProductDTO> list_product_updated = new ArrayList<>();
            productMap.forEach((key, value) -> list_product_updated.add(value));

            String id_user = (String) req.getSession(false).getAttribute("email");
            try {
                // Creation de l'order
                OrderDTO orderDTO = new OrderDTO(new UserService().get(id_user), list_product_updated, new java.sql.Date(Calendar.getInstance().getTime().getTime()), Status.ENATTENTE);

                orderService.create(orderDTO);

                //resp.sendRedirect("/myresto/orders_show");
                req.getRequestDispatcher("manage_orders.jsp").forward(req, resp);

            } catch (ExceptionService exceptionService) {
                exceptionService.printStackTrace();
            }
        }
    }

    private void inProgress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getMethod().equals("POST"))
        {
            // get order id
            int id_order = Integer.parseInt(req.getParameter("id_order"));

            // update order status
            try {
                orderService.update(id_order, StatusDTO.ENCOURS);
                resp.sendRedirect("/myresto/order_show");
            } catch (ExceptionService exceptionService) {
                System.out.println("order InProgress exception" + exceptionService.getMessage());
            }
        }
    }

    private void close(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getMethod().equals("POST"))
        {
            // get order id
            int id_order = Integer.parseInt(req.getParameter("id_order"));

            // update order status
            try {
                orderService.update(id_order, StatusDTO.TERMINE);
                resp.sendRedirect("/myresto/order_show");
            } catch (ExceptionService exceptionService) {
                System.out.println("order close exception" + exceptionService.getMessage());
            }
        }
    }

    private void cancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getMethod().equals("POST"))
        {
            // get order id
            int id_order = Integer.parseInt(req.getParameter("id_order"));

            // update order status
            try {
                orderService.update(id_order, StatusDTO.ANNULE);
                resp.sendRedirect("/myresto/order_show");
            } catch (ExceptionService exceptionService) {
                System.out.println("order Cancel exception" + exceptionService.getMessage());
            }
        }
    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getMethod().equals("GET"))
        {
            HttpSession session = req.getSession(false);

            String emailuser = (String) session.getAttribute("email");


            List<OrderDTO> orderlist = new ArrayList<OrderDTO>();

            try { // Récupération de toutes les commandes
                orderlist = orderService.getAll();
            } catch (ExceptionService exceptionService) {
                exceptionService.printStackTrace();
            }

            // Si l'utilisateur est un client, on trie les commandes pour ne récupérer que les siennes
            if(session.getAttribute("role").equals("2")){ // Si c'est un client, on trie les commandes

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
                Comparator<OrderDTO> compareByStatusAsc = Comparator.comparing(OrderDTO::getStatus);
                orderlist.sort(compareByStatusAsc);
                req.setAttribute("orderlist", orderlist);
            }

            req.getRequestDispatcher("manage_orders.jsp").forward(req, resp);
        }
    }
}
