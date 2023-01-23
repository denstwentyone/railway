package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class SearchAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        
        
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        request.getSession().setAttribute("foundroutes", trainService.searchForRoute(from, to));
        
        return "search.jsp";
    }
    
}
