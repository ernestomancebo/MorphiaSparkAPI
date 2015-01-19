package com.flectosystems.morphiasparkapi.service;

import com.flectosystems.morphiasparkapi.dao.ArtistDAO;
import spark.servlet.SparkApplication;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The propose of this class is provide an endpoint for the task related to
 * the {@link com.flectosystems.morphiasparkapi.models.Artist} entity
 * <p/>
 * Created by Ernesto Mancebo T on 1/7/15.
 */
@Named
public class ArtistService implements SparkApplication {

    @Inject
    ArtistDAO artistDAO;

    private static ArtistService INSTANCE = null;

    private ArtistService() {
    }

    @Override
    public void init() {
    }

    public void setArtistDAO(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    public static ArtistService getInstance() {
        if (null == INSTANCE)
            INSTANCE = new ArtistService();
        return INSTANCE;
    }
}