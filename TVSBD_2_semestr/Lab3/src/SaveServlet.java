import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Enrollee;
import pckg.Storage;

public class SaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Enrollee enrollee = new Enrollee();
        enrollee.setName(req.getParameter("name"));
        enrollee.setSurname(req.getParameter("surname"));
        enrollee.setPatronymic(req.getParameter("patronymic"));
        enrollee.setCertificateScore(Double.parseDouble(req.getParameter("certificateScore")));
        enrollee.setFirstExamScore(Integer.parseInt(req.getParameter("firstExamScore")));
        enrollee.setSecondExamScore(Integer.parseInt(req.getParameter("secondExamScore")));
        enrollee.setThirdExamScore(Integer.parseInt(req.getParameter("thirdExamScore")));
        try {
            enrollee.setId(Integer.parseInt(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        try {
            if(enrollee.getId() == null) {
                Storage.create(enrollee);
            } else {
                Storage.update(enrollee);
            }
        } catch(SQLException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}
