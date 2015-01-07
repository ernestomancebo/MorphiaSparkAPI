package com.flectosystems.morphiasparkapi.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Has the default access willing to avoid unwanted access.
 * <p>
 * Created by Ernesto Mancebo T on 1/7/15.
 */
class DependencyInjectionInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // Such as a Main method
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
