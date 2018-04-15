package edu.kpi.jee.labs.dao;

import edu.kpi.jee.labs.entities.Place;

import java.util.List;

/**
 * @author Yevheniia Zubrych on 16.03.2018.
 */
public class PlaceDAO extends DAO <Place, Integer> {

    private String[][] nameMapping = {{"name", "p_name"}, {"address", "p_address"}, {"latitude", "p_lat"}, {"longitude", "p_lng"}, {"id", "p_id"}};

    public PlaceDAO(String dbName, String tableName) {
        super(dbName, tableName);
    }

    @Override
    protected Class <Place> getEntityClass() {
        return Place.class;
    }

    @Override
    protected Class <Integer> getKeyClass() {
        return Integer.class;
    }

    @Override
    public String[][] getNameMapping() {
        return nameMapping;
    }


    public List <Place> getByName(String name) {
        return super.getAllByFilter("p_name = " + name);
    }
}
