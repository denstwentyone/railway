package entities;

public class Train {
    private Route route;
    private String date;
    private int number;
    private Double cost;
    private int seats;
    
    public Train(Route route, String date, int number, Double cost, int seats) {
        this.route = route;
        this.date = date;
        this.number = number;
        this.cost = cost;
        this.seats = seats;
    }

    public String getTravelTime() {
        return route.getFinalTime() + " - " + route.getStartingTime();
    }

    // TODO
    // public int reserveSeats(int amount) {
    //     seats -= amount;
    //     return seats;
    // }

    public Route getRoute() {
        return route;
    }

    public String getDate() {
        return date;
    }

    public int getNumber() {
        return number;
    }

    public Double getCost() {
        return cost;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return "Train [route=" + route + ", date=" + date + ", number=" + number + ", cost=" + cost + ", seats=" + seats
                + "]";
    }
    
}
