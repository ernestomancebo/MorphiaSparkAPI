package com.flectosystems.morphiasparkapi.test;

import com.flectosystems.morphiasparkapi.config.MongoDB;
import com.flectosystems.morphiasparkapi.dao.ConcertDAO;
import com.flectosystems.morphiasparkapi.dao.VenueDAO;
import com.flectosystems.morphiasparkapi.models.Concert;
import com.flectosystems.morphiasparkapi.models.Venue;
import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PersistenceTest {

    private static VenueDAO venueDAO;
    private static ConcertDAO concertDAO;

    /**
     * Get the connection to the DB and ensure it's not null.
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        MongoDB.setValues(
                "concert_api",
                "127.0.0.1",
                27017,
                "mongo",
                "mongodb"
        );
        MongoDB.getInstance();
        MongoDB.getMorphia();

        venueDAO = new VenueDAO();
        concertDAO = new ConcertDAO();
        assertNotNull(MongoDB.getMongo());
    }

    /**
     * After finish, clean up the DB and add some data.
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDown() throws Exception {
        venueDAO.deleteAllVenues();

        Venue v = new Venue("Estado Olimpico, Santo Domingo", new double[]{18.4775607, -69.9151118});
        venueDAO.save(v);
        v = new Venue("Bellas Artes, Santo Domingo", new double[]{18.4662652, -69.91582});
        venueDAO.save(v);

        Concert c = new Concert("Concert 1", new Date());
        c.setVenue(v);

        concertDAO.save(c);

        venueDAO = null;
        MongoDB.closeMongoDB();
    }

    /**
     * Ensure the venue is saved and the ObjectId is generated
     */
    @Test
    public void persistVenue() {
        Venue v = new Venue("Roma", new double[]{15.22, -45.36});

        ObjectId id = (ObjectId) venueDAO.save(v).getId();
        assertNotNull("An ObjectId must be generated when persisting", id);
        assertEquals("The returned value must match", id, v.getId());
    }

    @Test
    public void findVenueByName() {
        assertTrue("Null param shouldn't find anything", venueDAO.findByName(null).isEmpty());
        assertTrue("Empty parameter shouldn't find anything", venueDAO.findByName("").isEmpty());
        assertTrue("Wrong name shouldn't find anything", venueDAO.findByName("soda").isEmpty());
        assertEquals("A similar name should find a venue", "Roma", venueDAO.findByName("oMa").get(0).getName());
    }

    /**
     * Search for something near me.
     */
    @Test
    public void findVenueNear() {
        assertNotNull("Should find something near me",
                venueDAO.getVenueNear(
                        new double[]{15.0D, -44.0D}
                ).get(0));
    }

    @Test
    public void persistConcert() {
        Venue v = venueDAO.findByName("Roma").get(0);

        Concert c = new Concert("Concert A", new Date());
        c.setVenue(v);

        ObjectId id = (ObjectId) concertDAO.save(c).getId();

        assertNotNull("An ObjectId must be generated when persisting", id);
        assertEquals("The returned value must match", id, c.getId());
    }

    @Test
    public void findConcertByName() {
        assertTrue("Null param shouldn't find anything", concertDAO.findByName(null).isEmpty());
        assertTrue("Empty parameter shouldn't find anything", concertDAO.findByName("").isEmpty());
        assertTrue("Wrong name shouldn't find anything", concertDAO.findByName("house").isEmpty());
        assertEquals("A similar name should find a venue", "Concert A", concertDAO.findByName("ERT a").get(0).getName());
    }
}