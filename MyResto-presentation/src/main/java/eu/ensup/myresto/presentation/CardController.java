package eu.ensup.myresto.presentation;

import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.service.ExceptionService;
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
        name = "CardController",
        urlPatterns = "/card"
)

public class CardController extends HttpServlet {

    public CardController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            ProductService productService = new ProductService();
            List<ProductDTO> listProduct = productService.getAll();

            req.setAttribute("listProduct", listProduct);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (ExceptionService exceptionService) {
            exceptionService.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //On récupère le produit ajouter au panier
        String product = req.getParameter("product");
        System.out.println(product);
        //On update la variable panier
        HttpSession session = req.getSession();
        session.setAttribute("cards", "");

        //On affiche la vue
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
