package com.flectosystems.morphiasparkapi.service;

import com.flectosystems.morphiasparkapi.dao.VenueDAO;
import com.flectosystems.morphiasparkapi.utils.Constants;
import com.flectosystems.morphiasparkapi.utils.JsonTransformer;
import spark.servlet.SparkApplication;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.HashMap;

import static spark.Spark.get;

/**
 * The propose of this class is provide an endpoint for the task related to
 * the {@link com.flectosystems.morphiasparkapi.models.Venue} entity
 * <p>
 * Created by Ernesto Mancebo T on 1/7/15.
 */
@Named
public class VenueService implements SparkApplication {

    @Inject
    VenueDAO venueDAO;

    private static VenueService INSTANCE = null;

    public VenueService() {
    }

    public void setVenueDAO(VenueDAO venueDAO) {
        this.venueDAO = venueDAO;
    }

    @Override
    public void init() {

        // Get all venues
        get("/api/venue/get/", Constants.JSON_MIME, (req, res) -> {
            return venueDAO.find().asList();
        }, new JsonTransformer());

        // Get Venues by name
        get(Constants.VENUE_API_URL.concat("getByName/:name"), Constants.JSON_MIME, (req, res) -> {
            return venueDAO.getVenueByName(req.params("name"));
        }, new JsonTransformer());

        // Get Venues near location
        get(Constants.VENUE_API_URL.concat("near/:location"), Constants.JSON_MIME, (req, res) -> {
            // Uses functional programming provided by Stream API /15.5521,-42.1
            try {
                double[] location = Arrays.stream(req.params("location").split(","))
                        .mapToDouble(position -> Double.parseDouble(position))
                        .toArray();
                return venueDAO.getVenueNear(location);
            } catch (NumberFormatException e) {
                res.status(Constants.BAD_REQUEST);
                return new HashMap<String, String>().put("error", "Invalid location: " + req.params("location"));
            }
        }, new JsonTransformer());
    }

    /**
     * This class, as a {@link SparkApplication} implementation
     *
     * @return
     */
    public static VenueService getInstance() {
        if (null == INSTANCE)
            INSTANCE = new VenueService();

        return INSTANCE;
    }
}
