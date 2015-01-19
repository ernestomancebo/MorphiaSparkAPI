package com.flectosystems.morphiasparkapi.dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The propose of this class is to add some customs methods and add some functions to the {@link BasicDAO}
 * <p>
 * Created by Ernesto Mancebo T on 1/19/15.
 */
public abstract class ExtendedBasicDAO<T, K> extends BasicDAO<T, K> {

    protected ExtendedBasicDAO(Datastore ds) {
        super(ds);
    }

    /**
     * Find documents in collection by name.
     */
    public List<T> findByName(String name) {
        if (null == name || name.isEmpty())
            return new ArrayList<>();

        Pattern regExp = Pattern.compile(name + ".*", Pattern.CASE_INSENSITIVE);
        return getDatastore()
                .find(getEntityClass())
                .filter("name", regExp)
                .asList();
    }
}
