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

    /**
     * @param dao
     */
    public TrainService(AbstractDAO dao) {
        TrainService.dao = dao;
    }

    /**
     * @param page
     * @return
     * @throws Exception
     */
    public List<Train> getAllTrains(Integer page) throws Exception {

        return dao.getAllTrains(page);
    
    }

    /**
     * @param routeId
     * @param page
     * @return
     * @throws Exception
     */
    public List<Train> getTrainsByRoute(Long routeId, Integer page) throws Exception {

        return dao.getTrainsByRoute(routeId, page);
    
    }

    /**
     * @param page
     * @return
     * @throws Exception
     */
    public List<Route> getAllRoutes(Integer page) throws Exception {

        return dao.getAllRoutes(page);
    
    }

    /**
     * @param page
     * @return
     * @throws Exception
     */
    public List<Station> getAllStations(Integer page) throws Exception {

        return dao.getAllStations(page);
    
    }

    /**
     * @return
     * @throws Exception
     */
    public List<Station> getAllStations() throws Exception {

        return dao.getAllStations();
    
    }
    
    /**
     * @param trainId
     * @param userEmail
     * @return
     * @throws Exception
     */
    public Ticket order(long trainId, String userEmail) throws Exception  {
        try {
            Ticket ticket = dao.addTicket(trainId, dao.getUser(userEmail).get().getId());

            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("you cant order two tickets for one person");
        }
    }

    /**
     * @param name
     * @param city
     * @throws SQLException
     */
    public void addStation(String name, String city) throws SQLException {
        dao.addStation(name, city);
    }

    /**
     * @param startingStation
     * @param startingTime
     * @param finalStation
     * @param finalTime
     * @throws SQLException
     */
    public void addRoute(Long startingStation, String startingTime, Long finalStation, String finalTime) throws SQLException {
        dao.addRoute(startingStation, Time.valueOf(startingTime), finalStation, Time.valueOf(finalTime));
    }

    /**
     * @param route
     * @param date
     * @param cost
     * @throws SQLException
     */
    public void addTrain(Long route, String date, Double cost) throws SQLException {
        dao.addTrain(route, Date.valueOf(date), cost);
    }
}
