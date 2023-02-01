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
import static util.Validation.*;

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
    public Ticket order(long trainId, String userEmail, int seats) throws Exception  {
        
        Integer reservedSeats = dao.getReservedSeats(trainId);
        System.out.println(reservedSeats);
        if (reservedSeats < seats) {
            try {
                Ticket ticket = dao.addTicket(trainId, dao.getUser(userEmail).get().getId());
                return ticket;
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("you cant order two tickets for one person");
            }
        }
        throw new Exception("All seats have been reserved");
        
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
     * @throws Exception
     */
    public void addRoute(Long startingStation, String startingTime, Long finalStation, String finalTime) throws Exception {
        if (isTimeValid(startingTime) && isTimeValid(finalTime)) {
            dao.addRoute(startingStation, Time.valueOf(startingTime), finalStation, Time.valueOf(finalTime));
            
        }
        else {
            throw new Exception("invalid time");
        }
    }

    /**
     * @param route
     * @param date
     * @param cost
     * @throws Exception
     */
    public void addTrain(Long route, String date, Double cost, int seats) throws Exception {
        if (isDateValid(date)) {
            dao.addTrain(route, Date.valueOf(date), cost, seats);
        }
        else {
            throw new Exception("invalid date");
        }
    }

    /**
     * @param from
     * @param to
     * @return
     * @throws SQLException
     */
    public List<Route> searchForRoute(String from, String to) throws SQLException {
        return dao.getRoutesByStations(Long.parseLong(from), Long.parseLong(to));
    }
}
