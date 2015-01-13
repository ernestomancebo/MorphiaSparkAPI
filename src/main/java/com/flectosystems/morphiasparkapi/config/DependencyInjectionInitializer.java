package com.flectosystems.morphiasparkapi.config;

import com.flectosystems.morphiasparkapi.dao.VenueDAO;
import com.flectosystems.morphiasparkapi.service.VenueService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Has the default access willing to avoid unwanted access.
 * <p>
 * Created by Ernesto Mancebo T on 1/7/15.
 */
public class DependencyInjectionInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // Such as a Main method
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "Spring-AutoScan.xml"
        });

        VenueService.getInstance().setVenueDAO((VenueDAO) context.getBean("VenueDAO"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
