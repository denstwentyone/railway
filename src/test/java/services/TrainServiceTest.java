package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import db.entities.Route;
import db.entities.Station;
import db.entities.Ticket;
import db.entities.Train;

public class TrainServiceTest {
    
    TrainService trainService = mock(TrainService.class);

    List<Station> stations = List.of(new Station("Station name(1)", "City(1)"), 
                                    new Station("Station name(2)", "City(2)"),
                                    new Station("Station name(3)", "City(3)"),
                                    new Station("Station name(4)", "City(4)"));

    List<Route> routes = List.of(new Route(stations.get(0), "12:00:00", stations.get(1), "12:00:00"),
                                new Route(stations.get(2), "12:00:00", stations.get(3), "12:00:00"));

    List<Train> trains = List.of(new Train(routes.get(0), "01.01.2023", 50.5),
                                new Train(routes.get(1), "01.01.2023", 50.5));

    Ticket ticket = new Ticket(1L, 1L);

    @Test
    public void getAllTrainsTest() {
        try {
            Mockito.when(trainService.getAllTrains(1)).thenReturn(List.of(trains.get(0), trains.get(1)));

            List<Train> result = trainService.getAllTrains(1);

            assertEquals(result.get(0), trains.get(0));
            assertEquals(result.get(1), trains.get(1));
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

            assertEquals(result.get(0), routes.get(0));
            assertEquals(result.get(1), routes.get(1));
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

            assertEquals(result.get(0), stations.get(0));
            assertEquals(result.get(1), stations.get(1));
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

            assertEquals(result, ticket);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
