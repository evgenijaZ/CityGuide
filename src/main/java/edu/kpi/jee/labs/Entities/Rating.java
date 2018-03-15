package edu.kpi.jee.labs.Entities;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
public class Rating {
    private User user;
    private Place place;
    private float value;

    public Rating(User user, Place place, float value) {
        this.user = user;
        this.place = place;
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public Place getPlace() {
        return place;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
