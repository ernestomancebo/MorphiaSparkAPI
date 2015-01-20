package com.flectosystems.morphiasparkapi.dao;

import com.flectosystems.morphiasparkapi.config.MongoDB;
import com.flectosystems.morphiasparkapi.models.Concert;
import com.flectosystems.morphiasparkapi.models.Venue;
import org.bson.types.ObjectId;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class designed to access to the DB for the {@link Concert} entity.
 * <p>
 * Created by Ernesto Mancebo T on 1/6/15.
 */
@Named
public class ConcertDAO extends ExtendedBasicDAO<Concert, ObjectId> {

    public ConcertDAO() {
        super(MongoDB.getDatastore());
    }

    /**
     * Find documents in collection by date.
     */
    public List<Concert> findByDate(Date date) {
        if (null == date)
            return new ArrayList<>();

        return getDatastore()
                .find(getEntityClass())
                .filter("concertDate", date)
                .asList();
    }

    /**
     * Find documents in collection by venue.
     */
    public List<Concert> findByVenue(ObjectId venueID) {
        if (null == venueID)
            return new ArrayList<>();

        return getDatastore()
                .find(getEntityClass())
                .filter("venue.id", venueID)
                .asList();
    }
}
