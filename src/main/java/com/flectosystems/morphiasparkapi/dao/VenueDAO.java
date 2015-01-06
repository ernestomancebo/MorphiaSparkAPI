package com.flectosystems.morphiasparkapi.dao;

import com.flectosystems.morphiasparkapi.config.MongoDB;
import com.flectosystems.morphiasparkapi.models.Venue;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by Ernesto Mancebo T on 1/6/15.
 */
public class VenueDAO extends BasicDAO<Venue, ObjectId> {

    public VenueDAO() {
        super(MongoDB.getInstance().getDatastore());
    }
}
