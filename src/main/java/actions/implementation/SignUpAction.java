package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class SignUpAction implements Action {

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
        return "signup.jsp";
    }

    private String execuePost(HttpServletRequest request) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (userService.signUp(email, password)) {
            LogInAction login = new LogInAction();
            return login.execute(request);
        }
        else {
            throw new Exception("invalid input");
        }
        
    }
    
}
