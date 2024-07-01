import java.io.IOException;
import java.sql.SQLException;
import java.net.URLEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.User;
import pckg.UserService;

public class SaveUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        boolean con = true;
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        try {
            user.setId(Integer.parseInt(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        try {
            if(user.getId() == null) {
                if(UserService.checkUserLogin(user)) {
                    con = false;
                    String message = "Пользователь " + user.getLogin() + " уже существует";
                    String url = req.getContextPath()
                               + "/editUser.html?message="
                               + URLEncoder.encode(message, "UTF-8");
                    resp.sendRedirect(url);
                } else {
                    UserService.create(user);
                }
            } else {
                UserService.update(user);
            }
        } catch(SQLException e) {
            throw new ServletException(e);
        }
        if(con) {
            resp.sendRedirect(req.getContextPath() + "/indexUser.html");
        }
    }
}
