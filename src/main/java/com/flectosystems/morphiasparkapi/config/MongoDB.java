package com.flectosystems.morphiasparkapi.config;

import com.flectosystems.morphiasparkapi.models.BaseEntity;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
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
    private static final MongoDB INSTANCE = new MongoDB();

    private final Datastore dataStore;

    // DB configuration
    private static String DB_NAME;
    private static String HOST;
    private static int PORT;
    private static int SOCKET_T_O;
    private static int CONNECTION_T_O;

    private MongoDB() {
        MongoClientOptions.Builder mongoOptionsBuilder = new MongoClientOptions.Builder();
        MongoClient mongoClient;

        try {
            mongoClient = new MongoClient(
                    new ServerAddress(HOST, PORT),
                    mongoOptionsBuilder
                            .socketTimeout(SOCKET_T_O)
                            .connectTimeout(CONNECTION_T_O)
                            .build()
            );

        } catch (UnknownHostException e) {
            throw new RuntimeException("Error initializing MongoDB", e);
        }

        mongoClient.setWriteConcern(WriteConcern.SAFE);

        dataStore = new Morphia()
                .mapPackage(BaseEntity.class.getPackage().getName())
                .createDatastore(mongoClient, DB_NAME);
        dataStore.ensureIndexes();

        LOG.info("connection to '" + DB_NAME + "' initialized");
    }

    static void setValues(
            final String dbName,
            final String host,
            final int port,
            final int socketTO,
            final int connectionTO
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
