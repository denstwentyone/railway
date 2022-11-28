package db;

public abstract class DBConstants {
    private DBConstants() {

    }

    public static String GET_ALL_TRAINS = "SELECT r.\"startingTime\", ss.name startingName, ss.city startingCity, " +
                                            "r.\"finalTime\", sf.name finalName, sf.city finalCity, " + 
                                            "t.date, t.cost, t.seats, t.id " + 
                                            "FROM train t " + 
                                            "join route r on t.route = r.id " + 
                                            "join station ss on ss.id = r.\"startingStation\" " + 
                                            "join station sf on sf.id = r.\"finalStation\"";
}
