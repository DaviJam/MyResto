package eu.ensup.myresto.presentation;

import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.service.ExceptionService;
import eu.ensup.myresto.service.LoggerService;
import eu.ensup.myresto.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet(
        name = "StockController",
        urlPatterns = "/manage_stock"
)

public class StockController extends HttpServlet {

    private LoggerService loggerService = new LoggerService();

    public StockController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            ProductService productService = new ProductService();
            List<ProductDTO> listProduct = productService.getAll();

            req.setAttribute("listProduct", listProduct);
            req.getRequestDispatcher("manage_stock.jsp").forward(req, resp);
        } catch (ExceptionService exceptionService) {
            exceptionService.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("INSIDE DOPOST OF STOCK CONTROLLER");
        try {
            System.out.println("INSIDE DOPOST OF STOCK CONTROLLER");
            ProductService productService = new ProductService();
            Enumeration keys = req.getParameterNames();
            while (keys.hasMoreElements() ) {
                String key = (String) keys.nextElement();
                int stockValue = Integer.parseInt( req.getParameter(key) );

                System.out.println("INSIDE DOPOST OF STOCK CONTROLLER");

                ProductDTO productToUpdate = productService.get(Integer.parseInt(key));
                productToUpdate.setStock(stockValue);

                System.out.println("before productService update");
                productService.update(productToUpdate);
                System.out.println("after productService update");
            }
        } catch (ExceptionService exceptionService) {
            System.out.println(exceptionService.getMessage());
            exceptionService.printStackTrace();
            loggerService.logServiceError(exceptionService.getClass().getName(), "doPost stock controller", "La modification du stock n'as pus être effectué");
        }
        resp.sendRedirect(req.getContextPath() + "/manage_stock");
    }
}