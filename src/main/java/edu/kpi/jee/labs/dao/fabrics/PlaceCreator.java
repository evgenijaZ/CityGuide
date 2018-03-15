package edu.kpi.jee.labs.dao.fabrics;

import edu.kpi.jee.labs.Entities.Place;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Yevheniia Zubrych on 15.03.2018.
 */
public class PlaceCreator implements Creator <Place> {

    @Override
    public Place create(ResultSet resultSet) {
        Place place = new Place();
        try {
            place.setId(resultSet.getInt("id"));
            place.setName(resultSet.getString("name"));
            place.setAddress(resultSet.getString("address"));
            place.setLatitude(resultSet.getFloat("lat"));
            place.setLongitude(resultSet.getFloat("lng"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return place;
    }
}
