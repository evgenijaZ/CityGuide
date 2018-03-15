package edu.kpi.jee.labs.Entities;

import java.util.List;

/**
 * @author Yevheniia Zubrych on 25.02.2018.
 */
public class Place {
    private int id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private List <Rating> ratings;
    private List <Category> categories;

    public Place(String name, String address, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Place(int id, String name, String address, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Place(int id, String name, String address, double latitude, double longitude, List <Rating> ratings, List <Category> categories) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ratings = ratings;
        this.categories = categories;
    }

    public void addRating(Rating rating){
        ratings.add(rating);
    }

    public float getRating() {
        float resultRating = 0;
        for (Rating rating : ratings) {
            resultRating += rating.getValue() / ratings.size();
        }
        return resultRating;
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

    public List <Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List <Rating> ratings) {
        this.ratings = ratings;
    }

    public List <Category> getCategories() {
        return categories;
    }

    public void setCategories(List <Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Place place = (Place) obj;
        return (Math.abs(this.latitude - place.getLatitude()) < 0.00001 &&
                Math.abs(this.longitude - place.getLongitude()) < 0.00001 &&
                Math.abs(this.getRating() - place.getRating()) < 0.001 &&
                this.name.equals(place.getName()) &&
                this.address.equals(place.getAddress()));
    }

    @Override
    public int hashCode() {
        return (int) (longitude * 1000 + latitude);
    }
}
