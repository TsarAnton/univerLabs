import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            session.removeAttribute("user");
            session.removeAttribute("admin");
            session.removeAttribute("nurse");
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath());
    }
}
