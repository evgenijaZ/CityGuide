package edu.kpi.jee.labs.dao;

import edu.kpi.jee.labs.Entities.Place;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Yevheniia Zubrych on 01.03.2018.
 */

public class DAOPlaceTest {

    private DAO <Place, Integer> dao;

    private Place church;
    private Place potemkinStairs;
    private Place operaTheatre;
    private Place freedomSquare;

    @Before
    public void initDao() {
        dao = new PlaceDAO("attractions", "place");
        dao.truncateTable();
    }

    @Before
    public void initPlaces() {
        freedomSquare = new Place("Ploshcha Svobody", "Freedom Square, 8, Kharkiv, Kharkiv region, 61000", -50.004444f, -143.766667f);
        operaTheatre = new Place("Odessa Opera and Ballet Theatre", "lane Tchaikovsky, 1, Odessa, Ukraine", -46.485278f, -149.258611f);
        potemkinStairs = new Place("Potemkin Stairs", "Primorsky Boulevard, 9, Odessa, Ukraine", -46.488122f, -149.258856f);
        church = new Place("St Andrew's Church", "Andrew's Descent, 23, Kiev, Ukraine", -50.458361f, -149.481778f);
    }


    @Test
    public void testGettingListOfPlaces() throws SQLException {
        //Given
        dao.create(freedomSquare);
        dao.create(operaTheatre);
        dao.create(potemkinStairs);
        dao.create(church);

        //When
        List <Place> places = dao.getAll();

        //Then
        Assert.assertNotNull(places);
    }

    @Test
    public void testSizeOfGettingListOfPlaces() throws SQLException {
        //Given
        dao.create(freedomSquare);
        dao.create(operaTheatre);
        dao.create(potemkinStairs);
        dao.create(church);

        //When
        List <Place> places = dao.getAll();

        //Then
        Assert.assertEquals(4, places.size());
    }

    @Test
    public void testGeneratingNewIdWhileInserting() throws SQLException {
        //When
        dao.create(freedomSquare);

        //Then
        Assert.assertTrue(-1 != freedomSquare.getId());
    }

    @Test
    public void testInserting() throws SQLException {
        //When
        dao.create(freedomSquare);

        //Then
        Assert.assertEquals(freedomSquare, dao.getByKey(freedomSquare.getId()));
    }

    @Test
    public void testInsertingById() throws SQLException {
        //Given
        int id = 34;
        freedomSquare.setId(id);

        //When
        dao.create(freedomSquare);

        //Then
        Assert.assertEquals(freedomSquare, dao.getByKey(id));
    }


    @Test
    public void testUpdatingName() throws SQLException {
        //Given
        dao.create(potemkinStairs);

        //When
        String updatedName = "updated name";
        potemkinStairs.setName(updatedName);
        dao.update(potemkinStairs);

        //Then
        Assert.assertEquals(updatedName, dao.getByKey(potemkinStairs.getId()).getName());
    }

    @Test
    public void deletingTest() throws SQLException {
        //Given
        dao.create(church);
        int id = church.getId();

        //When
        dao.deleteByKey(id);

        //Then
        Assert.assertEquals(null, dao.getByKey(id));
    }

    @After
    public void cleanTable() {
        dao.truncateTable();
    }
}
