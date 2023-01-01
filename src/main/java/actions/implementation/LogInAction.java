package actions.implementation;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class LogInAction implements Action {

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getMethod().equals("POST")) {
            return execuePost(request);
        }
        else {
            return execueGet(request);
        }
    }

    private String execueGet(HttpServletRequest request) {
        return "login.jsp";
    }

    private String execuePost(HttpServletRequest request) {
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        try {
            if (userService.login(email, password)) {
                request.getSession().setAttribute("user", email);
                return "index.jsp";
            }
            else {
                request.getSession().setAttribute("error", "no such user");
                return "errorpage.jsp";
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            request.getSession().setAttribute("error", e.getMessage());
            return "errorpage.jsp";
        }
    }
    
}
