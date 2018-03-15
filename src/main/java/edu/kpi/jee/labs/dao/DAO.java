package edu.kpi.jee.labs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
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
                E entity = Specifics.getEntityFromResultSet(resultSet, tableName);
                if (entity != null)
                    entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public boolean update(E entity) {
        PreparedStatement preparedStatement = handler.getPrepareStatement(Specifics.getUpdateQuery(tableName));
        try {
            preparedStatement = Specifics.prepareStatement(dbName, tableName, preparedStatement, entity);
            return preparedStatement != null && preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public E getByKey(K key) {
        List <E> entities = new ArrayList <>();
        PreparedStatement statement = handler.getPrepareStatement(Specifics.SELECT_BY_ID);
        try {
            statement.setString(1, dbName);
            statement.setString(2, tableName);
            statement.setObject(3, key);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                E entity = Specifics.getEntityFromResultSet(resultSet, tableName);
                if (entity != null)
                    entities.add(entity);
            }
            if (entities.size() == 1)
                return entities.get(0);
            else throw new SQLDataException("Wrong number of result parameters");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(E entity) {
        PreparedStatement statement = handler.getPrepareStatement(Specifics.DELETE_BY_ID);
        try {
            statement.setString(1, dbName);
            statement.setString(2, tableName);
            statement.setObject(3, Specifics. <E, K>getPrimaryKey(tableName, entity));
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean create(E entity) {
        K key = Specifics. <E, K>getPrimaryKey(tableName, entity);
        PreparedStatement statement;
        if (key != null && key.equals(-1))
            statement = handler.getPrepareStatement(Specifics.INSERT);
        else
            statement = handler.getPrepareStatement(Specifics.INSERT_BY_ID);
        try {
            statement = Specifics.prepareStatement(dbName, tableName, statement, entity);
            return statement != null && statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
