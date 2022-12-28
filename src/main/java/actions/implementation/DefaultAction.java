package actions.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import actions.Action;
import db.entities.Train;
import db.postgres.PostgresDAO;

public class DefaultAction implements Action {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            List<Train> trains = new ArrayList<>();
            trains = PostgresDAO.getInstance().getAllTrains();
            request.getSession().setAttribute("trains", trains);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "index.jsp";
    }
    
}
