package edu.kpi.jee.labs.dao;

import edu.kpi.jee.labs.Entities.Place;

/**
 * @author Yevheniia Zubrych on 16.03.2018.
 */
public class PlaceDAO extends DAO <Place, Integer> {
    private String INSERT = "INSERT INTO %s.%s (name,address,lat,lng) VALUES (?, ?, ?, ?);";
    private String INSERT_BY_ID = "INSERT INTO %s.%s (name,address,lat,lng,id) VALUES (?, ?, ?, ?,?);";
    private String UPDATE = "UPDATE %s.%s SET name=?,address=?,lat=?,lng=? WHERE id=?;";

    private String[][] nameMapping = {{"name", "name"}, {"address", "address"}, {"latitude", "lat"}, {"longitude", "lng"},{"id", "id"}};
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

    @Override
    public String getInsertQuery() {
        return INSERT;
    }

    @Override
    public String getInsertByIdQuery() {
        return INSERT_BY_ID;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE;
    }
}
