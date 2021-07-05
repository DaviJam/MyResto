package eu.ensup.myresto.presentation;

import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.dto.UserDTO;
import eu.ensup.myresto.service.ConnectionService;
import eu.ensup.myresto.service.ExceptionService;
import eu.ensup.myresto.service.LoggerService;
import eu.ensup.myresto.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static eu.ensup.myresto.presentation.Common.errorFlag;

@WebServlet(
        name = "SignupServlet",
        urlPatterns = "/register"
)
public class SignupController extends HttpServlet {
    String className = getClass().getName();

    private LoggerService loggerService = null;
    private UserService userService = null;

    public SignupController() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        loggerService = new LoggerService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Show registration page
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        /**
         * Check for user id
         */
        String surname = req.getParameter("surname");
        String firstname = req.getParameter("firstname");
        String email = req.getParameter("email");
        String pass = req.getParameter("pwd");
        String address = req.getParameter("address");

        UserDTO userDto = new UserDTO(surname, firstname, Role.CLIENT, email, pass, address);
        try {
            userService.create(userDto);
        } catch (ExceptionService exceptionService) {
            req.setAttribute(errorFlag,"Echec lors de la création d'un compte utilisateur.");
            loggerService.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode." + exceptionService.getMessage());
            // give user another try
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        /**
         * Redirect to login page
         */
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
