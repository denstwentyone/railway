package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import db.derby.DerbyDBManager;
import db.entities.Route;
import db.entities.Station;
import db.entities.Train;
import db.postgres.PostgresDBManager;

public abstract class DBManager {

    private static DBManager manager;
    private static String URL;
    private static HikariDataSource dataSource;
    protected String driverClassName;

    /**
     * @param driverName Has to be one of "postgres" or "derby"
     * @return Manager instance of chosen db
     */
    public static synchronized DBManager getDManager(String driverName) {
        
        if (driverName.equals("postgres")) {
            manager = PostgresDBManager.getInstance();
        }
        else if (driverName.equals("derby")) {
            manager = DerbyDBManager.getInstance();
        }
        else {
            throw new IllegalArgumentException("argument has to be one of \"postgres\" or \"derby\"");
        }
        try (InputStream input = new FileInputStream(
                "C:\\Users\\sulyt\\myprojects\\Railway ticket office\\railway\\app.properties")) {

            final Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            URL = prop.getProperty("connection.url");
            HikariConfig config = new HikariConfig();
            if (driverName.equals("postgres")) {
                config.setDriverClassName("org.postgresql.Driver");
            }
            config.setJdbcUrl(URL);
            dataSource = new HikariDataSource(config);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return manager;
    }

    public List<Train> getAllTrains() throws SQLException {
        List<Train> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(DBConstants.GET_ALL_TRAINS);) {
            
            while (resultSet.next()) {
                mapTrain(result, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } 
        return result;
    }

    private void mapTrain(List<Train> result, ResultSet resultSet) throws SQLException {
        String startingTime = resultSet.getString("startingTime");
        String startingName = resultSet.getString("startingName");
        String startingCity = resultSet.getString("startingCity");
        String finalTime = resultSet.getString("finalTime");
        String finalName = resultSet.getString("finalName");
        String finalCity = resultSet.getString("finalCity");
        String date = resultSet.getString("date");
        Double cost = resultSet.getDouble("cost");
        int seats = resultSet.getInt("seats");
        int number = resultSet.getInt("id");

        result.add(new Train(new Route(new Station(startingName, startingCity),
                startingTime,
                new Station(finalName, finalCity),
                finalTime),
                date, number, cost, seats));
    }

    public List<Route> getAllRoutes() throws SQLException {
        List<Route> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(DBConstants.GET_ALL_ROUTES);) {
            
            while (resultSet.next()) {
                mapRoute(result, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } 
        return result;
    }

    private void mapRoute(List<Route> result, ResultSet resultSet) throws SQLException {
        String startingTime = resultSet.getString("startingTime");
        String startingName = resultSet.getString("startingName");
        String startingCity = resultSet.getString("startingCity");
        String finalTime = resultSet.getString("finalTime");
        String finalName = resultSet.getString("finalName");
        String finalCity = resultSet.getString("finalCity");

        result.add(new Route(new Station(startingName, startingCity), 
                             startingTime, 
                             new Station(finalName, finalCity), 
                             finalTime));
    }

    public List<Station> getAllStations() {
        return null;
    }

    public void addTrain(Train train) {

    }

    public void addRoute(Route route) {

    }

    public void addStation(Station station) {

    }
}
