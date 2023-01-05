package filters;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getSession().getAttribute("rb") == null) {
            Locale locale = new Locale("en", "US");
            ResourceBundle rb = ResourceBundle.getBundle("language", locale);
            request.getSession().setAttribute("rb", rb);
        }
        chain.doFilter(request, resp);
    }
    
}
