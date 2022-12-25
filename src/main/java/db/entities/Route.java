package db.entities;

import java.io.Serializable;

public class Route implements Serializable {
    private long id;
    private Station startingStation;
    private String startingTime;
    private Station finalStation;
    private String finalTime;
    
    public Route(Station startingStation, String startingTime, Station finalStation, String finalTime) {
        this.startingStation = startingStation;
        this.startingTime = startingTime;
        this.finalStation = finalStation;
        this.finalTime = finalTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Station getStartingStation() {
        return startingStation;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public Station getFinalStation() {
        return finalStation;
    }

    public String getFinalTime() {
        return finalTime;
    }

    @Override
    public String toString() {
        return "Route [startingStation=" + startingStation + ", startingTime=" + startingTime + ", finalStation="
                + finalStation + ", finalTime=" + finalTime + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((startingStation == null) ? 0 : startingStation.hashCode());
        result = prime * result + ((startingTime == null) ? 0 : startingTime.hashCode());
        result = prime * result + ((finalStation == null) ? 0 : finalStation.hashCode());
        result = prime * result + ((finalTime == null) ? 0 : finalTime.hashCode());
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
        Route other = (Route) obj;
        if (startingStation == null) {
            if (other.startingStation != null)
                return false;
        } else if (!startingStation.equals(other.startingStation))
            return false;
        if (startingTime == null) {
            if (other.startingTime != null)
                return false;
        } else if (!startingTime.equals(other.startingTime))
            return false;
        if (finalStation == null) {
            if (other.finalStation != null)
                return false;
        } else if (!finalStation.equals(other.finalStation))
            return false;
        if (finalTime == null) {
            if (other.finalTime != null)
                return false;
        } else if (!finalTime.equals(other.finalTime))
            return false;
        return true;
    }

    public long getId() {
        return id;
    }

    
}
