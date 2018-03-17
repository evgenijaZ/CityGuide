package edu.kpi.jee.labs.dao;

import edu.kpi.jee.labs.Entities.Place;

/**
 * @author Yevheniia Zubrych on 16.03.2018.
 */
public class PlaceDAO extends DAO <Place, Integer> {
    private String INSERT = "INSERT INTO %s.%s (%s, %s, %s, %s) VALUES (?, ?, ?, ?);";
    private String INSERT_BY_ID = "INSERT INTO %s.%s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?);";
    private String UPDATE = "UPDATE %s.%s SET %s=?,%s=?,%s=?,%s=? WHERE %s=?;";

    private String[][] nameMapping = {{"name", "p_name"}, {"address", "p_address"}, {"latitude", "p_lat"}, {"longitude", "p_lng"},{"id", "p_id"}};
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
        return String.format(INSERT,dbName, tableName, nameMapping[0][1],nameMapping[1][1],nameMapping[2][1],nameMapping[3][1] );

    }

    @Override
    public String getInsertByIdQuery() {
        return String.format(INSERT_BY_ID, dbName, tableName,nameMapping[0][1],nameMapping[1][1],nameMapping[2][1],nameMapping[3][1],nameMapping[4][1] );
    }

    @Override
    public String getUpdateQuery() {
        return String.format(UPDATE, dbName, tableName,nameMapping[0][1],nameMapping[1][1],nameMapping[2][1],nameMapping[3][1],nameMapping[4][1] );

    }
}
