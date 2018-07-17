package tech.flapweb.apps.rest.beans;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.glassfish.hk2.api.Factory;

public class EntityManagerFactoryProvider implements Factory<EntityManagerFactory> {
    
    private final EntityManagerFactory emf;
    
    public EntityManagerFactoryProvider(){
        emf = Persistence.createEntityManagerFactory( "flapweb" );
    }
        
    @Override
    public EntityManagerFactory provide() {
        return emf;
    }

    @Override
    public void dispose(EntityManagerFactory t) {
        t.close();
    }
}
