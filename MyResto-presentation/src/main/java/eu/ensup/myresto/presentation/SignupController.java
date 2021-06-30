package eu.ensup.myresto.presentation;

import eu.ensup.myresto.business.Role;
import eu.ensup.myresto.dto.UserDTO;
import eu.ensup.myresto.service.ConnectionService;
import eu.ensup.myresto.service.ExceptionService;
import eu.ensup.myresto.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "SignupServlet",
        urlPatterns = "/register"
)
public class SignupController extends HttpServlet {

    public SignupController() {
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
        requestDispatcher = req.getRequestDispatcher("register.jsp");

        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Check for user id
         */
        String surname = req.getParameter("surname");
        String firstname = req.getParameter("firstname");
        String email = req.getParameter("email");
        String pass = req.getParameter("pwd");
        String address = req.getParameter("address");

        RequestDispatcher requestDispatcher;
        UserDTO userDto = new UserDTO(surname, firstname, Role.CLIENT, email, pass, address);
        UserService userService = new UserService();
        try {
            userService.create(userDto);
        } catch (ExceptionService exceptionService) {
            req.setAttribute(errorFlag, exceptionService.getMessage());
            requestDispatcher = req.getRequestDispatcher("register.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }

        /**
         * Set the content type
         */
        requestDispatcher = req.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(req, resp);
    }
}
