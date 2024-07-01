import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import pckg.User;
import pckg.UserStorage;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(login != null && password != null) {
            /* условие выполняется, если сервлету была передана
             * форма авторизации */
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            try {
                if(UserStorage.checkUser(user)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    resp.sendRedirect(req.getContextPath());
                } else {
                    String message = "Имя пользователя или пароль неопознанны";
                    String url = req.getContextPath()
                               + "/login-form.jsp?message="
                               + URLEncoder.encode(message, "UTF-8");
                    resp.sendRedirect(url);
                }
            } catch(SQLException e) {
                throw new ServletException(e);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login-form.jsp");
        }
    }
}
