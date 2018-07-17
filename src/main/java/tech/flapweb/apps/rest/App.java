package tech.flapweb.apps.rest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebListener
public class App implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public final void contextInitialized(final ServletContextEvent sce) {
        LOGGER.info("------------- FLAPWEB REST SESRVICE STARTED -------------------");
    }

    @Override
    public final void contextDestroyed(final ServletContextEvent sce) {
        LOGGER.info("context destroyed");
    }
}
