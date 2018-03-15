package edu.kpi.jee.labs.Entities;

import java.util.List;

/**
 * @author Yevheniia Zubrych on 25.02.2018.
 */
public class Category {
    private int id;
    private String name;
    private List <Place> places;

    public Category(String name){
        this.name = name;
    }

    public Category(String name, List <Place> places) {
        this.name = name;
        this.places = places;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(int id, String name, List <Place> places) {
        this.id = id;
        this.name = name;
        this.places = places;
    }

    public void addPlace(Place place){
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

    public List <Place> getPlaces() {
        return places;
    }

    public void setPlaces(List <Place> places) {
        this.places = places;
    }
}
