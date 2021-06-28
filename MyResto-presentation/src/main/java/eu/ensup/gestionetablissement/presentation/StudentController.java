package eu.ensup.gestionetablissement.presentation;

import com.google.gson.GsonBuilder;
import eu.ensup.gestionetablissement.business.Role;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
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
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * The type Student controller.
 */
@WebServlet(
        name = "StudentController",
        urlPatterns = {
            "/students_menu",
            "/students_add",
            "/students_list",
            "/students_update",
            "/students_remove",
        }
)
public class StudentController extends HttpServlet {

    private String successFlag = "success";
    private String errorFlag = "error";
    /**
     * Instantiates a new Student controller.
     */
    public StudentController() {
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

        switch(req.getRequestURI())
        {
            case "/etablissement/students_menu": {
                HttpSession session = req.getSession();
                int role = (int)session.getAttribute("role");
                req.setAttribute("usrole", role);
                requestDispatcher = req.getRequestDispatcher("/student_menu.jsp");
                requestDispatcher.forward(req, resp);
                return;
            }
            case "/etablissement/students_update": {
                if(req.getMethod().equals("POST"))
                {
                    try {
                        PersonService sc = new PersonService();
                        StudentDTO student = new StudentDTO();
                        student.setId(Integer.parseInt(req.getParameter("ID")));
                        student.setLastname(req.getParameter("Surname"));
                        student.setFirstname(req.getParameter("Name"));
                        student.setMailAddress(req.getParameter("Email"));
                        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
                        student.setDateOfBirth(df.parse(new String(req.getParameter("BDate").getBytes("ISO-8859-1"))));
                        student.setAddress(new String(req.getParameter("address").getBytes("ISO-8859-1")));
                        student.setPhoneNumber(req.getParameter("tel"));
                        student.setPassword("");
                        student.setRole(Role.STUDENT);
                        sc.update(student);
                    }
                    catch(Exception e)
                    {
                        req.setAttribute(errorFlag, e.getMessage());
                        resp.sendRedirect(req.getContextPath() + "/students_update");
                        return;
                    }

                    List<StudentDTO> students = null;
                    try {
                        students = getStudentList();
                        req.setAttribute("students", students);
                        students.forEach((student) -> {
                            student.setPassword("");
                        });

                        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
                        String jsonStr = gson.toJson(students);
                        req.setAttribute("students_json", jsonStr);
                        req.setAttribute(successFlag, "Etudiant modifié avec succès.");

                    } catch (ExceptionService exceptionService) {
                        exceptionService.printStackTrace();
                    }
                    requestDispatcher = req.getRequestDispatcher("/update_student.jsp");
                    requestDispatcher.forward(req, resp);
                    return;
                }
                else if(req.getMethod().equals("GET"))
                {
                    try {
                        List<StudentDTO> students = getStudentList();

                        req.setAttribute("students", students);
                        students.forEach((student) -> {
                            student.setPassword("");
                        });

                        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
                        String jsonStr = gson.toJson(students);
                        req.setAttribute("students_json", jsonStr);


                    } catch (ExceptionService exceptionService) {
                        req.setAttribute(errorFlag, exceptionService.getMessage());
                        requestDispatcher = req.getRequestDispatcher("/update_student.jsp");
                        requestDispatcher.forward(req, resp);
                        return;
                    }

                    /**
                     * Set the content type
                     */
                    requestDispatcher = req.getRequestDispatcher("/update_student.jsp");

                    requestDispatcher.forward(req, resp);
                    return;
                }
            }
            case "/etablissement/students_add": {
                if(req.getMethod().equals("GET")){
                    requestDispatcher = req.getRequestDispatcher("/add_student.jsp");
                    requestDispatcher.forward(req, resp);
                    return;
                }
                else if(req.getMethod().equals("POST"))
                {
                    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]};:,<.>/?";
                    String password = RandomStringUtils.random( 15, characters );

                    String	lastname = req.getParameter("Surname");
                    String	mailAddress = req.getParameter("Email");
                    String  address = req.getParameter("address");
                    String	phoneNumber = req.getParameter("tel");
                    String	firstname = req.getParameter("Name");

                    SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateOfBirth = new Date();
                    try {
                        dateOfBirth = dtFormat.parse(req.getParameter("BDate"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    PersonDTO dto = new StudentDTO(lastname, mailAddress, address, phoneNumber, firstname, password, dateOfBirth );
                    PersonService sc = new PersonService();

                    try {
                        sc.create(dto);
                    } catch (ExceptionService exceptionService) {
                        req.setAttribute(errorFlag, exceptionService.getMessage());
                        requestDispatcher = req.getRequestDispatcher("add_student.jsp");
                        requestDispatcher.forward(req, resp);
                        return;
                    }

                    resp.sendRedirect(req.getContextPath() + "/students_menu");
                    return;
                }
            }
            case "/etablissement/students_remove": {
                try {
                    PersonService sc = new PersonService();
                    sc.delete(Integer.parseInt(req.getParameter("id")));
                    List<StudentDTO> students = getStudentList();
                    req.setAttribute("students", students);
                    students.forEach((student) -> {
                        student.setPassword("");
                    });

                    Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
                    String jsonStr = gson.toJson(students);
                    req.setAttribute("students_json", jsonStr);
                    req.setAttribute(successFlag, "Etudiant supprimé avec succès.");
                }
                catch(Exception e)
                {
                    req.setAttribute(errorFlag, e.getMessage());
                    requestDispatcher = req.getRequestDispatcher("/update_student.jsp");
                    requestDispatcher.forward(req, resp);
                    return;
                }
                requestDispatcher = req.getRequestDispatcher("/update_student.jsp");
                requestDispatcher.forward(req, resp);
                return;
            }
            case "/etablissement/students_list": {
                if(req.getMethod().equals("GET")){
                    try {
                        List<StudentDTO> list = getStudentList();
                        req.setAttribute("students", list);
                    }
                    catch(Exception e){
                        req.setAttribute(errorFlag, e.getMessage());
                        requestDispatcher = req.getRequestDispatcher("/list_student.jsp");
                        requestDispatcher.forward(req, resp);
                        return;
                    }
                    requestDispatcher = req.getRequestDispatcher("/list_student.jsp");
                    requestDispatcher.forward(req, resp);
                    return;
                }
            }
        }
    }


    /**
     * Check user boolean.
     *
     * @param req  the req
     * @param resp the resp
     * @return the boolean true if has session flase otherwise
     * @throws IOException the io exception
     */
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

    List<StudentDTO> getStudentList() throws ExceptionService {
        PersonService sc = new PersonService();

        List<PersonDTO> personList = null;
        List<StudentDTO> students = new ArrayList<>();
            personList = sc.getAll();
            for (PersonDTO person : personList) {
                if (person.getRole() == Role.STUDENT) {
                    students.add((StudentDTO) person);
                }
            }
        return students;
    }
}
