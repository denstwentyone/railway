package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class GetRouteTrains implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        Long routeId = Long.parseLong(request.getParameter("route"));
        request.getSession().setAttribute("trains", trainService.getTrainsByRoute(routeId, 1));
        request.getSession().setAttribute("trainsroute", routeId);
        return "route.jsp";
    }
    
}
