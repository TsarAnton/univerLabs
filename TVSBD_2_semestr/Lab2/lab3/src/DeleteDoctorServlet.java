import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.DoctorService;
import pckg.RoleService;
import pckg.Ticket;
import pckg.TicketService;

public class DeleteDoctorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(String id : req.getParameterValues("id")) {
            try {
                ArrayList<Ticket> tickets = TicketService.readDoctorTickets(DoctorService.readById(Integer.parseInt(id)));
                if(tickets.size() != 0) {
                    for(int i = 0; i < tickets.size(); i++) {
                        TicketService.delete(tickets.get(i).getId());
                    }
                }
                DoctorService.delete(Integer.parseInt(id));
                RoleService.deleteUserRole(Integer.parseInt(id), RoleService.readByName("doctor").getId());
            } catch(NumberFormatException e) {
                
            }
            catch(SQLException e) {
                throw new ServletException(e);

            }
        }
        resp.sendRedirect(req.getContextPath() + "/indexDoctor.html");
    }
}


