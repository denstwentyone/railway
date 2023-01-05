package actions;

import javax.servlet.http.HttpServletRequest;

import db.AbstractDAO;
import db.postgres.PostgresDAO;
import services.TrainService;
import services.UserService;

public interface Action {

    static AbstractDAO dao = PostgresDAO.getInstance();
    static UserService userService = new UserService(PostgresDAO.getInstance());
    static TrainService trainService = new TrainService(PostgresDAO.getInstance());
    
    String execute(HttpServletRequest request) throws Exception;

}
