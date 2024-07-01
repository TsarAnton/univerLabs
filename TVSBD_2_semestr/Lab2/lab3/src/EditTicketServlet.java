import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Ticket;
import pckg.TicketService;
import pckg.Doctor;
import pckg.DoctorService;

public class EditTicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean con = true;
        try {
            Integer doctorId = Integer.parseInt(req.getParameter("doctorId"));
            Integer id = Integer.parseInt(req.getParameter("id"));
            Ticket ticket = TicketService.readById(id);
            Doctor doctor = DoctorService.readById(doctorId);
            req.setAttribute("ticket", ticket);
            req.setAttribute("doctor", doctor);
        } catch(NumberFormatException e) {}
        catch(SQLException e) {
            throw new ServletException(e);
        }
        if(con) {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/editTicket.jsp").forward(req, resp);
        }
    }        
}
