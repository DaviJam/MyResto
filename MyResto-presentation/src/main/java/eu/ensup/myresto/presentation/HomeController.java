package eu.ensup.myresto.presentation;

import eu.ensup.myresto.business.Category;
import eu.ensup.myresto.business.Product;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* source
 .
 |-- pom.xml
 |-- resource2
 |   |-- external-resource.jpg
 |   `-- image2
 |       `-- external-resource2.jpg
 `-- src
     `-- main
         |-- java
         |   `-- com
         |       `-- example
         |           `-- projects
         |               `-- SampleAction.java
         |-- resources
         |   `-- images
         |       `-- sampleimage.jpg
         `-- webapp
             |-- WEB-INF
             |   `-- web.xml
             |-- index.jsp
             `-- jsp
                 `-- websource.jsp
 */

/* Target
documentedproject-1.0-SNAPSHOT.war
 |-- META-INF
 |   |-- MANIFEST.MF
 |   `-- maven
 |       `-- com.example.projects
 |           `-- documentedproject
 |               |-- pom.properties
 |               `-- pom.xml
 |-- WEB-INF
 |   |-- classes
 |   |   |-- com
 |   |   |   `-- example
 |   |   |       `-- projects
 |   |   |           `-- SampleAction.class
 |   |   `-- images
 |   |       `-- sampleimage.jpg
 |   `-- web.xml
 |-- external-resource.jpg
 |-- image2
 |   `-- external-resource2.jpg
 |-- index.jsp
 `-- jsp
     `-- websource.jsp
 */
//https://colorhunt.co/palette/273466
@WebServlet(
        name = "HomeController",
        urlPatterns = "/home"
)
public class HomeController extends HttpServlet {
    private LoggerService productServiceLogger = new LoggerService();
    public HomeController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        try {
            /**
             * Set the content type
             */
            ProductService productService = new ProductService();

            List<ProductDTO> listProduct = productService.getAll();

            List<Category> listCategory = Arrays.asList(Category.values());

            req.setAttribute("listProduct", listProduct);
            req.setAttribute("listCategory", listCategory);

            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (ExceptionService exceptionService) {
           productServiceLogger.logServiceError(exceptionService.getClass().getName(), "doGet Home controller", "Une erreur est survenue dans la récupération de la liste des produits");
        }

    }
}
