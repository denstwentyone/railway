package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;
import db.entities.Ticket;

public class OrderAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        Integer trainId = Integer.parseInt(request.getParameter("trainid"));
        String userEmail = (String) request.getSession().getAttribute("user");
        System.out.println(trainId);
        Ticket ticket = trainService.order(trainId, userEmail);
        request.getSession().setAttribute("ticket", ticket);
        return "order.jsp";
    }
    
}
