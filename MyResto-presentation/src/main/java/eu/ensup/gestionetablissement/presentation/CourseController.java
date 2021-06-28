package eu.ensup.gestionetablissement.presentation;


import eu.ensup.gestionetablissement.business.Role;
import eu.ensup.gestionetablissement.dto.CourseDTO;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.service.CourseService;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(
    name = "CourseController",
    urlPatterns = {
        "/course_menu",
        "/course_add",
        "/course_link"
    }
)
public class CourseController extends HttpServlet {
    private String successFlag = "success";
    private String errorFlag = "error";
    /**
     * Instantiates a new Student controller.
     */
    public CourseController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleMethods(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleMethods(req, resp);
    }

    private void handleMethods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkUser(req,resp)) {
            return;
        }
        RequestDispatcher requestDispatcher;

        switch(req.getRequestURI()) {
            case "/etablissement/course_menu":  {
                if(req.getMethod().equals("GET"))
                {
                    try {
                        req.setAttribute("courses", getAllCourse() );
                        req.setAttribute("students", getAllStudent());
                    }
                    catch(Exception e) {
                        requestDispatcher = req.getRequestDispatcher("course.jsp");
                        requestDispatcher.forward(req, resp);
                        return;
                    }

                    requestDispatcher = req.getRequestDispatcher("course.jsp");
                    requestDispatcher.forward(req, resp);
                }
            }
            case "/etablissement/course_add":  {
                if(req.getMethod().equals("POST"))
                {
                    try {
                        CourseService cs = new CourseService();
                        String theme = req.getParameter("theme");
                        float nbHours = Float.parseFloat(req.getParameter("nbHours"));
                        CourseDTO course = new CourseDTO(theme, nbHours);
                        cs.create(course);
                        req.setAttribute(successFlag, "Cours ajouté avec succès");
                        req.setAttribute("courses", getAllCourse() );
                        req.setAttribute("students", getAllStudent());
                    }
                    catch(Exception e)
                    {
                        req.setAttribute(errorFlag, e.getMessage());
                        requestDispatcher = req.getRequestDispatcher("course.jsp");
                        requestDispatcher.forward(req, resp);
                        return;
                    }

                    requestDispatcher = req.getRequestDispatcher("course.jsp");
                    requestDispatcher.forward(req, resp);

                    return;
                }
            }
            case "/etablissement/course_link":  {
                if(req.getMethod().equals("POST"))
                {
                    int student_id = Integer.parseInt(req.getParameter("student_id"));
                    int course_id = Integer.parseInt(req.getParameter("course_id"));

                    PersonService ps = null;
                    try {
                        req.setAttribute("courses", getAllCourse() );
                        req.setAttribute("students", getAllStudent());
                        ps = new PersonService();
                        ps.linkToCourse(student_id, course_id);
                        req.setAttribute(successFlag, "Etudiant lié au cours avec succès");
                    }
                    catch(Exception e)
                    {
                        req.setAttribute(errorFlag, e.getMessage());
                        requestDispatcher = req.getRequestDispatcher("course.jsp");
                        requestDispatcher.forward(req, resp);
                        return;
                    }

                    requestDispatcher = req.getRequestDispatcher("course.jsp");
                    requestDispatcher.forward(req, resp);
                    return;
                }
            }
        }
    }

    boolean checkUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean res = true;
        HttpSession checkUserSession = req.getSession(false);
        if (checkUserSession != null && checkUserSession.getAttribute("user-id") == null) {
            System.out.println(req.getMethod() + " Redirected to home no user logged in");
            resp.sendRedirect("/etablissement/accueil");
            res = false;
        }
        return res;
    }

    List<CourseDTO> getAllCourse() throws ExceptionService {
        CourseService cs = null;
        cs = new CourseService();
        return cs.getAll();
    }

    List<StudentDTO> getAllStudent() throws ExceptionService {
        PersonService ps = null;
        ps = new PersonService();
        List<PersonDTO> personList = ps.getAll();
        List<StudentDTO> studentList = new ArrayList<>();
        personList.forEach((person) -> {
            if (person.getRole() == Role.STUDENT) {
                studentList.add((StudentDTO) person);
            }
        });
        return studentList;
    }

}
