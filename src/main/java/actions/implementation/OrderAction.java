package actions.implementation;

import javax.servlet.http.HttpServletRequest;

import actions.Action;
import db.entities.Ticket;

public class OrderAction implements Action {

    @Override
    public String execute(HttpServletRequest request) {
        Long trainId = (Long) request.getSession().getAttribute("trainId");
        String userEmail = (String) request.getSession().getAttribute("user");
        System.out.println(trainId);
        System.out.println(userEmail);

        try {
            Ticket ticket = trainService.order(trainId, userEmail);
            request.getSession().setAttribute("ticket", ticket);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            request.getSession().setAttribute("error", e.getMessage());
            return "errorpage.jsp";
        }
        return "order.jsp";
    }
    
}
