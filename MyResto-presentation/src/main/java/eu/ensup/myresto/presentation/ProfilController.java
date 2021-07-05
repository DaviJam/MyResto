package eu.ensup.myresto.presentation;

import eu.ensup.myresto.dto.UserDTO;
import eu.ensup.myresto.service.ExceptionService;
import eu.ensup.myresto.service.LoggerService;
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

import static eu.ensup.myresto.presentation.Common.errorFlag;

@WebServlet(
        name = "ProfilServlet",
        urlPatterns = "/profile"
)
public class ProfilController extends HttpServlet {
    String className = getClass().getName();

    private LoggerService loggerService = null;
    private UserService service = null;

    @Override
    public void init() throws ServletException {
        super.init();
        loggerService = new LoggerService();
        service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");

        try {
            UserDTO userDTO = service.get(email);
            req.setAttribute("surname", userDTO.getSurname());
            req.setAttribute("firstname", userDTO.getFirstname());
            req.setAttribute("email", userDTO.getEmail());
            req.setAttribute("address", userDTO.getAddress());
            req.setAttribute("id_role", userDTO.getRole());

        } catch (ExceptionService exceptionService) {
            req.setAttribute(errorFlag, "Les informations de l'utilisateur sont momentanément indisponibles.");
            loggerService.logServiceError(className, methodName,"Un problème est survenue lors de l'appel à cette méthode." + exceptionService.getMessage());
        }
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
