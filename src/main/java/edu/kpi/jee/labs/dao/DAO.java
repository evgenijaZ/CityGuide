package edu.kpi.jee.labs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
public class DAO<E, K> implements InterfaceDAO <E, K> {
    private JDBCHandler handler;
    private String tableName;
    private String dbName;

    public DAO(String dbName, String tableName) {
        this.tableName = tableName;
        this.dbName = dbName;
        handler = new JDBCHandler(dbName);
    }

    public List <E> getAll() {
        List <E> entities = new ArrayList <>();
        PreparedStatement statement = handler.getPrepareStatement(Specifics.SELECT_ALL);
        try {
            statement.setString(1, dbName);
            statement.setString(2, tableName);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                E entity = (E) Specifics.getEntityFromResultSet(resultSet, tableName);
                if (entity != null)
                    entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public boolean update(E entity) {
        int primaryKey = Specifics.getPrimaryKey(tableName, entity);
        PreparedStatement preparedStatement = handler.getPrepareStatement(Specifics.getUpdateQuery(tableName));
        try {
            preparedStatement = Specifics.preparedUpdateStatement(dbName, tableName, preparedStatement, entity);
            return preparedStatement != null && preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public E getByKey(K key) {
        return null;
    }

    public boolean delete(E entity) {
        return false;
    }

    public boolean deleteByKey(K key) {
        return false;
    }

    public boolean create(E entity) {
        return false;
    }


}
