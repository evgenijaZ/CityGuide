package edu.kpi.jee.labs.dao;

import java.io.IOException;
import java.sql.*;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
public class DBHandler {
    private String pathToDB;
    private final String URL = "jdbc:mysql://localhost:3306/attractions?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASS = "root";

    public Connection openConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            return connection;
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
}
