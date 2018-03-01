package edu.kpi.jee.labs.Entities;

/**
 * @author Yevheniia Zubrych on 25.02.2018.
 */
public class Place {
    private int id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private double rating;

    public Place(String name, String address, double latitude, double longitude) {
        this.id = 0;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = 0;
    }

    public Place(int id, String name, String address, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = 0;
    }

    public Place(int id, String name, String address, double latitude, double longitude, double rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Place place = (Place) obj;
        return (this.latitude == place.getLatitude() &&
                this.longitude == place.getLongitude() &&
                this.rating == place.getRating() &&
                this.name.equals(place.getName()) &&
                this.address.equals(place.getAddress()));
    }
}
