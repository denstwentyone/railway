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
            System.out.println(trainId + " | " + userEmail);
            Ticket ticket = dao.addTicket(trainId, dao.getUser(userEmail).get().getId());

            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("you cant order two tickets for one person");
        }
    }

}
