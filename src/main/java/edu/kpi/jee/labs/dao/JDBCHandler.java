package edu.kpi.jee.labs.dao;

import java.sql.*;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
public class JDBCHandler {

    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String parameters = "?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASS = "root";
    private String dbName;


    public JDBCHandler(String dbName) {
        this.dbName = dbName;
    }

    public Connection openConnection() {
        try {
            return DriverManager.getConnection(URL + dbName + parameters, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection connection) {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public boolean execute(String query) {
        try (Connection connection = openConnection()) {
            if (connection != null) {
                Statement statement = connection.createStatement();
                return statement.execute(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet executeQuery(String query) {
        try {
            Connection connection = openConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                return statement.executeQuery(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = openConnection();
            preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }


    public void closePrepareStatement(PreparedStatement preparedStatement) {
        System.out.println(preparedStatement);
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
