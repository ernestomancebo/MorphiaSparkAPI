package com.flectosystems.morphiasparkapi.service;

import com.flectosystems.morphiasparkapi.dao.VenueDAO;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The propose of this class is provide an endpoint for the task related to
 * the {@link com.flectosystems.morphiasparkapi.models.Venue} entity
 * <p>
 * Created by Ernesto Mancebo T on 1/7/15.
 */
@Named
public class VenueService {

    @Inject
    VenueDAO venueDAO;
}
