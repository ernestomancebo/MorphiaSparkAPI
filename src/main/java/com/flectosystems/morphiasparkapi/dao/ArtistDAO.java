package com.flectosystems.morphiasparkapi.dao;

import com.flectosystems.morphiasparkapi.config.MongoDB;
import com.flectosystems.morphiasparkapi.models.Artist;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import javax.inject.Named;

/**
 * Class designed to access to the DB for the {@link Artist} entity.
 * <p>
 * Created by Ernesto Mancebo T on 1/6/15.
 */
@Named
public class ArtistDAO extends ExtendedBasicDAO<Artist, ObjectId> {

    public ArtistDAO() {
        super(MongoDB.getInstance().getDatastore());
    }
}
