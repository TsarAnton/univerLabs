import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.User;
import pckg.UserService;

public class ListUserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<User> users = UserService.readAll();
            req.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/indexUser.jsp").forward(req, resp);
        } catch(SQLException e) {
            throw new ServletException(e);
        }
    }
}

