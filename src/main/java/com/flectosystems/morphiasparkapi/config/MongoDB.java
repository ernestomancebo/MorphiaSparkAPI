package com.flectosystems.morphiasparkapi.config;

import org.mongodb.morphia.Datastore;

/**
 * Class that provides access to a MongoDB instance
 * <p/>
 * Created by ernesto on 1/5/15.
 */
public class MongoDB {

    private static final MongoDB INSTANCE = new MongoDB();

    private final Datastore dataStore;

    // DB configuration
    private static String DB_NAME;
    private static String HOST;
    private static long PORT;
    private static long SOCKET_T_O;
    private static long CONNECTION_T_O;

    private MongoDB() {

    }

    static void setValues(
            final String dbName,
            final String host,
            final long port,
            final long socketTO,
            final long connectionTO
    ) {
        DB_NAME = dbName;
        HOST = host;
        PORT = port;
        SOCKET_T_O = socketTO;
        CONNECTION_T_O = connectionTO;
    }

    public static MongoDB getInstance() {
        return INSTANCE;
    }

    public Datastore getDatastore() {
        return dataStore;
    }
}
