package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class LogOutAction implements Action {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);
        System.out.println("user: null");
        return "/railway";
    }
    
}
