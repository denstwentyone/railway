package db;

public abstract class DBConstants {
    private DBConstants() {

    }

    public static String GET_ALL_TRAINS = "SELECT r.\"startingTime\", ss.name startingName, ss.city startingCity, " +
                                            "r.\"finalTime\", sf.name finalName, sf.city finalCity, " + 
                                            "t.date, t.cost, t.id, t.seats " + 
                                            "FROM train t " + 
                                            "join route r on t.route = r.id " + 
                                            "join station ss on ss.id = r.\"startingStation\" " + 
                                            "join station sf on sf.id = r.\"finalStation\"LIMIT 3 OFFSET 3*(?-1)";

    public static String GET_TRAINS_BY_ROUTE_ID = "SELECT r.\"startingTime\", ss.name startingName, ss.city startingCity, " +
                                            "r.\"finalTime\", sf.name finalName, sf.city finalCity, " + 
                                            "t.date, t.cost, t.id, t.seats " + 
                                            "FROM train t " + 
                                            "join route r on t.route = r.id " + 
                                            "join station ss on ss.id = r.\"startingStation\" " + 
                                            "join station sf on sf.id = r.\"finalStation\" WHERE t.route = ? LIMIT 3 OFFSET 3*(?-1)";

    public static String GET_ALL_ROUTES = "SELECT s1.name as startingName, s1.city as startingCity, \"startingTime\"," 
                                        + "s2.name as finalName, s2.city as finalCity, \"finalTime\", r.id "
                                        + "FROM route r "
                                        + "join station s1 on s1.id = r.\"startingStation\""
                                        + "join station s2 on s2.id = r.\"finalStation\" LIMIT 3 OFFSET 3*(?-1)";

    public static String GET_ROUTES_BY_STATIONS = "SELECT s1.name as startingName, s1.city as startingCity, \"startingTime\"," 
                                        + "s2.name as finalName, s2.city as finalCity, \"finalTime\", r.id "
                                        + "FROM route r "
                                        + "join station s1 on s1.id = r.\"startingStation\""
                                        + "join station s2 on s2.id = r.\"finalStation\" WHERE s1.id = ? AND s2.id = ?";

    public static String GET_ALL_STATIONS = "SELECT * FROM station LIMIT 3 OFFSET 3*(?-1)";

    public static String GET_ALL_STATIONS_NO_PAGINATION = "SELECT * FROM station";

    public static String INSERT_STATION = "INSERT INTO station(name, city) VALUES (?, ?)";

    public static String INSERT_ROUTE = "INSERT INTO route(\"startingStation\", \"startingTime\", \"finalStation\", \"finalTime\") VALUES (?, ?, ?, ?)";

    public static String INSERT_TRAIN = "INSERT INTO train(route, date, cost, seats) VALUES (?, ?, ?, ?)";

    public static String INSERT_USER = "INSERT INTO \"user\"(email, password) VALUES (?, ?)";

    public static String FIND_USER = "SELECT * FROM \"user\" WHERE email = ? AND password = ?";

    public static String FIND_USER_BY_EMAIL = "SELECT * FROM \"user\" WHERE email = ?";

    public static String INSERT_TICKET = "insert into ticket (train, \"user\") values (?, ?)";

    public static String GET_TRAINS_BY_USER = "SELECT r.\"startingTime\", ss.name startingName, ss.city startingCity, " +
                                              "r.\"finalTime\", sf.name finalName, sf.city finalCity, " + 
                                              "t.date, t.cost, t.id, t.seats " + 
                                              "FROM train t " + 
                                              "join route r on t.route = r.id " + 
                                              "join station ss on ss.id = r.\"startingStation\" " + 
                                              "join station sf on sf.id = r.\"finalStation\"" + 
                                              "JOIN ticket tic ON t.id = tic.train WHERE tic.user = ? LIMIT 3 OFFSET 3*(?-1)";

    public static String COUNT_TICKETS = "SELECT COUNT(user) FROM ticket WHERE train = ?";

}
