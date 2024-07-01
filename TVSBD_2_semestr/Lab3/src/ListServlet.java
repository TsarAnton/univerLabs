import java.io.IOException;
import java.util.Collection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Enrollee;
import pckg.Storage;

public class ListServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Collection<Enrollee> objects = Storage.readAll();
            req.setAttribute("objects", objects);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        } catch(SQLException e) {
            throw new ServletException(e);
        }
    }
}
