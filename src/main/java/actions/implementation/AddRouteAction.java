package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class AddRouteAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        Long startingStation = Long.parseLong(request.getParameter("startingstation"));
        String startingTime = request.getParameter("startingtime");
        Long finalStation = Long.parseLong(request.getParameter("finalstation"));
        String finalTime = request.getParameter("finaltime");
        trainService.addRoute(startingStation, startingTime, finalStation, finalTime);
        return "/railway";
        // return "index.jsp";
    }
    
}
