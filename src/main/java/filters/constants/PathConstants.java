package filters.constants;

import java.util.List;

public class PathConstants {

    public static List<String> cusomerOnly = List.of(
        "/order.jsp",
        "/route.jsp"
    );

    public static List<String> notAuthorizedOnly = List.of(
        "/login.jsp",
        "/signup.jsp"
    );

    public static List<String> notAvailable = List.of(
        "/views/header.jsp",
        "/views/routes.jsp",
        "/views/stations.jsp",
        "/views/tickets.jsp",
        "/views/trains.jsp"
    );

}
