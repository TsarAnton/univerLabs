import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Role;
import pckg.RoleService;
import pckg.User;
import pckg.UserService;

public class EditUserRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            User user = UserService.readById(id);
            ArrayList<Role> roles = RoleService.readAll();
            req.setAttribute("user1", user);
            req.setAttribute("roles", roles);
        } catch(NumberFormatException e) {
            
        }
        catch(SQLException e) {
            throw new ServletException(e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/editUserRole.jsp").forward(req, resp);
    }        
}
