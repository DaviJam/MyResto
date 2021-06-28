package eu.ensup.gestionetablissement.presentation;

import eu.ensup.gestionetablissement.business.Role;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.service.ConnectionService;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.PersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "LoginServlet",
        urlPatterns = "/login"
)
public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }
    private String succesFlag = "success";
    private String errorFlag = "error";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher;
        /**
         * Set the content type
         */
        requestDispatcher = req.getRequestDispatcher("connexion.jsp");

        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * Check for user id
         */
        String id = req.getParameter("Ident");
        String pass = req.getParameter("Password");

        RequestDispatcher requestDispatcher;

        ConnectionService cs = new ConnectionService();
        PersonService ps = new PersonService();
        Role role = null;
        try {
            cs.checkConnection(id, pass);
            PersonDTO person = ps.get(id);
            role = person.getRole();

        } catch (ExceptionService exceptionService) {
            req.setAttribute(errorFlag, exceptionService.getMessage());
            requestDispatcher = req.getRequestDispatcher("connexion.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }

        /**
         * If success create user session
         */
        HttpSession session = req.getSession();
        session.setAttribute("user-id", id);
        session.setAttribute("role", role.getNum());

        /**
         * Set the content type
         */
        resp.sendRedirect(req.getContextPath() + "/accueil");
//        req.getRequestDispatcher("/accueil");
//
//        requestDispatcher.forward(req, resp);

    }
}
