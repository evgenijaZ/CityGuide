package edu.kpi.jee.labs.entities;

import java.util.List;

/**
 * @author Yevheniia Zubrych on 01.03.2018.
 */
public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    private List <Rating> ratings;


    public User(String userName, String email, String password, List <Rating> ratings) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.ratings = ratings;
    }

    public User(int id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void ratePlace(Place place, float value) {
        Rating rating = new Rating(this, place, value);
        ratings.add(rating);
        place.addRating(rating);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List <Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List <Rating> ratings) {
        this.ratings = ratings;
    }
}
