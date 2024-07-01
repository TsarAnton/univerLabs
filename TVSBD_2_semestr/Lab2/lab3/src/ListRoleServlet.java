import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Role;
import pckg.RoleService;

public class ListRoleServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<Role> roles = RoleService.readAll();
            req.setAttribute("roles", roles);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/indexRole.jsp").forward(req, resp);
        } catch(SQLException e) {
            throw new ServletException(e);
        }
    }
}
