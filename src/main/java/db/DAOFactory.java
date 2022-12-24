package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import db.dao.TrainDAO;
import db.dao.UserDAO;
import db.entities.Route;
import db.entities.Station;
import db.entities.Train;

public abstract class DAOFactory implements TrainDAO, UserDAO {

    private static HikariDataSource dataSource;

    protected DAOFactory() {
    }

    /**
     * @param driverName Has to be one of "postgres" or "derby"
     * @return Manager instance of chosen db
     */
    protected static synchronized void configDManager(String driverName) {
        
        if (driverName != "derby" && driverName != "postgres") {
            throw new IllegalArgumentException("argument has to be one of \"postgres\" or \"derby\"");
        }

        try (InputStream input = new FileInputStream(
                "C:\\Users\\sulyt\\myprojects\\Railway ticket office\\railway\\app.properties")) {

            final Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            String URL = prop.getProperty("connection.url");
            HikariConfig config = new HikariConfig();
            if (driverName.equals("postgres")) {
                config.setDriverClassName("org.postgresql.Driver");
            }
            config.setJdbcUrl(URL);
            dataSource = new HikariDataSource(config);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    public List<Station> getAllStations() throws SQLException {
        List<Station> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(DBConstants.GET_ALL_STATIONS);) {
            
            while (resultSet.next()) {
                result.add(new Station(resultSet.getString("name"), resultSet.getString("city")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } 
        return result;
    }

    public List<Train> getTrainsByCity(String city) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public long addTrain(Train train) throws Exception {
        return 0;
    }

    public long addRoute(Route route) throws Exception {
        return 0;
    }

    public long addStation(String name, String city) throws Exception {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.INSERT_STATION);) {
                int k = 0;
                statement.setString(++k, name);
                statement.setString(++k, city);
                statement.executeUpdate();
                statement.getGeneratedKeys();
        
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    @Override
    public long signUp(String email, String password) throws SQLException {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.INSERT_USER);) {
                int k = 0;
                statement.setString(++k, email);
                statement.setString(++k, password);
                statement.executeUpdate();
                statement.getGeneratedKeys();

                return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean logIn(String email, String password) throws SQLException {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.FIND_USER);) {
                int k = 0;
                statement.setString(++k, email);
                statement.setString(++k, password);
                return statement.executeQuery().next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
}
