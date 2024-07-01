import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Doctor;
import pckg.DoctorService;
import pckg.Role;
import pckg.RoleService;
import pckg.Ticket;
import pckg.TicketService;
import pckg.User;
import pckg.UserService;

public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(String id : req.getParameterValues("id")) {
            try {
                Integer idInt = Integer.parseInt(id);
                User user = UserService.readById(idInt);
                ArrayList<Role> roles = RoleService.readUserRoles(user);
                if(roles.size() != 0) {
                    for(int i = 0; i < roles.size(); i++) {
                        RoleService.deleteUserRole(user.getId(), roles.get(i).getId());
                    }
                }
                Doctor doctor = DoctorService.readById(idInt);
                if(doctor != null) {
                    ArrayList<Ticket> tickets = TicketService.readDoctorTickets(doctor);
                    if(tickets.size() != 0) {
                        for(int i = 0; i < tickets.size(); i++) {
                            TicketService.delete(tickets.get(i).getId());
                        }
                    }
                    DoctorService.delete(idInt);
                }
                UserService.delete(idInt);
            } catch(NumberFormatException e) {
                
            }
            catch(SQLException e) {
                throw new ServletException(e);

            }
        }
        resp.sendRedirect(req.getContextPath() + "/indexUser.html");
    }
}

