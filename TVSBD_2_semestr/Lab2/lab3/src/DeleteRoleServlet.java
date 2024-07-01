import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.RoleService;

public class DeleteRoleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(String id : req.getParameterValues("id")) {
            try {
                RoleService.delete(Integer.parseInt(id));
            } catch(NumberFormatException e) {
                
            }
            catch(SQLException e) {
                throw new ServletException(e);

            }
        }
        resp.sendRedirect(req.getContextPath() + "/indexRole.html");
    }
}
