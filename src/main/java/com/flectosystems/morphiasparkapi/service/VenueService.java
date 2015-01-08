package com.flectosystems.morphiasparkapi.service;

import com.flectosystems.morphiasparkapi.dao.VenueDAO;
import spark.servlet.SparkApplication;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The propose of this class is provide an endpoint for the task related to
 * the {@link com.flectosystems.morphiasparkapi.models.Venue} entity
 * <p/>
 * Created by Ernesto Mancebo T on 1/7/15.
 */
@Named
public class VenueService implements SparkApplication {

    @Inject
    VenueDAO venueDAO;

    private VenueService instance = null;

    public VenueService() {
    }

    public void setVenueDAO(VenueDAO venueDAO) {
        this.venueDAO = venueDAO;
    }

    @Override
    public void init() {

    }

    /**
     * This class, as a {@link SparkApplication} implementation
     *
     * @return
     */
    public VenueService getInstance() {
        if (null == instance)
            instance = this;

        return instance;
    }
}
