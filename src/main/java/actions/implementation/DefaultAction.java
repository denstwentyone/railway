package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class DefaultAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        try {
            request.getSession().setAttribute("trains", trainService.getAllTrains(1));
            request.getSession().setAttribute("trainspage", 1);

            request.getSession().setAttribute("tickets", userService.getTickets(
                    (String)request.getSession().getAttribute("user"), 1
            ));
            request.getSession().setAttribute("ticketspage", 1);

            request.getSession().setAttribute("routes", trainService.getAllRoutes(1));
            request.getSession().setAttribute("routespage", 1);

            request.getSession().setAttribute("stations", trainService.getAllStations(1));
            request.getSession().setAttribute("stationspage", 1);


            return "index.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("try later");
        }
    }
    
}
