package eu.ensup.myresto.presentation;

import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.dto.UserDTO;
import eu.ensup.myresto.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static eu.ensup.myresto.presentation.Common.errorFlag;
import static eu.ensup.myresto.presentation.Common.succesFlag;

@WebServlet(
        name = "LoginServlet",
        urlPatterns = "/login"
)
public class LoginController extends HttpServlet {
    String className = getClass().getName();
    public LoginController() {
        super();
    }

    private LoggerService loggerService = null;
    private ConnectionService cs = null;
    private UserService ps = null;

    @Override
    public void init() throws ServletException {
        super.init();
        loggerService = new LoggerService();
        cs = new ConnectionService();
        ps = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // show login page
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

        /**
         * Check for user id
         */
        String id = req.getParameter("login");
        String pass = req.getParameter("pwd");

        Role role = null;
        int userId = 0;
        try {
            cs.checkConnection(id, pass);
            UserDTO user = ps.get(id);
            role = user.getRole();
            userId = user.getId();
            req.setAttribute(succesFlag, "Connection réussie!");
        } catch (ExceptionService exceptionService) {
            req.setAttribute(errorFlag, "Le login ou le mot de passe saisi est incorrect.");
            loggerService.logServiceInfo(className, methodName,"Un problème est survenue lors de l'appel à cette méthode." + exceptionService.getMessage());
            // give user another try
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        /**
         * If success create user session
         */
        HttpSession session = req.getSession();
        session.setAttribute("email", id);
        session.setAttribute("id_user", userId);
        session.setAttribute("role", String.valueOf(role.getNum()));

        /**
         * Set the content type
         */
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
