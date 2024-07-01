import java.io.IOException;
import java.sql.SQLException;
import java.net.URLEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Role;
import pckg.RoleService;

public class SaveRoleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        boolean con = true;
        Role role = new Role();
        role.setName(req.getParameter("name"));
        try {
            role.setId(Integer.parseInt(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        try {
            if(role.getId() == null) {
                if(RoleService.checkRoleName(role)) {
                    con = false;
                    String message = "Роль " + role.getName() + " уже существует";
                    String url = req.getContextPath()
                               + "/editRole.html?message="
                               + URLEncoder.encode(message, "UTF-8");
                    resp.sendRedirect(url);
                } else {
                    RoleService.create(role);
                }
            } else {
                RoleService.update(role);
            }
        } catch(SQLException e) {
            throw new ServletException(e);
        }
        if(con) {
            resp.sendRedirect(req.getContextPath() + "/indexRole.html");
        }
    }
}
