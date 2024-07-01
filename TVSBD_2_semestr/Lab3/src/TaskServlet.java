import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pckg.Task;

public class TaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if(!req.getParameter("placeCount").isEmpty()) {
            Integer placeCount = Integer.parseInt(req.getParameter("placeCount"));
            Task.findResult(placeCount);
        }
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}
