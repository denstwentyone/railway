package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class AutorizationFilter implements Filter {
    String role;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        role = (String) req.getSession().getAttribute("role");
        if (isAccessDenied(req)) {
            req.getRequestDispatcher("errorpage.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }

    private boolean isAccessDenied(HttpServletRequest req) {
        String path = req.getServletPath();
        if (role == null && path == "/railway/order.jsp") {
            return true;
        }
        return false;
    }
    
}
