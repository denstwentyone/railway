package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

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
        
        if (userService.login(email, password)) {
            request.getSession().setAttribute("user", email);
            return "/railway";
        }
        else {
            throw new Exception("invalid password or email");
        }
        
    }
    
}
