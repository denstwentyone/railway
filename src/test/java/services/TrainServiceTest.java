package services;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import db.entities.Route;
import db.entities.Station;
import db.entities.Ticket;
import db.entities.Train;
import db.postgres.PostgresDAO;

public class TrainServiceTest {
    
    @Mock
    TrainService trainService = new TrainService(PostgresDAO.getInstance());

    List<Station> stations = List.of(new Station("Station name(1)", "City(1)"), 
                                    new Station("Station name(2)", "City(2)"),
                                    new Station("Station name(3)", "City(3)"),
                                    new Station("Station name(4)", "City(4)"));

    List<Route> routes = List.of(new Route(stations.get(0), "12:00:00", stations.get(1), "12:00:00"),
                                new Route(stations.get(2), "12:00:00", stations.get(3), "12:00:00"));

    List<Train> trains = List.of(new Train(routes.get(0), "01.01.2023", 50.5),
                                new Train(routes.get(1), "01.01.2023", 50.5));

    Ticket ticket = new Ticket(1L, 1L);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTrainsTest() {
        try {
            Mockito.when(trainService.getAllTrains(1)).thenReturn(List.of(trains.get(0), trains.get(1)));

            List<Train> result = trainService.getAllTrains(1);

            assert(result.get(0).equals(trains.get(0)));
            assert(result.get(1).equals(trains.get(1)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void getAllRoutesTest() {
        try {
            Mockito.when(trainService.getAllRoutes(1)).thenReturn(List.of(routes.get(0), 
                                                                        routes.get(1)));
            
            
            List<Route> result = trainService.getAllRoutes(1);

            assert(result.get(0).equals(routes.get(0)));
            assert(result.get(1).equals(routes.get(1)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void getAllStationsTest() {
        try {
            Mockito.when(trainService.getAllStations(1)).thenReturn(List.of(stations.get(0), 
                                                                        stations.get(1)));
            
            
            List<Station> result = trainService.getAllStations(1);

            assert(result.get(0).equals(stations.get(0)));
            assert(result.get(1).equals(stations.get(1)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   }

   @Test
    public void orderTest() {
        try {
            Mockito.when(trainService.order(1L, "email")).thenReturn(ticket);
            
            Ticket result = trainService.order(1L, "email");

            assert(result.equals(ticket));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
