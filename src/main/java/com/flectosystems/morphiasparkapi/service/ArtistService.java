package com.flectosystems.morphiasparkapi.service;

import com.flectosystems.morphiasparkapi.dao.ArtistDAO;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The propose of this class is provide an endpoint for the task related to
 * the {@link com.flectosystems.morphiasparkapi.models.Artist} entity
 * <p>
 * Created by Ernesto Mancebo T on 1/7/15.
 */
@Named
public class ArtistService {

    @Inject
    ArtistDAO artistDAO;
}
