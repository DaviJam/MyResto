package eu.ensup.myresto.presentation;

import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.service.ExceptionService;
import eu.ensup.myresto.service.ProductService;
import eu.ensup.myresto.dto.ProductDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;

@WebServlet(
        name = "CardController",
        urlPatterns = "/card"
)

public class CardController extends HttpServlet {

    public CardController() {
        super();
    }
    private ProductService productService = new ProductService();
    private ArrayList<ProductDTO> listProduct = new ArrayList<ProductDTO>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("cards", listProduct);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (Exception e) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //On récupère le produit ajouter au panier
        String product = req.getParameter("product");
        System.out.println(product);

        //On update la variable panier
        ProductDTO p = new ProductDTO();
        try {
            p = productService.get(Integer.parseInt(req.getParameter("product")));
            listProduct.add(p);
        } catch (ExceptionService exceptionService) {
            exceptionService.printStackTrace();
        }

        //On obtient le prix total du panier
        double sum = 0;
        String responseHTML =
                "<h3 class=\"main-title\">Mon panier</h3>\n" +
                "<div class=\"relative px\" style=\"max-height: calc(100% - 232px); overflow-y: auto;\" id=\"containerCardProduct\">";
        for (ProductDTO pUnit: listProduct) {
            sum += pUnit.getPrice();
            responseHTML += "<div class=\"product no-float\">\n" +
                    "           <div class=\"product-body\">\n" +
                    "               <img src=\"" + pUnit.getImage() + "\" alt=\"\">\n" +
                    "               <span>" + pUnit.getName() + "<b> x1</b></span>\n" +
                    "           </div>\n" +
                    "           <img src=\"img/delete.svg\" alt=\"\" class=\"delete-product\">\n" +
                    "      </div>\n" +
                    "      <hr>";
        }

        responseHTML +=
                "</div>" +
                "<h3 class=\"main-title\" style=\"text-align: right; margin: 0;\">Total : " + String.valueOf(sum) + " TTC</h3>\n" +
                "        <div class=\"relative px\">\n" +
                "            <a href=\"#\" class=\"btn\" style=\"margin-top: 25px;\">Valider ma commande</a>\n" +
                "        </div>";

        HttpSession session = req.getSession();
        session.setAttribute("cards", listProduct);
        session.setAttribute("sum", listProduct);


        resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        resp.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        resp.getWriter().write(responseHTML);       // Write response body.
    }
}
