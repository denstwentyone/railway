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

    public static String GET_ALL_ROUTES = "SELECT s1.name as startingName, s1.city as startingCity, \"startingTime\"," 
                                        + "s2.name as finalName, s2.city as finalCity, \"finalTime\""
                                        + "FROM route r "
                                        + "join station s1 on s1.id = r.\"startingStation\""
                                        + "join station s2 on s2.id = r.\"finalStation\"";
}
