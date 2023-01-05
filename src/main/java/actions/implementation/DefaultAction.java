package actions.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import actions.Action;
import db.entities.Train;

public class DefaultAction implements Action {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        try {
            List<Train> trains = new ArrayList<>();
            trains = dao.getAllTrains(); // TODO
            request.getSession().setAttribute("trains", trains);

            if (request.getSession().getAttribute("user") != null) {
                List<Train> tickets = new ArrayList<>();
                tickets = dao.getTrainsForUser(dao.getUser((String)request.getSession().getAttribute("user"))); // TODO
                request.getSession().setAttribute("tickets", tickets);
            }
            return "index.jsp";
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new Exception("try later");
        }
    }
    
}
