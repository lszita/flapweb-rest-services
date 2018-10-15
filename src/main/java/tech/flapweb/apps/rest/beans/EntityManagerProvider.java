package tech.flapweb.apps.rest.beans;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.glassfish.hk2.api.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityManagerProvider implements Factory<EntityManager>{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerProvider.class);
    
    @Inject
    EntityManagerFactory emf;
    
    @Override
    public EntityManager provide() {
        LOGGER.debug("EM was provided");
        return emf.createEntityManager();
    }

    @Override
    public void dispose(EntityManager t) {
        LOGGER.debug("EM was disposed and closed");
        t.close();
    }
    
}
