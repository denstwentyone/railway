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

    private static Connection connection;
    private static DBManager instance;
    private static String URL;

    {
        try (InputStream input = new FileInputStream("app.properties")) {
    
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

    private DBManager() {
        if (connection == null) {
			try {
				connection = DriverManager.getConnection(URL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }

    public List<Train> getAllTrains() {
        List<Train> result = new ArrayList<>();
        try {
			Statement statement =  connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM public.train;");
			while (resultSet.next()) {
				// result.add(new Train(new Route(new Station(DATABASE_URL, DATABASE_URL), 
                //                             DATABASE_URL, 
                //                             new Station(DATABASE_URL, DATABASE_URL), 
                //                             DATABASE_URL), 
                //         DATABASE_URL, 
                //         0, 
                //         null, 
                //         0));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
    }
}
