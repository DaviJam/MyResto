package eu.ensup.myresto.presentation;

import com.sun.tools.jconsole.JConsolePlugin;
import eu.ensup.myresto.business.Category;
import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.service.ExceptionService;
import eu.ensup.myresto.service.LoggerService;
import eu.ensup.myresto.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(
        name = "ProductController",
        urlPatterns = "/product/*"
)

public class ProductController extends HttpServlet {

    private LoggerService loggerService = null;

    @Override
    public void init() throws ServletException {
        super.init();
        loggerService = new LoggerService();
    }

    public ProductController() {
        super();
    }
    private LoggerService productServiceLogger = new LoggerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ProductService productService = new ProductService();

                String urlId = req.getPathInfo();
                System.out.println("urlId " + urlId);

                int productId = Integer.parseInt(urlId.replace("/",""));
                System.out.println("productId " + productId);

                ProductDTO productDTO = productService.getProductById(1);
                System.out.println("productDTO " + productDTO);

                req.setAttribute("productDTO", productDTO);
                System.out.println("productDTO req " + req.getParameter("productDTO"));

                req.getRequestDispatcher("product.jsp").forward(req, resp);


        } catch (ExceptionService exceptionService) {
            System.out.println("ça passe pas chef");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        requestDispatcher = req.getRequestDispatcher("product.jsp");
        requestDispatcher.forward(req, resp);
    }
}
