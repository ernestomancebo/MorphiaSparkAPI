package com.flectosystems.morphiasparkapi.service;

import com.flectosystems.morphiasparkapi.dao.ConcertDAO;
import com.flectosystems.morphiasparkapi.utils.Constants;
import com.flectosystems.morphiasparkapi.utils.JsonTransformer;
import org.bson.types.ObjectId;
import spark.servlet.SparkApplication;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import static spark.Spark.get;

/**
 * The propose of this class is provide an endpoint for the task related to
 * the {@link com.flectosystems.morphiasparkapi.models.Concert} entity
 * <p>
 * Created by Ernesto Mancebo T on 1/7/15.
 */
@Named
public class ConcertService implements SparkApplication {

    @Inject
    ConcertDAO concertDAO;

    private static ConcertService INSTANCE = null;

    private ConcertService() {
    }

    @Override
    public void init() {
        // Get all concerts
        get(Constants.CONCERT_API_URL.concat("get/"), (req, res) -> {
            return concertDAO.find().asList();
        }, new JsonTransformer());

        // Search concerts by name
        get(Constants.CONCERT_API_URL.concat("getByName/:name"), (req, res) -> {
            return concertDAO.findByName(req.params("name"));
        }, new JsonTransformer());

        // Search concerts by date
        get(Constants.CONCERT_API_URL.concat("getByDate/:date"), (req, res) -> {
            String date = req.params("date");

            if (null == date || date.isEmpty()) {
                res.status(Constants.BAD_REQUEST);
                return new HashMap<String, String>().put("error", "Invalid date: " + date);
            }

            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                return concertDAO.findByDate(
                        format.parse(date)
                );
            } catch (ParseException ex) {
                res.status(Constants.BAD_REQUEST);
                return new HashMap<String, String>().put("error", "Invalid date: " + date);
            }
        }, new JsonTransformer());

        // Search concerts by venue
        get(Constants.CONCERT_API_URL.concat("getByVenue/:venueID"), (req, res) -> {

            String id = req.params("venueID");
            if (null == id || id.isEmpty()) {
                res.status(Constants.BAD_REQUEST);
                return new HashMap<String, String>().put("error", "Invalid Venue");
            }

            ObjectId venueID = new ObjectId(id);

            return concertDAO.findByVenue(venueID);
        }, new JsonTransformer());

        // Search concerts near
        get(Constants.CONCERT_API_URL.concat("getByVenueNear/:location"), (req, res) -> {
            res.status(500);
            return new HashMap<String, String>().put("error", "Not implemented yet");
//            String venue = req.params("venue");
//
//            Venue v = new Venue();
//            return concertDAO.findByVenue(v);
        }, new JsonTransformer());
    }

    public void setConcertDAO(ConcertDAO concertDAO) {
        this.concertDAO = concertDAO;
    }

    public static ConcertService getInstance() {
        if (null == INSTANCE)
            INSTANCE = new ConcertService();
        return INSTANCE;
    }
}
