import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import db.AbstractDAO;
import db.entities.Station;
import db.postgres.PostgresDAO;

public class DAOTest {
    
    @Mock
    AbstractDAO dao = PostgresDAO.getInstance();

    @BeforeEach
   public void setup() {
        MockitoAnnotations.initMocks(this);
   }

   @Test
   public void testDAO() {
        try {
            Mockito.when(dao.getAllStations(1)).thenReturn(List.of(new Station("Station name(1)", "City(1)"), 
                                                                        new Station("Station name(2)", "City(2)")));
            
            
            List stations = dao.getAllStations(1);

            assert(stations.get(0).equals(new Station("Station name(1)", "City(1)")));
            assert(stations.get(1).equals(new Station("Station name(2)", "City(2)")));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

   }
}
