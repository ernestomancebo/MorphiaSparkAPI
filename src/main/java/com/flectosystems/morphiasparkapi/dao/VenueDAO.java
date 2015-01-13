package com.flectosystems.morphiasparkapi.dao;

import com.flectosystems.morphiasparkapi.config.MongoDB;
import com.flectosystems.morphiasparkapi.models.Venue;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    public List<Venue> getVenueByName(String name) {
        if (null == name || name.isEmpty())
            return new ArrayList<>();

        Pattern regExp = Pattern.compile(name + ".*", Pattern.CASE_INSENSITIVE);
        return getDatastore()
                .find(getEntityClass())
                .filter("name", regExp)
                .asList();
    }

    public List<Venue> getVenueNear(double[] location) {
        return getDatastore()
                .find(getEntityClass())
                .field("loc").near(location[0], location[1])
                .asList();
    }

    public void deleteAllVenues() {
        getDatastore().getCollection(Venue.class).remove(new BasicDBObject());
    }
}
