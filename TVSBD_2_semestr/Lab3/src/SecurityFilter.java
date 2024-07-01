import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain)
                               throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)req)
                                                  .getSession(false);
        if(session != null && session.getAttribute("user") != null) {
            chain.doFilter(req, resp);
        } else {
            ((HttpServletResponse)resp).sendRedirect(
                ((HttpServletRequest)req).getContextPath()
                + "/login-form.jsp"
            );
        }
    }

    @Override
    public void destroy() {}
}
