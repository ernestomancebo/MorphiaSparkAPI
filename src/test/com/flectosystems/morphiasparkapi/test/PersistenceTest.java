package com.flectosystems.morphiasparkapi.test;

import com.flectosystems.morphiasparkapi.config.MongoDB;
import com.flectosystems.morphiasparkapi.dao.VenueDAO;
import com.flectosystems.morphiasparkapi.models.Venue;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersistenceTest {

    private VenueDAO venueDAO;

    /**
     * Get the connection to the DB and ensure it's not null.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        MongoDB.setValues("");
        MongoDB.getInstance();

        venueDAO = new VenueDAO();
        assertNotNull(MongoDB.getMongo());
    }

    /**
     * After finish, clean up the DB.
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        venueDAO.delete(Venue.class);
    }

    /**
     * Ensure the venue is saved and the ObjectId is generated
     */
    @Test
    public void persistVenue() {
        Venue v = new Venue("Roma", new double[]{15.22, -45.36});

        ObjectId id = venueDAO.save(v);
        assertNotNull("An ObjectId must be generated when persisting", id);
        assertEquals("The returned value must match", id, v.getId());
    }

    @Test
    public void findVenueByName() {
        assertNull("Null param shouldn't find anything", venueDAO.getVenueByName(null));
        assertnull("Empty parameter shouldn't find anything", venueDAO.getVenueByName(""));
        assertNull("Wrong name shoudn't find anything", venueDAO.getVenueByName("soda"));
        assertEquals("A simmilar name should find a venue", "Roma", venueDAO.getVenueByName("oMa").get(0));
    }

    /**
     * Search for something near me.
     */
    @Test
    public void findVenueNear() {
        assertNotNull("Should find something near me", venueDAO.getVenueNear([15.0, -44]));
    }
}