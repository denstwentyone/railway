package db.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import db.entities.Route;
import db.entities.Station;
import db.entities.Ticket;
import db.entities.Train;

public interface TrainDAO {
    
    /**
     * @param page
     * @return 
     * @throws SQLException
     */
    public List<Train> getAllTrains(Integer page) throws SQLException;

    /**
     * @param page
     * @return
     * @throws SQLException
     */
    public List<Route> getAllRoutes(Integer page) throws SQLException;

    /**
     * @param page
     * @return
     * @throws SQLException
     */
    public List<Station> getAllStations(Integer page) throws SQLException;

    /**
     * @param routeId
     * @param page
     * @return
     * @throws SQLException
     */
    public List<Train> getTrainsByRoute(Long routeId, Integer page) throws SQLException;

    /**
     * @param route
     * @param date
     * @param cost
     * @return
     * @throws SQLException
     */
    public long addTrain(Long route, Date date, Double cost, int seats) throws SQLException;

    /**
     * @param startingStation
     * @param startingTime
     * @param finalStation
     * @param finalTime
     * @return
     * @throws SQLException
     */
    public long addRoute(Long startingStation, Time startingTime, Long finalStation, Time finalTime) throws SQLException;

    /**
     * @param name
     * @param city
     * @return
     * @throws SQLException
     */
    public long addStation(String name, String city) throws SQLException;

    /**
     * @param trainId
     * @param userId
     * @return
     * @throws SQLException
     */
    public Ticket addTicket(long trainId, long userId) throws SQLException;

    /**
     * @param trainId
     * @return
     * @throws SQLException
     */
    public Integer getReservedSeats(long trainId) throws SQLException;
    
}
