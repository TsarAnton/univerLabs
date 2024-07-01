import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.DateFunctions;
import pckg.Doctor;
import pckg.DoctorService;
import pckg.Ticket;
import pckg.TicketService;

public class DeleteTicketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer doctorId = Integer.parseInt(req.getParameter("doctorId"));
        for(String id : req.getParameterValues("id")) {
            try {
                Ticket ticket = TicketService.readById(Integer.parseInt(id));
                if(!ticket.getIsFree()) {
                    Doctor doctor = DoctorService.readById(doctorId);
                    doctor.setAllDuration(DateFunctions.subDates(doctor.getAllDuration(), ticket.getDuration()));
                    DoctorService.update(doctor);
                }
                TicketService.delete(Integer.parseInt(id));
            } catch(NumberFormatException e) {
                
            }
            catch(SQLException e) {
                throw new ServletException(e);

            }
        }
        resp.sendRedirect(req.getContextPath() + "/indexTicket.html?id=" + URLEncoder.encode(Integer.toString(doctorId), "UTF-8"));
    }
}
