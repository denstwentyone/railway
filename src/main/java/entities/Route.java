package entities;

public class Route {
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
}
