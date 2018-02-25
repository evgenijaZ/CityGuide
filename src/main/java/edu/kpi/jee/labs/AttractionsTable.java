package edu.kpi.jee.labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Yevheniia Zubrych on 25.02.2018.
 */
public class AttractionsTable {
    private final String URL = "jdbc:mysql://localhost:3306/attractions?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASS = "root";
    private Connection connection;

    public void openConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
