import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Doctor;
import pckg.DoctorService;
import pckg.RoleService;
import pckg.Ticket;
import pckg.TicketService;

public class DeleteUserRoleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        for(String id : req.getParameterValues("id")) {
            try {
                if(RoleService.readById(Integer.parseInt(id)).getName().compareTo("doctor") == 1) {
                    Doctor doctor = DoctorService.readById(userId);
                    if(doctor != null) {
                        ArrayList<Ticket> tickets = TicketService.readDoctorTickets(doctor);
                        if(tickets.size() != 0) {
                            for(int i = 0; i < tickets.size(); i++) {
                                TicketService.delete(tickets.get(i).getId());
                            }
                        }
                        DoctorService.delete(userId);
                    }
                }
                RoleService.deleteUserRole(userId, Integer.parseInt(id));
            } catch(NumberFormatException e) {
                
            }
            catch(SQLException e) {
                throw new ServletException(e);

            }
        }
        resp.sendRedirect(req.getContextPath() + "/indexUserRole.html?id=" + URLEncoder.encode(Integer.toString(userId), "UTF-8"));
    }
}

