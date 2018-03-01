package edu.kpi.jee.labs;

import edu.kpi.jee.labs.Entities.Place;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @author Yevheniia Zubrych on 01.03.2018.
 */

public class AttractionsTableTest {
    private static AttractionsTable attractionsTable;

    @BeforeClass
    public static void initTable(){
        attractionsTable = new AttractionsTable();
    }

    @Before
    public void openConnection(){
        attractionsTable.openConnection();
    }

    @Test
    public void placeRowShouldBeInsertedTest() throws SQLException {
        Place place = new Place("Plóshcha Svobodý", "Freedom Square, 8, Kharkiv, Kharkiv region, 61000", -50.004444, -143.766667);
        attractionsTable.insertPlace(place);
    }

    @After
    public void closeConnection(){
        attractionsTable.closeConnection();
    }
}
