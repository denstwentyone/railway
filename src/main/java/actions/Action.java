package actions;

import javax.servlet.http.HttpServletRequest;

import db.postgres.PostgresDAO;
import services.TrainService;
import services.UserService;

public interface Action {
    public UserService userService = new UserService(PostgresDAO.getInstance());
    public TrainService trainService = new TrainService(PostgresDAO.getInstance());
    
    String execute(HttpServletRequest request);

}
