package com.flectosystems.morphiasparkapi.dao;

import com.flectosystems.morphiasparkapi.config.MongoDB;
import com.flectosystems.morphiasparkapi.models.Venue;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import javax.inject.Named;

/**
 * Class designed to access to the DB for the {@link Venue} entity.
 * <p>
 * Created by Ernesto Mancebo T on 1/6/15.
 */
@Named
public class VenueDAO extends BasicDAO<Venue, ObjectId> {

    public VenueDAO() {
        super(MongoDB.getInstance().getDatastore());
    }
}
