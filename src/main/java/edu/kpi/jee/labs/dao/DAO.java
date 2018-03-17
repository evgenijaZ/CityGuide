package edu.kpi.jee.labs.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
public abstract class DAO<E, K> implements InterfaceDAO <E, K> {

    private String TRUNCATE_TABLE = "TRUNCATE TABLE %s.%s;";
    private String SELECT_ALL = "SELECT * FROM %s.%s";
    private String SELECT_BY_ID = "SELECT * FROM %s.%s WHERE %s = ?;";
    private String DELETE_BY_ID = "DELETE FROM %s.%s WHERE %s = ?;";

    private JDBCHandler handler;
    String tableName;
    String dbName;

    DAO(String dbName, String tableName) {
        this.tableName = tableName;
        this.dbName = dbName;
        handler = new JDBCHandler(dbName);
    }

    protected abstract Class <E> getEntityClass();

    protected abstract Class <K> getKeyClass();

    public abstract String[][] getNameMapping();

    public abstract String getInsertQuery();

    public abstract String getInsertByIdQuery();

    public abstract String getUpdateQuery();

    public String getTruncateTable() {
        return String.format(TRUNCATE_TABLE, dbName, tableName);
    }

    public String getSelectAllQuery() {
        return String.format(SELECT_ALL, dbName, tableName);
    }

    public String getSelectByIdQuery() {
        String keyFieldName = getNameMapping()[getNameMapping().length-1][1];
        return String.format(SELECT_BY_ID,dbName,tableName,keyFieldName);
    }

    public String getDeleteByIdQuery() {
        String keyFieldName = getNameMapping()[getNameMapping().length-1][1];
        return String.format(DELETE_BY_ID,dbName,tableName,keyFieldName);
    }

    private void setFieldValue(Field field, Object instance, String value) throws IllegalAccessException {
        String fieldType = field.getType().getSimpleName();
        switch (fieldType) {
            case "String": {
                field.set(instance, value);
                break;
            }
            case "int":
            case "Integer": {
                field.setInt(instance, Integer.parseInt(value));
                break;
            }
            case "Double":
            case "double":
            case "Float":
            case "float": {
                field.setFloat(instance, Float.parseFloat(value));
                break;
            }
            case "boolean":
            case "Boolean": {
                field.setBoolean(instance, Boolean.getBoolean(value));
                break;
            }
        }
    }

    private Object getEntityFromResultSet(ResultSet resultSet) {
        Class <?> entityClass = this.getEntityClass();
        Object instance = null;
        try {
            instance = entityClass.newInstance();
            for (String[] columnFieldPair : (getNameMapping())) {
                String fieldName = columnFieldPair[0];
                String columnName = columnFieldPair[1];

                Field field = entityClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                String value = resultSet.getString(columnName);

                setFieldValue(field, instance, value);
            }
        } catch (NoSuchFieldException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }

        return instance;
    }

    @SuppressWarnings("unchecked")
    public List <E> getAll() {
        List <E> entities = new ArrayList <>();
        PreparedStatement statement = getPrepareStatement(getSelectAllQuery());
        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
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
            statement = prepareStatement(statement, entity);
            result = statement != null && statement.execute();
            handler.closePrepareStatement(statement);
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return result;
    }

    public E getByKey(K key) {
        E entity = null;
        PreparedStatement statement = getPrepareStatement(getSelectByIdQuery());
        int idIndex = getNameMapping().length - 1;
        try {
            statement = prepareStatementWithOneValue(statement, key, idIndex);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.getMetaData().getColumnCount() != 1)
                if (resultSet.next()) {
                    Object parsingSetResult = this.getEntityFromResultSet(resultSet);
                    if (parsingSetResult != null && (getEntityClass()).isInstance(parsingSetResult))
                        entity = ((E) parsingSetResult);
                }
            handler.closePrepareStatement(statement);
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public boolean deleteByKey(K key) {
        boolean result = false;
        PreparedStatement statement = getPrepareStatement(getDeleteByIdQuery());
        int idIndex = getNameMapping().length - 1;
        try {
            statement = prepareStatementWithOneValue(statement, key, idIndex);
            result = statement.execute();
            handler.closePrepareStatement(statement);
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean create(E entity) {
        boolean result = false;
        boolean generateKey;
        try {
            K key = null;
            int keyIndex = getNameMapping().length - 1;
            Field field = getField(keyIndex);
            field.setAccessible(true);
            Object value = field.get(entity);

            if (value != null && getKeyClass().isInstance(value))
                key = (K) value;

            generateKey = (key == null) || key.equals(-1);
            PreparedStatement statement;
            if (generateKey)
                statement = getPrepareStatement(getInsertQuery());
            else statement = getPrepareStatement(getInsertByIdQuery());

            statement = prepareStatement(statement, entity);
            if (statement == null)
                return false;
            result = statement.execute();
            if (generateKey) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        String keyValue = generatedKeys.getString(1);
                        setFieldValue(field, entity, keyValue);
                    } else {
                        throw new SQLException("Creating " + tableName + " failed, no ID obtained.");
                    }
                }
            }
            handler.closePrepareStatement(statement);
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    private PreparedStatement getPrepareStatement(String query) {
        return handler.getPrepareStatement(query);
    }

    private PreparedStatement prepareStatement(PreparedStatement statement, E item) throws SQLException, NoSuchFieldException, IllegalAccessException {
        int parametersCount = statement.getParameterMetaData().getParameterCount();
        for (int i = 0; i < parametersCount; i++) {
            Field field = getField(i);
            field.setAccessible(true);
            Object value = field.get(item);
            statement = prepareStatementWithOneValue(statement, value, i);
        }
        return statement;
    }

    private String getFieldTypeName(int index) throws NoSuchFieldException {
        Field field = getField(index);
        field.setAccessible(true);
        return field.getType().getSimpleName();
    }

    private Field getField(int index) throws NoSuchFieldException {
        Class <?> entityClass = this.getEntityClass();
        String[][] mapping = getNameMapping();

        String fieldName = mapping[index][0];
        return entityClass.getDeclaredField(fieldName);

    }

    private PreparedStatement prepareStatementWithOneValue(PreparedStatement statement, Object value, int fieldIndex) throws SQLException, NoSuchFieldException, IllegalAccessException {
        String fieldType = getFieldTypeName(fieldIndex);
        if (statement.getParameterMetaData().getParameterCount() < fieldIndex)
            fieldIndex = 0;
        switch (fieldType) {
            case "String": {
                statement.setString(fieldIndex + 1, value.toString());
                break;
            }
            case "int":
            case "Integer": {
                statement.setInt(fieldIndex + 1, Integer.parseInt(value.toString()));
                break;
            }
            case "Float":
            case "float":
            case "Double":
            case "double": {
                statement.setDouble(fieldIndex + 1, Double.parseDouble(value.toString()));
                break;
            }
            case "boolean":
            case "Boolean": {
                statement.setBoolean(fieldIndex + 1, Boolean.getBoolean(value.toString()));
                break;
            }
        }
        return statement;
    }


    void truncateTable() {
        Statement statement;
        try {
            statement = handler.getStatement();
            statement.addBatch("SET FOREIGN_KEY_CHECKS = 0");
            statement.addBatch(String.format(getTruncateTable(), dbName, tableName));
            statement.addBatch("SET FOREIGN_KEY_CHECKS = 1");
            statement.executeBatch();
            statement.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
