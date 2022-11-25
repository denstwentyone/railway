package entities;

public class Station {
    private String city;
    private String name;

    public Station(String city, String name) {
        this.city = city;
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }
}
