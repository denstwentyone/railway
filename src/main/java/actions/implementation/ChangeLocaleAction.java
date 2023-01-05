package actions.implementation;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class ChangeLocaleAction implements Action {

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getAttribute("rb") == null) {
            String[]  planguage = request.getParameter("locale").split("_");
            String language = planguage[0];
            String country = planguage[1];
            System.out.println(language + " " + country);
            Locale locale = new Locale(language, country);
            ResourceBundle rb = ResourceBundle.getBundle("language", locale);
            System.out.println(rb.toString());
            request.getSession().setAttribute("rb", rb);
        }
        return "/railway";
    }
    
}
