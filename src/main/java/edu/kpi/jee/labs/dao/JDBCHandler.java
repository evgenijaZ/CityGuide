package edu.kpi.jee.labs.dao;

import java.sql.*;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
class JDBCHandler {

    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String parameters = "?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASS = "root";
    private String dbName;


    JDBCHandler(String dbName) {
        this.dbName = dbName;
    }

    private Connection openConnection() {
        try {
            return DriverManager.getConnection(URL + dbName + parameters, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = openConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }


    void closePrepareStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
