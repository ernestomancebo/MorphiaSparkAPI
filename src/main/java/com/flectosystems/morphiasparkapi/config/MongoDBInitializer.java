package com.flectosystems.morphiasparkapi.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

/**
 * Since MongoDB instance is a singleton, it can't be instantiated by the servlet container,
 * so an Initializer class is needed.
 * <p/>
 * Has the default access willing to avoid unwanted access.
 * <p/>
 * Created by ernesto on 1/5/15.
 */

public class MongoDBInitializer implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(MongoDBInitializer.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.info("WebApp started");

        ServletContext context = servletContextEvent.getServletContext();
        // Setting up the parameters
        final String DB_NAME = context.getInitParameter("database");
        final String HOST = context.getInitParameter("databaseHost");
        final int PORT = Integer.valueOf(context.getInitParameter("databasePort"));
        final String USER = context.getInitParameter("user");
        final String PASSWORD = context.getInitParameter("password");

        // pass parameters to the MongoDB instance
        MongoDB.setValues(DB_NAME, HOST, PORT, USER, PASSWORD);
        MongoDB.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        MongoDB.closeMongoDB();

        LOG.info("WebApp destroyed");
    }
}
