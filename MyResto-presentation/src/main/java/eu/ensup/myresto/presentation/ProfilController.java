package eu.ensup.myresto.presentation;

import eu.ensup.myresto.dto.UserDTO;
import eu.ensup.myresto.service.ExceptionService;
import eu.ensup.myresto.service.OrderService;
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
        name = "ProfilServlet",
        urlPatterns = "/profile"
)
public class ProfilController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        requestDispatcher = req.getRequestDispatcher("profile.jsp");

        HttpSession session = req.getSession();
        session.setAttribute("email", "Lacomblez.thomas@gmail.com");
        String email = (String) session.getAttribute("email");

        req.setAttribute("test", "test");

        UserService service = new UserService();
        try {
            UserDTO userDTO = service.get("Lacomblez.thomas@gmail.com");
            req.setAttribute("id_user", userDTO.getId());
            req.setAttribute("surname", userDTO.getSurname());
            req.setAttribute("firstname", userDTO.getFirstname());
            req.setAttribute("email", userDTO.getEmail());
            req.setAttribute("password", userDTO.getPassword());
            req.setAttribute("address", userDTO.getAddress());
            req.setAttribute("id_role", userDTO.getRole());

        } catch (ExceptionService exceptionService) {
            // TODO A CHANGER !!!!!!!!!!!!!
            exceptionService.printStackTrace();
        }
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
