package edu.kpi.jee.labs.entities;

import java.util.List;

/**
 * @author Yevheniia Zubrych on 25.02.2018.
 */
public class Category {
    private int id;
    private String name;
    private List <edu.kpi.jee.labs.entities.Place> places;

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, List <edu.kpi.jee.labs.entities.Place> places) {
        this.name = name;
        this.places = places;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(int id, String name, List <edu.kpi.jee.labs.entities.Place> places) {
        this.id = id;
        this.name = name;
        this.places = places;
    }

    public void addPlace(edu.kpi.jee.labs.entities.Place place) {
        places.add(place);
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

    public List <edu.kpi.jee.labs.entities.Place> getPlaces() {
        return places;
    }

    public void setPlaces(List <edu.kpi.jee.labs.entities.Place> places) {
        this.places = places;
    }
}
