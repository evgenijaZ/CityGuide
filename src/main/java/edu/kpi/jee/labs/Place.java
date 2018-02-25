package edu.kpi.jee.labs;

/**
 * @author Yevheniia Zubrych on 25.02.2018.
 */
public class Place {
    private String name;
    private String address;
    private double latitude;
    private double longitude;

    public Place(String name, String address, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
