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
        requestDispatcher = req.getRequestDispatcher("login.jsp");

        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * Check for user id
         */
        String id = req.getParameter("login");
        String pass = req.getParameter("pwd");

        System.out.println(id +" "+ pass);

        RequestDispatcher requestDispatcher;

        ConnectionService cs = new ConnectionService();
        UserService ps = new UserService();
        Role role = null;
        try {
            cs.checkConnection(id, pass);
            UserDTO user = ps.get(id);
            role = user.getRole();

        } catch (ExceptionService exceptionService) {
            req.setAttribute(errorFlag, exceptionService.getMessage());
            requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }

        /**
         * If success create user session
         */
        HttpSession session = req.getSession();
        session.setAttribute("email", id);
        session.setAttribute("role", String.valueOf(role.getNum()));

        /**
         * Set the content type
         */
        resp.sendRedirect(req.getContextPath() + "/home");
        //requestDispatcher = req.getRequestDispatcher("index.jsp");
        //requestDispatcher.forward(req, resp);
    }
}
