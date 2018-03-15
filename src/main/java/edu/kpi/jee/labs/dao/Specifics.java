package edu.kpi.jee.labs.dao;

import edu.kpi.jee.labs.Entities.Place;
import edu.kpi.jee.labs.dao.fabrics.PlaceCreator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
class Specifics<E, K> {
    static String SELECT_ALL = "SELECT * FROM `?`.`?`";
    static String SELECT_BY_ID = "SELECT * FROM `?`.`?` WHERE `?`.`id` = '?'";
    static String DELETE_BY_ID = "DELETE * FROM `?`.`?` WHERE `?`.`id` = '?'";
    static String INSERT = "INSERT INTO `?`.`?` (`name`,`address`,`lat`,`lng`,`id`) VALUES ('?', '?', '?', '?', '?')";
    static String INSERT_BY_ID = "INSERT INTO `?`.`?` (`name`,`address`,`lat`,`lng`) VALUES ('?', '?', '?', '?') WHERE `id`='?'";
    private static String UPDATE_PLACE = "UPDATE `?`.`?` SET `name`='?',`address`='?',`lat`='?',`lng`='?' WHERE `id`='?'";

    static <E, K> K getPrimaryKey(String tableName, E item) {
        switch (tableName) {
            case "Place":
                return (K) (((Place) item).getId());
            default:
                return null;
        }
    }

    static <E> E getEntityFromResultSet(ResultSet resultSet, String tableName) throws SQLException {
        switch (tableName) {
            case "Place":
                return (E) (new PlaceCreator()).create(resultSet);
            default:
                return null;
        }
    }

    static <E> PreparedStatement prepareStatement(String dbName, String tableName, PreparedStatement statement, E item) throws SQLException {
        switch (tableName) {
            case "Place": {
                Place place = (Place) item;
                statement.setString(1, dbName);
                statement.setString(2, tableName);
                statement.setString(3, place.getName());
                statement.setString(4, place.getAddress());
                statement.setFloat(5, (float) place.getLatitude());
                statement.setFloat(6, (float) place.getLongitude());
                statement.setInt(7, place.getId());
                return statement;
            }
            default:
                return null;
        }
    }

    static String getUpdateQuery(String tableName) {
        switch (tableName) {
            case "Place": {
                return UPDATE_PLACE;
            }
            default:
                return null;
        }
    }
}
