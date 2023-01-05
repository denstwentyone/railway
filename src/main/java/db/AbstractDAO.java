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
import db.entities.Ticket;
import db.entities.Route;
import db.entities.Station;
import db.entities.Train;
import db.entities.User;

public abstract class AbstractDAO implements TrainDAO, UserDAO {

    private static HikariDataSource dataSource;

    protected AbstractDAO() {
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
                "C:\\Users\\sulyt\\myprojects\\Railway ticket office\\railway\\app.properties")) { // TODO

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
            throw new SQLException("Something went wrong while getting trains information");
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
        int number = resultSet.getInt("id");

        result.add(new Train(new Route(new Station(startingName, startingCity),
                startingTime,
                new Station(finalName, finalCity),
                finalTime),
                date, number, cost));
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
            throw new SQLException("Something went wrong while getting route information");
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
            throw new SQLException("Something went wrong while getting station information");

        } 
        return result;
    }

    public List<Train> getTrainsByCity(String city) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public long addTrain(Train train) throws SQLException {
        return 0;
    }

    public long addRoute(Route route) throws SQLException {
        return 0;
    }

    public long addStation(String name, String city) throws SQLException {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.INSERT_STATION, Statement.RETURN_GENERATED_KEYS)) {
                int k = 0;
                statement.setString(++k, name);
                statement.setString(++k, city);
                statement.executeUpdate();
                statement.getGeneratedKeys();
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getLong("id");
                }
                return 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong while adding station information");

        }
    }

    @Override
    public long signUp(String email, String password) throws SQLException {
        System.out.println("dao signup");

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.INSERT_USER, Statement.RETURN_GENERATED_KEYS);) {
                int k = 0;
                statement.setString(++k, email);
                statement.setString(++k, password);
                statement.executeUpdate();
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    long id = keys.getLong(1);
                    return id;
                }
                return 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong while sign up");

        }
    }

    @Override
    public Optional<User> logIn(String email, String password) throws SQLException {
        Optional<User> result = Optional.empty();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.FIND_USER);) {
                int k = 0;
                statement.setString(++k, email);
                statement.setString(++k, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    result = Optional.of(new User(resultSet.getLong("id"), 
                                            resultSet.getString("email"), 
                                            resultSet.getString("password")));
                    result.get().setRole(resultSet.getString("role"));
                }
                return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong while log in");

        }
    }

    @Override
    public Ticket addTicket(long trainId, long userId) throws SQLException {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.INSERT_TICKET, Statement.RETURN_GENERATED_KEYS);) {
                int k = 0;
                statement.setLong(++k, trainId);
                statement.setLong(++k, userId);
                statement.executeUpdate();
                statement.getGeneratedKeys();
                ResultSet keys = statement.getGeneratedKeys();
                System.out.println("colomns:"+keys.getMetaData().getColumnCount());
                if (keys.next()) {
                    return new Ticket(keys.getLong("train"), keys.getLong("user"));
                }
                throw new SQLException("Something went wrong while adding order information");
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong while adding order information");

        }
    }

    @Override
    public Optional<User> getUser(String email) throws SQLException {
        Optional<User> result = Optional.empty();
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(DBConstants.FIND_USER_BY_EMAIL);) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(new User(resultSet.getLong("id"), 
                                    resultSet.getString("email"), 
                                    resultSet.getString("password")));
                result.get().setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong while getting user information");

        } 
        return result;
    }

    @Override
    public List<Train> getTrainsForUser(User user) throws SQLException {
        List<Train> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(DBConstants.GET_TRAINS_BY_USER);) {
            
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                mapTrain(result, resultSet);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Something went wrong while getting trains information");
        } 
        return result;
    }
}
