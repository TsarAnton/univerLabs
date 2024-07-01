import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Role;
import pckg.RoleService;
import pckg.User;
import pckg.UserService;

public class ListUserRoleServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            User user = UserService.readById(id);
            ArrayList<Role> roles = RoleService.readUserRoles(user);
            req.setAttribute("roles", roles);
            req.setAttribute("user1", user);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/indexUserRole.jsp").forward(req, resp);
        } catch(SQLException e) {
            throw new ServletException(e);
        }
    }
}