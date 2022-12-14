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
    
    public List<Train> getAllTrains() throws SQLException;

    public List<Route> getAllRoutes() throws SQLException;

    public List<Station> getAllStations() throws SQLException;

    public List<Train> getTrainsByCity(String city) throws SQLException;

    public long addTrain(Long route, Date date, Double cost) throws SQLException;

    public long addRoute(Long startingStation, Time startingTime, Long finalStation, Time finalTime) throws SQLException;

    public long addStation(String name, String city) throws SQLException;

    public Ticket addTicket(long trainId, long userId) throws SQLException;
}
