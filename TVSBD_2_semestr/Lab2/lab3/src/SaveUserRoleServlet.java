import java.io.IOException;
import java.sql.SQLException;
import java.net.URLEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Role;
import pckg.RoleService;

public class SaveUserRoleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Integer userId = Integer.parseInt(req.getParameter("id"));
        Integer roleId = Integer.parseInt(req.getParameter("roleId"));
        boolean con = true;
        try {
            Role role = RoleService.readById(roleId);
            if(RoleService.checkUserRole(roleId, userId)) {
                con = false;
                String message = "Пользователь уже имеет роль " + role.getName();
                    String url = req.getContextPath()
                               + "/editUserRole.html?message="
                               + URLEncoder.encode(message, "UTF-8")
                               + "&&id="
                               + URLEncoder.encode(Integer.toString(userId), "UTF-8");
                    resp.sendRedirect(url);
            } else {
                RoleService.createUserRole(userId, role);
            }
        } catch(SQLException e) {
            throw new ServletException(e);
        }
        if(con) {
            resp.sendRedirect(req.getContextPath() + "/indexUserRole.html?id=" + URLEncoder.encode(Integer.toString(userId), "UTF-8"));
        }
    }
}