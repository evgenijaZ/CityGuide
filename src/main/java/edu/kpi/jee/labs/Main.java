package edu.kpi.jee.labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Yevheniia Zubrych on 25.02.2018.
 */
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/attractions?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static void main(String[] args) {
        Connection connection;
        try {
           connection = DriverManager.getConnection(URL, USER, PASS);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
