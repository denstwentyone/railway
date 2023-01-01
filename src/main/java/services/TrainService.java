package services;

import db.AbstractDAO;
import db.entities.Ticket;

public class TrainService implements Service {

    private static AbstractDAO dao;

    public TrainService(AbstractDAO dao) {
        TrainService.dao = dao;
    }
    
    public Ticket order(long trainId, String userEmail) throws Exception  {
        try {
            return dao.addTicket(trainId, dao.getUser(userEmail).getId());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("something went wrong");
        }
    }

}
