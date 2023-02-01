package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Date;
import java.util.*;

import org.apache.log4j.Logger;

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

    final static Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

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
            Thread.currentThread().getContextClassLoader().getResource("").getPath() + "app.properties" )) {
            
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

    /* (non-Javadoc)
     * @see db.dao.TrainDAO#getAllTrains()
     */
    @Override
    public List<Train> getAllTrains(Integer page) throws SQLException {
        List<Train> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.GET_ALL_TRAINS);) {
            
            statement.setInt(1, page);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mapTrain(result, resultSet);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
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
        int seats = resultSet.getInt("seats");

        result.add(new Train(new Route(new Station(startingName, startingCity),
                startingTime,
                new Station(finalName, finalCity),
                finalTime),
                date, number, cost, seats));
    }

    /* (non-Javadoc)
     * @see db.dao.TrainDAO#getAllRoutes()
     */
    @Override
    public List<Route> getAllRoutes(Integer page) throws SQLException {
        List<Route> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.GET_ALL_ROUTES);) {
            
            statement.setInt(1, page);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mapRoute(result, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
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
        Integer id = resultSet.getInt("id");


        Route route = new Route(new Station(startingName, startingCity), 
                                    startingTime, 
                                    new Station(finalName, finalCity), 
                                    finalTime);
        route.setId(id);
        result.add(route);
        
    }

    /* (non-Javadoc)
     * @see db.dao.TrainDAO#getAllStations()
     */
    @Override
    public List<Station> getAllStations(Integer page) throws SQLException {
        List<Station> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.GET_ALL_STATIONS);) {
            
            statement.setInt(1, page);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Station station = new Station(resultSet.getString("name"), resultSet.getString("city"));
                station.setId(resultSet.getInt("id"));
                result.add(station);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
            throw new SQLException("Something went wrong while getting station information");

        } 
        return result;
    }

    /**
     * @return
     * @throws SQLException
     */
    public List<Station> getAllStations() throws SQLException {
        List<Station> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.GET_ALL_STATIONS_NO_PAGINATION);) {
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Station station = new Station(resultSet.getString("name"), resultSet.getString("city"));
                station.setId(resultSet.getInt("id"));
                result.add(station);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
            throw new SQLException("Something went wrong while getting station information");

        } 
        
        return result;
    }

    /* (non-Javadoc)
     * @see db.dao.TrainDAO#getTrainsByRoute(java.lang.Long)
     */
    @Override
    public List<Train> getTrainsByRoute(Long routeId, Integer page) throws SQLException {
        List<Train> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.GET_TRAINS_BY_ROUTE_ID)) {
            
            statement.setLong(1, routeId);
            statement.setInt(2, page);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mapTrain(result, resultSet);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
            throw new SQLException("Something went wrong while getting trains information");
        } 
        return result;
    }

    /* (non-Javadoc)
     * @see db.dao.TrainDAO#addTrain(java.lang.Long, java.sql.Date, java.lang.Double)
     */
    @Override
    public long addTrain(Long route, Date date, Double cost, int seats) throws SQLException {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.INSERT_TRAIN, Statement.RETURN_GENERATED_KEYS)) {
                int k = 0;
                statement.setLong(++k, route);
                statement.setDate(++k, date);
                statement.setDouble(++k, cost);
                statement.setInt(++k, seats);
                statement.executeUpdate();
                statement.getGeneratedKeys();
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getLong("id");
                }
                return 0;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e);
            throw new SQLException("Something went wrong while adding train information");

        }
    }

    /* (non-Javadoc)
     * @see db.dao.TrainDAO#addRoute(java.lang.Long, java.sql.Time, java.lang.Long, java.sql.Time)
     */
    public long addRoute(Long startingStation, Time startingTime, Long finalStation, Time finalTime) throws SQLException {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.INSERT_ROUTE, Statement.RETURN_GENERATED_KEYS)) {
                int k = 0;
                statement.setLong(++k, startingStation);
                statement.setTime(++k, startingTime);
                statement.setLong(++k, finalStation);
                statement.setTime(++k, finalTime);
                statement.executeUpdate();
                statement.getGeneratedKeys();
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getLong("id");
                }
                return 0;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e);
            throw new SQLException("Something went wrong while adding route information");

        }
    }

    /* (non-Javadoc)
     * @see db.dao.TrainDAO#addStation(java.lang.String, java.lang.String)
     */
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
            LOGGER.error(e);
            throw new SQLException("Something went wrong while adding station information");

        }
    }

    /* (non-Javadoc)
     * @see db.dao.UserDAO#signUp(java.lang.String, java.lang.String)
     */
    @Override
    public long signUp(String email, String password) throws SQLException {

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
            LOGGER.error(e);
            throw new SQLException("Something went wrong while sign up");

        }
    }

    /* (non-Javadoc)
     * @see db.dao.UserDAO#logIn(java.lang.String, java.lang.String)
     */
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
                    result = Optional.of(new User(resultSet.getLong("id"), 
                                            resultSet.getString("email"), 
                                            resultSet.getString("password")));
                    result.get().setRole(resultSet.getString("role"));
                }
                return result;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e);
            throw new SQLException("Something went wrong while log in");

        }
    }

    /* (non-Javadoc)
     * @see db.dao.TrainDAO#addTicket(long, long)
     */
    @Override
    public Ticket addTicket(long trainId, long userId) throws SQLException {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.INSERT_TICKET, Statement.RETURN_GENERATED_KEYS);) {
                int k = 0;
                statement.setLong(++k, trainId);
                statement.setLong(++k, userId);
                statement.executeUpdate();
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    return new Ticket(keys.getLong("train"), keys.getLong("user"));
                }
                throw new SQLException("Something went wrong while adding order information");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e);
            throw new SQLException("Something went wrong while adding order information");

        }
    }

    /* (non-Javadoc)
     * @see db.dao.UserDAO#getUser(java.lang.String)
     */
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
            LOGGER.error(e);
            throw new SQLException("Something went wrong while getting user information");

        } 
        return result;
    }

    /* (non-Javadoc)
     * @see db.dao.UserDAO#getTrainsForUser(db.entities.User)
     */
    @Override
    public List<Train> getTrainsForUser(User user, Integer page) throws SQLException {
        List<Train> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBConstants.GET_TRAINS_BY_USER);) {
            
            statement.setLong(1, user.getId());
            statement.setInt(2, page);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mapTrain(result, resultSet);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
            throw new SQLException("Something went wrong while getting trains information");
        } 
        return result;
    }

    public List<Route> getRoutesByStations(Long from, Long to) throws SQLException  {
        List<Route> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(DBConstants.GET_ROUTES_BY_STATIONS);) {
            int k = 0;
            statement.setLong(++k, from);
            statement.setLong(++k, to);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mapRoute(result, resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e);
            throw new SQLException("Something went wrong while getting trains information");
        }
        return result;
    }

    @Override
    public Integer getReservedSeats(long trainId) throws SQLException {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(DBConstants.COUNT_TICKETS);) {
            int k = 0;
            statement.setLong(++k, trainId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e);
            throw new SQLException("Something went wrong while getting seats information");
        }
    }

    
}
