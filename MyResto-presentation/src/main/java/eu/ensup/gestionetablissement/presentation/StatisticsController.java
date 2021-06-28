package eu.ensup.gestionetablissement.presentation;

    import eu.ensup.gestionetablissement.dto.MarkDTO;
    import eu.ensup.gestionetablissement.service.ExceptionService;
    import eu.ensup.gestionetablissement.service.MarkService;

    import javax.servlet.RequestDispatcher;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import javax.servlet.http.HttpSession;
    import java.io.IOException;
    import java.util.List;
    import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(
    name = "StatisticsController",
    urlPatterns = {
        "/statistics"
    }
)
public class StatisticsController extends HttpServlet {
    private String successFlag = "success";
    private String errorFlag = "error";
    private int NIVEAU_EXCELLENT = 17;
    private int NIVEAU_BON = 12;
    private int NIVEAU_MOYEN = 8;

    /**
     * Instantiates a new Student controller.
     */
    public StatisticsController() {
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
        if (!checkUser(req, resp)) {
            return;
        }
        RequestDispatcher requestDispatcher;

        switch (req.getRequestURI()) {
            case "/etablissement/statistics": {
                if(req.getMethod().equals("GET"))
                {
                    try {
                        List<MarkDTO> allMarks = getAllMarks();
                        AtomicInteger note_mauvais = new AtomicInteger();
                        AtomicInteger note_moyen = new AtomicInteger();
                        AtomicInteger note_bon = new AtomicInteger();
                        AtomicInteger note_excellent = new AtomicInteger();
                        allMarks.forEach((markDTO) -> {
                            float mark = markDTO.getMark();
                            if(mark >= NIVEAU_EXCELLENT ) {
                                note_excellent.getAndIncrement();
                            } else if(mark >= NIVEAU_BON) {
                                note_bon.getAndIncrement();
                            } else if(mark >= NIVEAU_MOYEN) {
                                note_moyen.getAndIncrement();
                            } else {
                                note_mauvais.getAndIncrement();
                            }
                        });
                        float perc_mauvais = 0.f;
                        float perc_moyen = 0.f;
                        float perc_bon = 0.f;
                        float perc_excellent = 0.f;
                        float nbNote = allMarks.size();

                        perc_excellent = (note_excellent.get() / nbNote) * 100;
                        perc_bon = (note_bon.get() / nbNote)  * 100;
                        perc_moyen = (note_moyen.get() / nbNote) * 100;
                        perc_mauvais = (note_mauvais.get()  / nbNote) * 100;

                        req.setAttribute("mauvais", perc_mauvais);
                        req.setAttribute("moyen", perc_moyen);
                        req.setAttribute("bon", perc_bon);
                        req.setAttribute("excellent", perc_excellent);
                    }
                    catch(Exception e) {
                        req.setAttribute(errorFlag, e.getMessage());
                        requestDispatcher = req.getRequestDispatcher("students_statistics.jsp");
                        requestDispatcher.forward(req, resp);
                        return;
                    }

                    requestDispatcher = req.getRequestDispatcher("students_statistics.jsp");
                    requestDispatcher.forward(req, resp);
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

    List<MarkDTO> getAllMarks() throws ExceptionService {
        MarkService ms = null;
        ms = new MarkService();
        return ms.getAll();
    }
}