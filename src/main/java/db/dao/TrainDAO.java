package db.dao;

import java.sql.SQLException;
import java.util.List;

import db.entities.Route;
import db.entities.Station;
import db.entities.Train;

public interface TrainDAO {
    
    public List<Train> getAllTrains() throws SQLException;

    public List<Route> getAllRoutes() throws SQLException;

    public List<Station> getAllStations() throws SQLException;

    public List<Train> getTrainsByCity(String city) throws SQLException;

    public long addTrain(Train train) throws Exception;

    public long addRoute(Route route) throws Exception;

    public long addStation(String name, String city) throws Exception;
}
