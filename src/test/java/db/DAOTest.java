package db;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import db.entities.Route;
import db.entities.Station;
import db.entities.Ticket;
import db.entities.Train;
import db.entities.User;

public class DAOTest {
    
    AbstractDAO dao = mock(AbstractDAO.class);

    List<Station> stations = List.of(new Station("Station name(1)", "City(1)"), 
                                    new Station("Station name(2)", "City(2)"),
                                    new Station("Station name(3)", "City(3)"),
                                    new Station("Station name(4)", "City(4)"));

    List<Route> routes = List.of(new Route(stations.get(0), "12:00:00", stations.get(1), "12:00:00"),
                                new Route(stations.get(2), "12:00:00", stations.get(3), "12:00:00"));

    List<Train> trains = List.of(new Train(routes.get(0), "01.01.2023", 50.5),
                                new Train(routes.get(1), "01.01.2023", 50.5));

    User user = new User(1L, "email", "password");

    Ticket ticket = new Ticket(1L, 1L);

    @Test
    public void getAllStationsTest() {
        try {
            Mockito.when(dao.getAllStations(1)).thenReturn(List.of(stations.get(0), 
                                                                        stations.get(1)));
            
            
            List<Station> result = dao.getAllStations(1);

            assert(result.get(0).equals(stations.get(0)));
            assert(result.get(1).equals(stations.get(1)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   }

    @Test
    public void getAllRoutesTest() {
        try {
            Mockito.when(dao.getAllRoutes(1)).thenReturn(List.of(routes.get(0), 
                                                                        routes.get(1)));
            
            
            List<Route> result = dao.getAllRoutes(1);

            assert(result.get(0).equals(routes.get(0)));
            assert(result.get(1).equals(routes.get(1)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public void getAllTrainsTest() {
        try {
            Mockito.when(dao.getAllTrains(1)).thenReturn(List.of(trains.get(0), 
                                                                    trains.get(1)));
            
            
            List<Train> result = dao.getAllTrains(1);

            assert(result.get(0).equals(trains.get(0)));
            assert(result.get(1).equals(trains.get(1)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void getTrainsByRouteTest() {
        try {
            Mockito.when(dao.getTrainsByRoute(1L, 1)).thenReturn(List.of(trains.get(0), 
                                                                                    trains.get(1)));
            
            
            List<Train> result = dao.getTrainsByRoute(1L, 1);

            assert(result.get(0).equals(trains.get(0)));
            assert(result.get(1).equals(trains.get(1)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void getTrainsForUserTest() {
        try {
            Mockito.when(dao.getTrainsForUser(user, 1)).thenReturn(List.of(trains.get(0), 
                                                                                trains.get(1)));
            
            
            List<Train> result = dao.getTrainsForUser(user, 1);

            assert(result.get(0).equals(trains.get(0)));
            assert(result.get(1).equals(trains.get(1)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void getUserTest() {
        try {
            Mockito.when(dao.getUser("email")).thenReturn(Optional.of(user));
            
            
            Optional<User> result = dao.getUser("email");

            assert(result.equals(Optional.of(user)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void logInTest() {
        try {
            Mockito.when(dao.logIn("email", "password")).thenReturn(Optional.of(user));
            
            
            Optional<User> result = dao.logIn("email", "password");

            assert(result.equals(Optional.of(user)));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void signUpTest() {
        try {
            Mockito.when(dao.signUp("email", "password")).thenReturn(1L);
            
            Long result = dao.signUp("email", "password");

            assert(result.equals(1L));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void addTicketTest() {
        try {
            Mockito.when(dao.addTicket(1L, 1L)).thenReturn(ticket);
            
            Ticket result = dao.addTicket(1L, 1L);

            assert(result.equals(ticket));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void addRouteTest() {
        try {
            Mockito.when(dao.addRoute(1L, new Time(0), 2L, new Time(0))).thenReturn(1L);
            
            Long result = dao.addRoute(1L, new Time(0), 2L, new Time(0));

            assert(result.equals(1L));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void addStationTest() {
        try {
            Mockito.when(dao.addStation("name", "city")).thenReturn(1L);
            
            Long result = dao.addStation("name", "city");

            assert(result.equals(1L));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void addTrainTest() {
        try {
            Mockito.when(dao.addTrain(1L, new Date(0), 50.5)).thenReturn(1L);
            
            Long result = dao.addTrain(1L, new Date(0), 50.5);

            assert(result.equals(1L));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
