package entities;

import org.springframework.data.annotation.Id;

public class Airport {

    @Id
    private String id;

    private String iataCode;
    private String city;
    private double[] coordinate;

    public Airport(String iataCode, String city, double[] coordinate) {
        this.iataCode = iataCode;
        this.city = city;
        this.coordinate = coordinate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(double[] coordinate) {
        this.coordinate = coordinate;
    }
}
