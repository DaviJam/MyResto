package eu.ensup.gestionetablissement.presentation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        urlPatterns = "/accueil"
)
public class HomeController extends HttpServlet {

    public HomeController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher;
        /**
         * Set the content type
         */
        requestDispatcher = req.getRequestDispatcher("index.jsp");

        requestDispatcher.forward(req, resp);

    }
}
