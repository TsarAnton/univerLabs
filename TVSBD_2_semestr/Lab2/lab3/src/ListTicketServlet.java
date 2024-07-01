import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Ticket;
import pckg.TicketService;
import pckg.Doctor;
import pckg.DoctorService;

public class ListTicketServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Doctor doctor = DoctorService.readById(id);
            ArrayList<Ticket> tickets = TicketService.readDoctorTickets(doctor);
            req.setAttribute("tickets", tickets);
            req.setAttribute("doctor", doctor);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/indexTicket.jsp").forward(req, resp);
        } catch(SQLException e) {
            throw new ServletException(e);
        }
    }
}
