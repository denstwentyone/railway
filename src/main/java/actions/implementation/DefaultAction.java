package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class DefaultAction implements Action {

    @Override
    public String execute(HttpServletRequest request) {
        return "index.jsp";
    }
    
}
