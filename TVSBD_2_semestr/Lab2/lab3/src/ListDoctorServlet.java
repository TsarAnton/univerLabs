import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Doctor;
import pckg.DoctorService;

public class ListDoctorServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<Doctor> doctors = DoctorService.readAll();
            req.setAttribute("doctors", doctors);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/indexDoctor.jsp").forward(req, resp);
        } catch(SQLException e) {
            throw new ServletException(e);
        }
    }
}
