package eu.ensup.myresto.presentation;

import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.service.LoggerService;
import eu.ensup.myresto.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "ProductController",
        urlPatterns = "/product/*"
)

public class ProductController extends HttpServlet {

    private LoggerService loggerService;

    @Override
    public void init() throws ServletException {
        super.init();
        loggerService = new LoggerService();
    }

    public ProductController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws SecurityException {
        try {
                ProductService productService = new ProductService();

                String urlId = req.getPathInfo();
                Integer.parseInt(urlId.replace("/",""));
                ProductDTO productDTO = productService.getProductById(1);
                req.setAttribute("productDTO", productDTO);
                req.getRequestDispatcher("product.jsp").forward(req, resp);


        } catch (Exception exception) {

            loggerService.logServiceError(this.getClass().getName(),"doGet", "ça passe pas chef"+exception.getMessage());
            try {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } catch(Exception ex) {
                loggerService.logServiceError(this.getClass().getName(),"doGet", ex.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher;
        requestDispatcher = req.getRequestDispatcher("product.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch( Exception e){
            loggerService.logServiceError(this.getClass().getName(),"doPost", e.getMessage());
        }
    }
}
