package actions.implementation;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import actions.Action;
import db.postgres.PostgresDAO;

public class SignUpAction implements Action {

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            PostgresDAO.getInstance().signUp(email, password);
            if (PostgresDAO.getInstance().logIn(email, password)) {
                
                request.getSession().setAttribute("user", email);
                System.out.println(email);

                return "index.jsp";
            }
            return "login.jsp";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            request.getSession().setAttribute("error", "invalid input");
            return "errorpage.jsp";
        }
    }
    
}
