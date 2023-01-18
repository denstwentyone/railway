package actions.implementation.pagination;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class NextAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        if (request.getParameter("value").equals("trains")) {
            Integer page = (Integer)request.getSession().getAttribute("trainspage");
            page++;
            if (trainService.getAllTrains(page).size() == 0) {
                page--;
            }
            request.getSession().setAttribute("trainspage", page);
            request.getSession().setAttribute("trains", trainService.getAllTrains(page));
        }
        if (request.getParameter("value").equals("trainsforroute")) {
            Integer page = (Integer)request.getSession().getAttribute("trainspage");
            page++;
            if (trainService.getTrainsByRoute((Long)request.getSession().getAttribute("trainsroute"), page).size() == 0) {
                page--;
            }
            request.getSession().setAttribute("trainspage", page);
            request.getSession().setAttribute("trains", trainService.getTrainsByRoute(
                (Long)request.getSession().getAttribute("trainsroute"), page));
        }
        if (request.getParameter("value").equals("tickets")) {
            Integer page = (Integer)request.getSession().getAttribute("ticketspage");
            page++;
            if (userService.getTickets((String)request.getSession().getAttribute("user"), page).size() == 0) {
                page--;
            }
            request.getSession().setAttribute("ticketspage", page);
            request.getSession().setAttribute("tickets", userService.getTickets(
                (String)request.getSession().getAttribute("user"), page));
        }
        if (request.getParameter("value").equals("stations")) {
            Integer page = (Integer)request.getSession().getAttribute("stationspage");
            page++;
            if (trainService.getAllStations(page).size() == 0) {
                page--;
            }
            request.getSession().setAttribute("stationspage", page);
            request.getSession().setAttribute("stations", trainService.getAllStations(page));
        }
        if (request.getParameter("value").equals("routes")) {
            Integer page = (Integer)request.getSession().getAttribute("routespage");
            page++;
            if (trainService.getAllRoutes(page).size() == 0) {
                page--;
            }
            request.getSession().setAttribute("routespage", page);
            request.getSession().setAttribute("routes", trainService.getAllRoutes(page));
        }
        if (request.getParameter("value").equals("selectroutes")) {
            Integer page = (Integer)request.getSession().getAttribute("selectroutespage");
            page++;
            if (trainService.getAllRoutes(page).size() == 0) {
                page--;
            }
            request.getSession().setAttribute("selectroutespage", page);
            request.getSession().setAttribute("selectroutes", trainService.getAllRoutes(page));
        }
        return request.getParameter("view");
    }
    
}
