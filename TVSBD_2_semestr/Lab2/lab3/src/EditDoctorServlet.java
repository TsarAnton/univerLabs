import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Doctor;
import pckg.DoctorService;
import pckg.User;
import pckg.UserService;

public class EditDoctorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<User> users = UserService.readAll();
            req.setAttribute("users", users);
            Integer id = Integer.parseInt(req.getParameter("id"));
            Doctor doctor = DoctorService.readById(id);
            req.setAttribute("doctor", doctor);
        } catch(NumberFormatException e) {
            
        }
        catch(SQLException e) {
            throw new ServletException(e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/editDoctor.jsp").forward(req, resp);
    
    }        
}
