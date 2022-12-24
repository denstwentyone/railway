import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import org.junit.jupiter.api.*;

import db.DAOFactory;
import db.derby.DerbyDAO;
import db.entities.Station;
import db.entities.Train;

public class DBManagerTest {

    private static final String CONNECTION_URL = "jdbc:derby:memory:testdb;create=true";
    private static final String SHUTDOWN_URL = "jdbc:derby:;shutdown=true";
    private static final String APP_CONTENT = "connection.url=" + CONNECTION_URL;
    private static final String APP_PROPS_FILE = "app.properties";

    private static final String CREATE_STATION_TABLE =
			"CREATE TABLE station ("
			+ "	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," 
			+ "	name VARCHAR(50) NOT NULL,"
			+ "	city VARCHAR(50) NOT NULL" 
			+ ")";

    private static final String CREATE_ROUTE_TABLE = 
			"CREATE TABLE route("
			+ "	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," 
			+ "	\"startingStation\" INT REFERENCES station(id) on delete cascade," 
			+ "	\"startingTime\" TIME NOT NULL," 
			+ "	\"finalStation\" INT REFERENCES station(id) on delete cascade,"
			+ "	\"finalTime\" TIME NOT NULL" 
			+ ")";

    private static final String CREATE_TRAIN_TABLE =
			"CREATE TABLE train ("
			+ "	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," 
			+ "	route INT REFERENCES route(id) on delete cascade,"
			+ "	date DATE NOT NULL,"
			+ "	cost DECIMAL NOT NULL,"
			+ "	seats INT NOT NULL" 
			+ ")";

    private static Connection con;
	
	private static String userDefinedAppContent;
	
	@BeforeAll
	static void globalSetUp() throws SQLException, IOException {
		userDefinedAppContent = Files.readString(Path.of(APP_PROPS_FILE));
	    Files.write(Path.of(APP_PROPS_FILE), APP_CONTENT.getBytes());

		con = DriverManager.getConnection(CONNECTION_URL);
	}

	@AfterAll
	static void globalTearDown() throws SQLException, IOException {
		con.close();
		try {
			DriverManager.getConnection(SHUTDOWN_URL);
		} catch (SQLException ex) {
			System.err.println("Derby shutdown");
		}
		
		try (PrintWriter out = new PrintWriter(APP_PROPS_FILE)) {
			out.print(userDefinedAppContent);
		}
	}

	private DAOFactory dbm;
	
	@BeforeEach
	void setUp() throws SQLException {
		// dbm = DBManager.getDManager("derby");
		dbm = DerbyDAO.getInstance();


		con.createStatement().executeUpdate(CREATE_STATION_TABLE);
		con.createStatement().executeUpdate(CREATE_ROUTE_TABLE);
		con.createStatement().executeUpdate(CREATE_TRAIN_TABLE);

	}

	@AfterEach
	void tearDown() throws SQLException {
		con.createStatement().executeUpdate("DROP TABLE train");
        con.createStatement().executeUpdate("DROP TABLE route");
        con.createStatement().executeUpdate("DROP TABLE station");
	}

    @Test
    public void Test() {
		try {
			List<Station> st = new ArrayList<>();
			st.add(new Station("test", "test"));
			for (Station station : st) {
				dbm.addStation(station.getName(), station.getCity());
			}
			assertEquals(st, dbm.getAllStations());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals(1, 1);
    }
}
