package tech.flapweb.apps.rest.beans;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.glassfish.hk2.api.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityManagerFactoryProvider implements Factory<EntityManagerFactory> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerFactoryProvider.class);
    private final EntityManagerFactory emf;
    
    public EntityManagerFactoryProvider(){
        LOGGER.debug("EMF was constructed");
        emf = Persistence.createEntityManagerFactory( "flapweb" );
    }
        
    @Override
    public EntityManagerFactory provide() {
        LOGGER.debug("EMF was provided");
        return emf;
    }

    @Override
    public void dispose(EntityManagerFactory t) {
        LOGGER.debug("EMF was disposed and closed");
        t.close();
    }
}
