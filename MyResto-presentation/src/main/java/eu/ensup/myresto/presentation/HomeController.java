package eu.ensup.myresto.presentation;

import eu.ensup.myresto.business.Category;
import eu.ensup.myresto.dto.ProductDTO;
import eu.ensup.myresto.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static eu.ensup.myresto.presentation.Common.errorFlag;

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
             |-- home.jsp
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
 |-- home.jsp
 `-- jsp
     `-- websource.jsp
 */
//https://colorhunt.co/palette/273466
@WebServlet(
        name = "HomeController",
        value = "/home"
)
public class HomeController extends HttpServlet {
    String className = getClass().getName();

    private LoggerService loggerService = null;

    @Override
    public void init() throws ServletException {
        super.init();
        loggerService = new LoggerService();
    }

    public HomeController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            /**
             * Set the content type
             */
            ProductService productService = new ProductService();
            List<ProductDTO> listProduct = productService.getAll();
            List<Category> listCategory = Arrays.asList(Category.values());

            req.setAttribute("listProduct", listProduct);
            req.setAttribute("listCategory", listCategory);

            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } catch (ExceptionService exceptionService) {
            req.setAttribute(errorFlag, "Le chargement des produits a échoué.");
            loggerService.logServiceInfo(className, methodName,"Un problème est survenue lors de l'appel à cette méthode." + exceptionService.getMessage());
        }
    }
}
