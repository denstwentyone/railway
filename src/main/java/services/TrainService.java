package services;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import db.AbstractDAO;
import db.entities.Route;
import db.entities.Station;
import db.entities.Ticket;
import db.entities.Train;

public class TrainService implements Service {

    private static AbstractDAO dao;

    public TrainService(AbstractDAO dao) {
        TrainService.dao = dao;
    }

    public List<Train> getAllTrains() throws Exception {

        return dao.getAllTrains();
    
    }

    public List<Route> getAllRoutes() throws Exception {

        return dao.getAllRoutes();
    
    }

    public List<Station> getAllStations() throws Exception {

        return dao.getAllStations();
    
    }
    
    public Ticket order(long trainId, String userEmail) throws Exception  {
        try {
            Ticket ticket = dao.addTicket(trainId, dao.getUser(userEmail).get().getId());

            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("you cant order two tickets for one person");
        }
    }

    public void addStation(String name, String city) throws SQLException {
        dao.addStation(name, city);
    }

    public void addRoute(Long startingStation, String startingTime, Long finalStation, String finalTime) throws SQLException {
        dao.addRoute(startingStation, Time.valueOf(startingTime), finalStation, Time.valueOf(finalTime));
    }

    public void addTrain(Long route, String date, Double cost) throws SQLException {
        dao.addTrain(route, Date.valueOf(date), cost);
    }
}
