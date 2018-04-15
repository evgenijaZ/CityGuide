package edu.kpi.jee.labs.entities;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
public class Rating {
    private edu.kpi.jee.labs.entities.User user;
    private edu.kpi.jee.labs.entities.Place place;
    private float value;

    public Rating(edu.kpi.jee.labs.entities.User user, edu.kpi.jee.labs.entities.Place place, float value) {
        this.user = user;
        this.place = place;
        this.value = value;
    }

    public edu.kpi.jee.labs.entities.User getUser() {
        return user;
    }

    public edu.kpi.jee.labs.entities.Place getPlace() {
        return place;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
