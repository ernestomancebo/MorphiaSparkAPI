package com.flectosystems.morphiasparkapi.config;

import com.flectosystems.morphiasparkapi.models.BaseEntity;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 * Class that provides access to a MongoDB instance
 * <p/>
 * Created by ernesto on 1/5/15.
 */
public class MongoDB {

    private static final Logger LOG = Logger.getLogger(MongoDB.class.getName());
    private static MongoDB INSTANCE;

    private static MongoClient mongo;
    private static Morphia morphia;
    private static Datastore dataStore;

    private MongoDB() {
        getMongo();
    }

    public static MongoClient getMongo() {
        if (mongo == null) {
            LOG.info("Initializing connection to '" + DB_NAME + "'..");

            try {
                // Builds something like: "mongodb://user:password@localhost:27017/mongoDatabase"
                StringBuilder uriBuilder = new StringBuilder();

                uriBuilder.append("mongodb://");
                uriBuilder.append(USER);
                uriBuilder.append(":" + PASSWORD);
                uriBuilder.append("@" + HOST);
                uriBuilder.append(":" + PORT);
                uriBuilder.append("/" + DB_NAME);

                mongo = new MongoClient(
                        new MongoClientURI(uriBuilder.toString())
                );
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            LOG.info("Connection to '" + DB_NAME + "' initialized");
        }
        return mongo;
    }

    // DB configuration
    private static String USER;
    private static String PASSWORD;
    private static String HOST;
    private static String DB_NAME;
    private static int PORT;

    public static void setValues(
            final String dbName,
            final String host,
            final int port,
            final String user,
            final String password
    ) {
        DB_NAME = dbName;
        HOST = host;
        PORT = port;
        USER = user;
        PASSWORD = password;
    }

    public static MongoDB getInstance() {
        if (null == INSTANCE)
            INSTANCE = new MongoDB();
        return INSTANCE;
    }

    public static Morphia getMorphia() {
        if (morphia == null) {
            morphia = new Morphia();
            morphia.mapPackage(BaseEntity.class.getPackage().getName());
        }
        return morphia;
    }

    public static Datastore getDatastore() {
        if (dataStore == null) {
            dataStore = getMorphia().createDatastore(MongoDB.getMongo(), MongoDB.getMongoDB().getName());
            dataStore.ensureIndexes();
        }
        return dataStore;
    }

    public static DB getMongoDB() {
        return getMongo().getDB(DB_NAME);
    }

    public static void closeMongoDB() {
        if (mongo != null)
            try {
                LOG.info("Closing connection to '" + DB_NAME + "'..");
                mongo.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        LOG.info("Connection to '" + DB_NAME + "' closed");
    }
}