package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import db.entities.Route;
import db.entities.Station;
import db.entities.Ticket;
import db.entities.Train;
import db.entities.User;

public class UserServiceTest {
    
    UserService userService = mock(UserService.class);


    List<Station> stations = List.of(new Station("Station name(1)", "City(1)"), 
                                    new Station("Station name(2)", "City(2)"),
                                    new Station("Station name(3)", "City(3)"),
                                    new Station("Station name(4)", "City(4)"));

    List<Route> routes = List.of(new Route(stations.get(0), "12:00:00", stations.get(1), "12:00:00"),
                                new Route(stations.get(2), "12:00:00", stations.get(3), "12:00:00"));

    List<Train> trains = List.of(new Train(routes.get(0), "01.01.2023", 1, 50.5, 200),
                                new Train(routes.get(1), "01.01.2023", 1, 50.5, 200));

    User user = new User(1L, "email", "password");

    Ticket ticket = new Ticket(1L, 1L);

    @Test
    public void logInTest() {
        try {
            Mockito.when(userService.login("email", "password")).thenReturn(Optional.of(user));
            
            
            Optional<User> result = userService.login("email", "password");

            assert(result.equals(Optional.of(user)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void signUpTest() {
        try {
            Mockito.when(userService.signUp("email", "password")).thenReturn(true);
            
            Boolean result = userService.signUp("email", "password");

            assert(result.equals(true));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void getTicketsTest() {
        try {
            Mockito.when(userService.getTickets("email", 1)).thenReturn(List.of(trains.get(0), 
                                                                                trains.get(1)));
            
            
            List<Train> result = userService.getTickets("email", 1);

            assertEquals(result.get(0), trains.get(0));
            assertEquals(result.get(1), trains.get(1));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
