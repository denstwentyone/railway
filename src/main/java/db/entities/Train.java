package db.entities;

import java.io.Serializable;

public class Train implements Serializable {
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
        return "Train [route=" + route + ", date=" + date + ", number=" + number + ", cost=" + cost
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((route == null) ? 0 : route.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + number;
        result = prime * result + ((cost == null) ? 0 : cost.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Train other = (Train) obj;
        if (route == null) {
            if (other.route != null)
                return false;
        } else if (!route.equals(other.route))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (number != other.number)
            return false;
        if (cost == null) {
            if (other.cost != null)
                return false;
        } else if (!cost.equals(other.cost))
            return false;
        return true;
    }
    
}
