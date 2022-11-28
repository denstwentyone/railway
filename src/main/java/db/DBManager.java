package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import entities.Route;
import entities.Station;
import entities.Train;

public class DBManager {

    // private static Connection connection;
    private static DBManager instance;
    private static String URL;

    // private List<Train> trains;
    // private List<Route> routes;
    // private List<Station> stations;



    {
        try (InputStream input = new FileInputStream("C:\\Users\\sulyt\\myprojects\\Railway ticket office\\railway\\app.properties")) {
    
          final Properties prop = new Properties();
    
          // load a properties file
          prop.load(input);
    
          // get the property value and print it out
          URL = prop.getProperty("connection.url");
          
    
        } catch (IOException ex) {
          ex.printStackTrace();
        }
    }

    public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

    // private DBManager() {
    //     if (connection == null) {
	// 		try {
	// 			connection = DriverManager.getConnection(URL);
	// 		} catch (SQLException e) {
	// 			e.printStackTrace();
	// 		}
	// 	}
    // }

    public List<Train> getAllTrains() throws SQLException {
        List<Train> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(DBConstants.GET_ALL_TRAINS);) {
                while (resultSet.next()) {
                    mapTrain(result, resultSet);
                }
		}
		catch (SQLException e) {
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

    public List<Route> getAllRoutes() {
        return null;
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
    public static void main(String[] args) {
        try {
            System.out.println(DBManager.getInstance().getAllTrains());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
