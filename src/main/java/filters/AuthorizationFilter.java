package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import static filters.constants.PathConstants.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    String role;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        role = (String) req.getSession().getAttribute("role");
        if (isAccessDenied(req)) {
            System.out.println(role);
            req.getRequestDispatcher("/errorpage.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }

    private boolean isAccessDenied(HttpServletRequest req) {
        String path = req.getServletPath();
        if (role == null) { role = ""; };
        if (!role.equals("") && notAuthorizedOnly.contains(path)) {
            req.setAttribute("error", "you logged in");
            return true;
        }
        if (!role.equals("customer") && cusomerOnly.contains(path)) {
            req.setAttribute("error", "please log in");
            return true;
        }
        if (notAvailable.contains(path)) {
            req.setAttribute("error", "path is not available");
            return true;
        }
        return false;
    }
    
}
