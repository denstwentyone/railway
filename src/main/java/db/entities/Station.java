package db.entities;

public class Station {
    private String name;
    private String city;

    public Station(String name, String city) {
        this.city = city;
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Station [name=" + name + ", city=" + city + "]";
    }
}
