package actions.implementation;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import actions.Action;
import db.postgres.PostgresDAO;

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
        return "views/login.jsp";
    }

    private String execuePost(HttpServletRequest request) {
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        try {
            if (PostgresDAO.getInstance().logIn(email, password)) {
                request.getSession().setAttribute("user", email);
                return "index.jsp";
            }
            else {
                request.getSession().setAttribute("error", "no such user");
                return "views/errorpage.jsp";
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "index.jsp";
    }
    
}
