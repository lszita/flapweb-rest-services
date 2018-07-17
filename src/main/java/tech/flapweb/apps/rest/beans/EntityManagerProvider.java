package tech.flapweb.apps.rest.beans;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.glassfish.hk2.api.Factory;

public class EntityManagerProvider implements Factory<EntityManager>{

    @Inject
    EntityManagerFactory emf;
    
    @Override
    public EntityManager provide() {
        return emf.createEntityManager();
    }

    @Override
    public void dispose(EntityManager t) {
        t.close();
    }
    
}
