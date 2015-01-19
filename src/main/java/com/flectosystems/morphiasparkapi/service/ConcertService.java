package com.flectosystems.morphiasparkapi.service;

import com.flectosystems.morphiasparkapi.dao.ConcertDAO;
import spark.servlet.SparkApplication;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The propose of this class is provide an endpoint for the task related to
 * the {@link com.flectosystems.morphiasparkapi.models.Concert} entity
 * <p/>
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
