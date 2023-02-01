package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;
import db.entities.Ticket;

public class OrderAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        Integer trainId = Integer.parseInt(request.getParameter("trainid"));
        Integer seats = Integer.parseInt(request.getParameter("seats"));
        String userEmail = (String) request.getSession().getAttribute("user");
        Ticket ticket = trainService.order(trainId, userEmail, seats);
        request.getSession().setAttribute("ticket", ticket);
        return "order.jsp";
    }
    
}
