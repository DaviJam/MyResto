package eu.ensup.gestionetablissement.presentation;

import eu.ensup.gestionetablissement.service.ConnectionService;
import eu.ensup.gestionetablissement.service.ExceptionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "LogoutServlet",
        urlPatterns = "/logout"
)
public class LogoutController extends HttpServlet {

    public LogoutController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        /**
         * If success clear user session
         */
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        /**
         * Set the content type
         */
         resp.sendRedirect("index.jsp");
    }
}
