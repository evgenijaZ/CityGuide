//package edu.kpi.jee.labs;
//
//import edu.kpi.jee.labs.Entities.Place;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.sql.SQLException;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * @author Yevheniia Zubrych on 01.03.2018.
// */
//
//public class AttractionsTableTest {
//    private static AttractionsTable attractionsTable;
//
//    @BeforeClass
//    public static void initTable() {
//        attractionsTable = new AttractionsTable();
//    }
//
//    @Before
//    public void openConnection() {
//        attractionsTable.openConnection();
//    }
//
//    @Test
//    public void placeRowShouldBeInsertedTest() throws SQLException {
//        Place place = new Place("Plóshcha Svobodý", "Freedom Square, 8, Kharkiv, Kharkiv region, 61000", -50.004444, -143.766667);
//        attractionsTable.insertPlace(place);
//    }
//
//    @Test
//    public void insertionTest() throws SQLException {
//        Place expectedPlace = new Place("Potemkin Stairs", "Primorsky Boulevard, 9, Odessa, Ukraine", -46.488122, -149.258856);
//        attractionsTable.insertPlace(expectedPlace);
//        Place receivedPlace = attractionsTable.getPlaceByName(expectedPlace.getName());
//        assertEquals(expectedPlace, receivedPlace);
//    }
//
//    @Test
//    public void updatingTest() throws SQLException {
//        Place place = new Place(76, "Potemkin Stairs", "Primorsky Boulevard, 9, Odessa, Ukraine", -46.488122, -149.258856);
//        attractionsTable.insertPlace(place);
//        place.setRating(5.0);
//        attractionsTable.updatePlaceById(place.getId(), place);
//        assertEquals(attractionsTable.getPlaceById(place.getId()).getRating(), 5.0, 0.01);
//        attractionsTable.deletePlaceById(76);
//    }
//
//    @Test
//    public void deletingTest() throws SQLException {
//        Place place = new Place("Potemkin Stairs", "Primorsky Boulevard, 9, Odessa, Ukraine", -46.488122, -149.258856);
//        attractionsTable.insertPlace(place);
//        attractionsTable.deletePlaceByName(place.getName());
//        assertEquals(attractionsTable.getPlaceByName(place.getName()), null);
//    }
//
//    @After
//    public void closeConnection() {
//        attractionsTable.closeConnection();
//    }
//}