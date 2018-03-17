package edu.kpi.jee.labs.dao;

import edu.kpi.jee.labs.Entities.Place;

import java.util.ArrayList;
import java.util.List;

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
        return String.format(INSERT,makeFormatArgs(getFieldCount()-1));

    }

    @Override
    public String getInsertByIdQuery() {
        return String.format(INSERT_BY_ID, makeFormatArgs(getFieldCount()));
    }

    @Override
    public String getUpdateQuery() {
        return String.format(UPDATE, makeFormatArgs(getFieldCount()));

    }
}
