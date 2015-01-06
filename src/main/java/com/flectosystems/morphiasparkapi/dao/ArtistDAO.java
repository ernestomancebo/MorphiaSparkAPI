package com.flectosystems.morphiasparkapi.dao;

import com.flectosystems.morphiasparkapi.config.MongoDB;
import com.flectosystems.morphiasparkapi.models.Artist;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by Ernesto Mancebo T on 1/6/15.
 */
public class ArtistDAO extends BasicDAO<Artist, ObjectId> {

    public ArtistDAO() {
        super(MongoDB.getInstance().getDatastore());
    }
}
