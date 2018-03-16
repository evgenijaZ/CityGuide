package edu.kpi.jee.labs.dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
public abstract class DAO<E, K> implements InterfaceDAO <E, K> {

    private String SELECT_ALL = "SELECT * FROM %s.%s";
    private String SELECT_BY_ID = "SELECT * FROM %s.%s WHERE id = ?;";
    private String DELETE_BY_ID = "DELETE * FROM %s.%s WHERE id = ?;";

    private JDBCHandler handler;
    private String tableName;
    private String dbName;

    DAO(String dbName, String tableName) {
        this.tableName = tableName;
        this.dbName = dbName;
        handler = new JDBCHandler(dbName);
    }

    protected abstract Class <E> getEntityClass();

    public abstract String[][] getMapping();

    public abstract String getInsertQuery();

    public abstract String getInsertByIdQuery();

    public abstract String getUpdateQuery();

    public String getSelectAllQuery() {
        return SELECT_ALL;
    }

    public String getSelectByIdQuery() {
        return SELECT_BY_ID;
    }

    public String getDeleteByIdQuery() {
        return DELETE_BY_ID;
    }
    private Object getEntityFromResultSet(ResultSet resultSet) {
        Class <?> entityClass = this.getEntityClass();
        Object instance = null;
        try {
            instance = entityClass.newInstance();
            for (String[] aMapping : getMapping()) {
                String fieldName = aMapping[0];
                String columnName = aMapping[1];

                Field field = entityClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                String value = resultSet.getString(columnName);
                String fieldType = field.getType().getSimpleName();
                switch (fieldType) {
                    case "int": {
                        field.setInt(instance, Integer.parseInt(value));
                        break;
                    }
                    case "String": {
                        field.set(instance, value);
                        break;
                    }
                    case "double": {
                        field.setDouble(instance, Double.parseDouble(value));
                        break;
                    }
                }

            }
        } catch (NoSuchFieldException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }

        return instance;
    }

    @SuppressWarnings("unchecked")
    public List <E> getAll() {
        List <E> entities = new ArrayList <>();
        PreparedStatement statement = getPrepareStatement(SELECT_ALL);
        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // E entity = Specifics.getEntityFromResultSet(resultSet, tableName);
                Object parsingSetResult = this.getEntityFromResultSet(resultSet);
                if (parsingSetResult != null && (getEntityClass()).isInstance(parsingSetResult))
                    entities.add((E) parsingSetResult);
            }
            handler.closePrepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }


    public boolean update(E entity) {
        boolean result = false;
        PreparedStatement statement = getPrepareStatement(getUpdateQuery());
        try {
            statement = Specifics.prepareStatement(tableName, statement, entity);
            result = statement != null && statement.execute();
            handler.closePrepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public E getByKey(K key) {
        E entity = null;
        PreparedStatement statement = getPrepareStatement(getSelectAllQuery());
        try {
            statement.setObject(1, key);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.getMetaData().getColumnCount() != 1)
                throw new SQLDataException("Wrong number of result parameters");
            if (resultSet.next()) {
                Object parsingSetResult = this.getEntityFromResultSet(resultSet);
                if (parsingSetResult != null && (getEntityClass()).isInstance(parsingSetResult))
                    entity = (E) parsingSetResult;
            }
            handler.closePrepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public boolean delete(E entity) {
        boolean result = false;
        PreparedStatement statement = getPrepareStatement(getDeleteByIdQuery());
        try {
            statement.setObject(1, Specifics. <E, K>getPrimaryKey(tableName, entity));
            result = statement.execute();
            handler.closePrepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean create(E entity) {
        boolean result = false;
        boolean generateKey;
        K key = Specifics. <E, K>getPrimaryKey(tableName, entity);
        PreparedStatement statement;
        if (key == null)
            return false;
        if (key.equals(-1))
            generateKey = true;
        else
            generateKey = false;
        statement = getPrepareStatement(getInsertQuery());
        try {
            Specifics.prepareStatement(tableName, statement, entity);
            if (statement == null)
                return false;
            System.out.println(statement);
            result = statement.execute();

            if (generateKey) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        key = (K) generatedKeys.getObject(1);
                        Specifics.setPrimaryKey(tableName, entity, key);
                    } else {
                        throw new SQLException("Creating " + tableName + " failed, no ID obtained.");
                    }
                }
            }

            handler.closePrepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private PreparedStatement getPrepareStatement(String query) {
        return handler.getPrepareStatement(String.format(query, dbName, tableName));
    }

}
