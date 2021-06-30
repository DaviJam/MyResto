package eu.ensup.myresto.presentation;

import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.service.ExceptionService;
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
        urlPatterns = "/gestion-stock"
)

public class StockController extends HttpServlet {

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //try {
            Enumeration keys = req.getParameterNames();
            while (keys.hasMoreElements() ) {
                String key = (String) keys.nextElement();
                System.out.println("key : " + key);

                String vCode = key.substring(key.indexOf("_") + 1);
                System.out.println("vCode : " + vCode);

                String[] arrayParameters = req.getParameterValues(key);
                System.out.println("arrayParameters.length : " + arrayParameters.length);
            }

            /*int product_id = Integer.parseInt( req.getParameter("product_id") );
            int stock = Integer.parseInt( req.getParameter("stock") );
            ProductService productService = new ProductService();

            ProductDTO productToUpdate = productService.get(product_id);
            productToUpdate.setStock(stock);
            productService.update(productToUpdate);*/

            resp.sendRedirect("/gestion_stock");
        /*} catch (ExceptionService exceptionService) {
            exceptionService.printStackTrace();
        }*/



    }
}
