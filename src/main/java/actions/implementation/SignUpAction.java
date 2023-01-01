package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class SignUpAction implements Action {

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
        return "signup.jsp";
    }

    private String execuePost(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            if (userService.signUp(email, password)) {
                LogInAction login = new LogInAction();
                return login.execute(request);
            }
            else {
                request.getSession().setAttribute("error", "invalid input");
                return "errorpage.jsp";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            request.getSession().setAttribute("error", e.getMessage());
            return "errorpage.jsp";
        }
    }
    
}
