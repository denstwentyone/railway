package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;

public class AddTrainAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        Long route = Long.parseLong(request.getParameter("route"));
        String date = request.getParameter("date");
        Double cost = Double.parseDouble(request.getParameter("cost"));
        int seats = Integer.parseInt(request.getParameter("seats"));
        trainService.addTrain(route, date, cost, seats);
        return "/railway";
    }
    
}
