package edu.kpi.jee.labs;

import edu.kpi.jee.labs.Entities.Category;
import edu.kpi.jee.labs.Entities.Place;

import java.sql.*;

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

    public void insertPlace(String name, String address, float latitude, float longitude) {
        String query = String.format("INSERT INTO `attractions`.`place` (`p_name`, `p_address`, `p_lat`, `p_lng`) VALUES ('%s', '%s', '%-10.6f', '%-10.6f');", name, address, latitude, longitude);
        execute(query);
    }

    public void insertPlace(String name, String address, float latitude, float longitude, float rating) {
        String query = String.format("INSERT INTO `attractions`.`place` (`p_name`, `p_address`, `p_lat`, `p_lng`, `p_rating`) VALUES ('%s', '%s', '%-10.6f', '%-10.6f', '%-3.2f');", name, address, latitude, longitude, rating);
        execute(query);
    }

    public void insertPlace(Place place) {
        String query = String.format("INSERT INTO `attractions`.`place` (`p_name`, `p_address`, `p_lat`, `p_lng`, `p_rating`) VALUES ('%s', '%s', '%-10.6f', '%-10.6f', '%-3.2f');", place.getName(), place.getAddress(), place.getLatitude(), place.getLongitude(), place.getRating());
        execute(query);
    }

    public void insertCategory(String name) {
        String query = String.format("INSERT INTO `attractions`.`category` (`c_name`) VALUES ('%s');", name);
        execute(query);
    }

    public void insertCategory(Category category) {
        String query = String.format("INSERT INTO `attractions`.`category` (`c_name`) VALUES ('%s');", category.getName());
        execute(query);
    }

    public Place getPlaceByName(String name) {
        String query = String.format("SELECT * FROM `attractions`.`place` WHERE `place`.`p_name` = %s", name);
        ResultSet resultSet = executeQuery(query);
        try {
            if (resultSet != null) {
                int id = resultSet.getInt("p_id");
                String address = resultSet.getString("p_address");
                float latitude = resultSet.getFloat("p_lat");
                float longitude = resultSet.getFloat("p_lng");
                float rating = resultSet.getFloat("p_rating");
                return new Place(id, name, address, latitude, longitude, rating);
            } else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void execute(String query) {
        try {
            if (connection != null && !connection.isClosed()) {
                Statement statement = connection.createStatement();
                statement.execute(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet executeQuery(String query) {
        try {
            if (connection != null && !connection.isClosed()) {
                Statement statement = connection.createStatement();
                return statement.executeQuery(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
