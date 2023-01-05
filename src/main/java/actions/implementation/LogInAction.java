package actions.implementation;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import actions.Action;
import db.entities.User;

public class LogInAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {

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

    private String execuePost(HttpServletRequest request) throws Exception {
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        Optional<User> user = userService.login(email, password);
        
        if (user.isPresent()) {
            request.getSession().setAttribute("user", user.get().getEmail());
            request.getSession().setAttribute("role", user.get().getRole());
            return "/railway";
        }
        else {
            throw new Exception("invalid password or email");
        }
        
    }
    
}
