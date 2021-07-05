package eu.ensup.myresto.presentation;
import eu.ensup.myresto.business.Status;
import eu.ensup.myresto.dto.OrderDTO;
import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.dto.RoleDTO;
import eu.ensup.myresto.dto.StatusDTO;
import eu.ensup.myresto.service.ExceptionService;
import eu.ensup.myresto.service.LoggerService;
import eu.ensup.myresto.service.OrderService;
import eu.ensup.myresto.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import static eu.ensup.myresto.presentation.Common.errorFlag;
import static eu.ensup.myresto.presentation.Common.succesFlag;

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
    String className = getClass().getName();

    private OrderService orderService = null;
    private LoggerService loggerService = null;

    public OrderController() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        loggerService = new LoggerService();
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
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        if (!checkUser(req, resp)) {
            return;
        }
        switch (req.getRequestURI()) {
            case "/myresto/order_create": {
                create(req, resp);
                break;
            }
            case "/myresto/order_cancel": {
                cancel(req, resp);
                break;
            }
            case "/myresto/orders_show": {
                show(req, resp);
                break;
            }
            case "/myresto/order_close": {
                close(req, resp);
                break;
            }
            case "/myresto/order_inprogress": {
                inProgress(req, resp);
                break;
            }
            default:
                loggerService.logServiceError(className, methodName,"Unknown Route name requested");
                break;
        }
    }

    boolean checkUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        boolean res = true;
        HttpSession checkUserSession = req.getSession(false);
        if (checkUserSession.getAttribute("email") == null) {
            req.setAttribute(errorFlag, "Please log in to proceed.");
            loggerService.logServiceError(className, methodName,"L'utilisation tente d'effectuer une action nécessitant un compte utilisateur");
            resp.sendRedirect(req.getContextPath()+"/login");
            res = false;
        }
        return res;
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        if(req.getMethod().equals("GET"))
        {
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
                OrderDTO orderDTO = new OrderDTO(new UserService().get(id_user), list_product_updated, java.util.Date.from(Calendar.getInstance().toInstant()), Status.ENATTENTE);
                orderService.create(orderDTO);
                req.getSession().setAttribute(succesFlag, "La commande a été créée avec succès");
                resp.sendRedirect(req.getContextPath()+"/orders_show");
            } catch (ExceptionService exceptionService) {
                loggerService.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode."+ exceptionService.getMessage());
                req.getSession().setAttribute(errorFlag, "Erreur serveur. La création de la commande a échoué.");
            }
        }
    }

    private void inProgress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        if(req.getMethod().equals("POST"))
        {
            // get order id
            int id_order = Integer.parseInt(req.getParameter("id_order"));

            // update order status and product stock
            try {
                orderService.update(id_order, StatusDTO.ENCOURS);
                req.getSession().setAttribute(succesFlag, "La commande " + id_order + " a été mise à jours avec succès.");
                resp.sendRedirect(req.getContextPath()+"/update_stock?id="+id_order);
            } catch (ExceptionService exceptionService) {
                loggerService.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode."+ exceptionService.getMessage());
                req.getSession().setAttribute(errorFlag, "Erreur serveur. La modification du status de la commande a échoué.");
            }
        }
    }

    private void close(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        if(req.getMethod().equals("POST"))
        {
            // get order id
            int id_order = Integer.parseInt(req.getParameter("id_order"));

            // update order status
            try {
                orderService.update(id_order, StatusDTO.TERMINE);
                req.getSession().setAttribute(succesFlag, "La commande " + id_order + " a été mis à jours avec succès");
                resp.sendRedirect(req.getContextPath()+"/orders_show");
            } catch (ExceptionService exceptionService) {
                loggerService.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode." + exceptionService.getMessage());
                req.getSession().setAttribute(errorFlag, "Erreur serveur. La modification du status de la commande a échoué.");
            }
        }
    }

    private void cancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        if(req.getMethod().equals("POST"))
        {
            // get order id
            int id_order = Integer.parseInt(req.getParameter("id_order"));

            // update order status
            try {
                orderService.update(id_order, StatusDTO.ANNULE);
                req.getSession().setAttribute(succesFlag, "La commande " + id_order + " a été annulé avec succès.");
                resp.sendRedirect(req.getContextPath()+"/orders_show");
            } catch (ExceptionService exceptionService) {
                loggerService.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode." + exceptionService.getMessage());
                req.getSession().setAttribute(errorFlag, "Erreur serveur. La modification du status de la commande a échoué.");
            }
        }
    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        if(req.getMethod().equals("GET"))
        {
            HttpSession session = req.getSession(false);
            String emailuser = (String) session.getAttribute("email");

            List<OrderDTO> orderlist = new ArrayList<OrderDTO>();

            try { // Récupération de toutes les commandes
                orderlist = orderService.getAll();
            } catch (ExceptionService exceptionService) {
                loggerService.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode." + exceptionService.getMessage());
                req.getSession().setAttribute(errorFlag, "Erreur serveur. L'affichage des commandes est indisponible pour le moment.");
            }
            // Si l'utilisateur est un client, on trie les commandes pour ne récupérer que les siennes
            if(session.getAttribute("role").equals(String.valueOf(RoleDTO.CLIENT.getNum()))) { // Si c'est un client, on trie les commandes
                orderlist = orderlist.stream().filter(order -> order.getUser().getEmail().equals(emailuser)).collect(Collectors.toList());
                Comparator<OrderDTO> compareByDateAsc = Comparator.comparing(OrderDTO::getOrder_date).reversed();
                orderlist.sort(compareByDateAsc);
            }else{
                Comparator<OrderDTO> compareByStatusDesc = Comparator.comparing(OrderDTO::getStatus);
                orderlist.sort(compareByStatusDesc);
            }
            // Compute time since order and it to the map
            LinkedHashMap<OrderDTO, LinkedHashMap<String, String>> sinceTimeList = new LinkedHashMap<>();
            orderlist.forEach(e -> {
                var w_map = new LinkedHashMap<String, String>();
                var dateDiff = Date.from(Instant.now().minusSeconds(e.getOrder_date().toInstant().getEpochSecond()));
                var calendar = new Calendar.Builder().setInstant(dateDiff).build();

                w_map.put("hour", String.valueOf(calendar.get(Calendar.HOUR)-1));
                w_map.put("minute", String.valueOf(calendar.get(Calendar.MINUTE)));
                sinceTimeList.put(e, w_map);
            });

            req.setAttribute("orderlist", sinceTimeList);
            req.getRequestDispatcher("manage_orders.jsp").forward(req, resp);
        }
    }
}
