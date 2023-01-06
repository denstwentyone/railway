package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class DefaultAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        try {
            request.getSession().setAttribute("trains", trainService.getAllTrains());

            request.getSession().setAttribute("tickets", userService.getTickets(
                    (String)request.getSession().getAttribute("user")
            ));

            request.getSession().setAttribute("routes", trainService.getAllRoutes());

            request.getSession().setAttribute("stations", trainService.getAllStations());

            return "index.jsp";
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new Exception("try later");
        }
    }
    
}
