package eu.ensup.myresto.presentation;

import eu.ensup.myresto.dao.ExceptionDao;
import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.service.ExceptionService;
import eu.ensup.myresto.service.LoggerService;
import eu.ensup.myresto.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet(
        name = "StockController",
        urlPatterns = {
                "/manage_stock",
                "/update_stock/*"
        }
)

public class StockController extends HttpServlet {

    String className = getClass().getName();

    private LoggerService loggerService = null;
    private ProductService productService = null;

    public StockController() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        loggerService = new LoggerService();
        productService = new ProductService();
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
        if (req.getRequestURI().contains("/myresto/manage_stock")) {
            show(req, resp);
        } else if (req.getRequestURI().contains("/myresto/update_stock")){
            update(req, resp);
        }else {
            loggerService.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode.");
            req.getSession().setAttribute("error", "Erreur serveur. La page est indisponible pour le moment.");
        }
    }

    boolean checkUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean res = true;
        HttpSession checkUserSession = req.getSession(false);
        if (checkUserSession.getAttribute("email") == null) {
            req.setAttribute("error", "Please log in to proceed.");
            resp.sendRedirect(req.getContextPath()+"/login");
            res = false;
        }
        return res;
    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        switch(req.getMethod()){
            case "POST" :{
                try {
                    Enumeration keys = req.getParameterNames();
                    while (keys.hasMoreElements() ) {
                        String key = (String) keys.nextElement();
                        int stockValue = Integer.parseInt( req.getParameter(key) );

                        ProductDTO productToUpdate = productService.get(Integer.parseInt(key));
                        productToUpdate.setStock(stockValue);

                        productService.update(productToUpdate);
                    }
                } catch (ExceptionService exceptionService) {
                    loggerService.logServiceError(className, methodName, "POST - La modification du stock n'as pus être effectué");
                    req.getSession().setAttribute("error", "Erreur serveur. Echec lors de la modification du stock. Veuillez contacter votre administrateur");
                }
                resp.sendRedirect(req.getContextPath() + "/manage_stock");
                break;
            }
            case "GET" : {
                try {
                    List<ProductDTO> listProduct = productService.getAll();

                    req.setAttribute("listProduct", listProduct);
                    req.getRequestDispatcher("manage_stock.jsp").forward(req, resp);
                } catch (ExceptionService exceptionService) {
                    req.getSession().setAttribute("error", "Erreur serveur. La page est indisponible pour le moment.");
                    loggerService.logServiceError(className,methodName,"GET - Un problème est survenue lors de l'appel à cette méthode.");
                }
                break;
            }
        }

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        switch(req.getMethod()) {
            case "POST": {
                resp.sendRedirect(req.getContextPath()+ "/orders_show");
                break;
            }
            case "GET": {
                String id_order = req.getQueryString();
                int orderId = Integer.parseInt(id_order.split("=")[1]);
                try {
                    var productsinfo = productService.getOrderProducts(orderId);
                    productsinfo.forEach(info->{
                        try {
                            productService.updateStock(info.getValue0(), (info.getValue1() - info.getValue2()));
                        } catch (ExceptionDao | ExceptionService exception) {
                            req.getSession().setAttribute("error", "Erreur serveur. La page est indisponible pour le moment.");
                            loggerService.logServiceError(className, methodName ,"GET - Un problème est survenue lors de l'appel à cette méthode.");
                        }
                    });
                } catch (ExceptionService exceptionService) {
                    req.getSession().setAttribute("error", "Erreur serveur. La page est indisponible pour le moment.");
                    loggerService.logServiceError(className, methodName ,"GET - Un problème est survenue lors de l'appel à cette méthode.");
                }
                resp.sendRedirect(req.getContextPath()+ "/orders_show");
                break;
            }
        }
    }

}