package edu.kpi.jee.labs;

import edu.kpi.jee.labs.Entities.Category;
import edu.kpi.jee.labs.Entities.Place;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Yevheniia Zubrych on 25.02.2018.
 */
public class AttractionsTable {
    private final String URL = "jdbc:mysql://localhost:3306/attractions?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASS = "root";
    private Connection connection;

    public Connection openConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection closeConnection() {
        try {
            connection.close();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertPlace(String name, String address, float latitude, float longitude) throws SQLException {
        String query = String.format(Locale.US, "INSERT INTO `attractions`.`place` (`p_name`, `p_address`, `p_lat`, `p_lng`, , `p_rating`) VALUES ('%s', '%s', '%-10.6f', '%-10.6f', '0.0');", name, address, latitude, longitude);
        execute(query);
    }

    public void insertPlace(String name, String address, float latitude, float longitude, float rating) throws SQLException {
        String query = String.format(Locale.US, "INSERT INTO `attractions`.`place` (`p_name`, `p_address`, `p_lat`, `p_lng`, `p_rating`) VALUES ('%s', '%s', '%-10.6f', '%-10.6f', '%-3.2f');", name, address, latitude, longitude, rating);
        execute(query);
    }

    public void insertPlace(Place place) throws SQLException {
        String query = String.format(Locale.US, "INSERT INTO `attractions`.`place` (`p_name`, `p_address`, `p_lat`, `p_lng`, `p_rating`) VALUES ('%s', '%s', '%10.6f', '%10.6f', '%-3.1f');", place.getName(), place.getAddress(), place.getLatitude(), place.getLongitude(), place.getRating());
        execute(query);
    }

    public void insertCategory(String name) throws SQLException {
        String query = String.format(Locale.US, "INSERT INTO `attractions`.`category` (`c_name`) VALUES ('%s');", name);
        execute(query);
    }

    public void insertCategory(Category category) throws SQLException {
        String query = String.format(Locale.US, "INSERT INTO `attractions`.`category` (`c_name`) VALUES ('%s');", category.getName());
        execute(query);
    }

    public Place getPlaceByName(String name) throws SQLException {
        String query = String.format(Locale.US, "SELECT * FROM `attractions`.`place` WHERE `place`.`p_name` = '%s'", name);
        ResultSet resultSet = executeQuery(query);
        if (resultSet != null) {
            resultSet.next();
            int id = resultSet.getInt("p_id");
            String address = resultSet.getString("p_address");
            float latitude = resultSet.getFloat("p_lat");
            float longitude = resultSet.getFloat("p_lng");
            float rating = resultSet.getFloat("p_rating");
            return new Place(id, name, address, latitude, longitude, rating);
        } else
            return null;
    }

    public List <Place> getPlaces() throws SQLException {
        String query = String.format(Locale.US, "SELECT * FROM `attractions`.`place`");
        List <Place> places = new ArrayList <Place>();
        ResultSet resultSet = executeQuery(query);
        if (resultSet != null) {
            while (resultSet.next()) {
                int id = resultSet.getInt("p_id");
                String name = resultSet.getString("p_name");
                String address = resultSet.getString("p_address");
                float latitude = resultSet.getFloat("p_lat");
                float longitude = resultSet.getFloat("p_lng");
                float rating = resultSet.getFloat("p_rating");
                places.add(new Place(id, name, address, latitude, longitude, rating));
            }
            return places;
        } else
            return null;
    }

    public void updatePlaceById(int id, Place place) throws SQLException {
        String query = String.format(Locale.US, "UPDATE `attractions`.`place` SET `p_name`='%s',`p_address`='%s',`p_lat`='%10.6f',`p_lng`='%10.6f',`p_rating`='%3.1f' WHERE `p_id`='%d';", place.getName(), place.getAddress(), place.getLatitude(), place.getLongitude(), place.getRating(), id);
        execute(query);
    }

    public void deletePlaceByName(String name) throws SQLException {
        String query = String.format(Locale.US, "DELETE FROM `attractions`.`place` WHERE `p_name`='%s';", name);
        execute(query);
    }

    public void deletePlaceById(int id) throws SQLException {
        String query = String.format(Locale.US, "DELETE FROM `attractions`.`place` WHERE `p_id`='%d';", id);
        execute(query);
    }

    private void execute(String query) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } else throw new IllegalStateException();
    }

    private ResultSet executeQuery(String query) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }
        return null;
    }

}
