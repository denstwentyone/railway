package actions.implementation.pagination;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class PrevAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        if (request.getParameter("value").equals("trains")) {
            Integer page = (Integer)request.getSession().getAttribute("trainspage");
            if (page>1) {
                page--;
            }
            request.getSession().setAttribute("trainspage", page);
            request.getSession().setAttribute("trains", trainService.getAllTrains(page));
        }
        if (request.getParameter("value").equals("trainsforroute")) {
            Integer page = (Integer)request.getSession().getAttribute("trainspage");
            if (page>1) {
                page--;
            }
            request.getSession().setAttribute("trainspage", page);
            request.getSession().setAttribute("trains", trainService.getTrainsByRoute(
                (Long)request.getSession().getAttribute("trainsroute"), page));
        }
        if (request.getParameter("value").equals("tickets")) {
            Integer page = (Integer)request.getSession().getAttribute("ticketspage");
            if (page>1) {
                page--;
            }
            request.getSession().setAttribute("ticketspage", page);
            request.getSession().setAttribute("tickets", userService.getTickets(
                (String)request.getSession().getAttribute("user"), page));
        }
        if (request.getParameter("value").equals("stations")) {
            Integer page = (Integer)request.getSession().getAttribute("stationspage");
            if (page>1) {
                page--;
            }
            request.getSession().setAttribute("stationspage", page);
            request.getSession().setAttribute("stations", trainService.getAllStations(page));
        }
        if (request.getParameter("value").equals("routes")) {
            Integer page = (Integer)request.getSession().getAttribute("routespage");
            if (page>1) {
                page--;
            }
            request.getSession().setAttribute("routespage", page);
            request.getSession().setAttribute("routes", trainService.getAllRoutes(page));
        }
        return request.getParameter("view");
    }
    
}
