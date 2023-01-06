package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class AddStationAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String name = request.getParameter("stationname");
        String city = request.getParameter("city");
        trainService.addStation(name, city);
        return "/railway";
    }
    
}
