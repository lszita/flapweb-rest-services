package tech.flapweb.apps.rest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import tech.flapweb.apps.rest.beans.EntityManagerFactoryProvider;
import tech.flapweb.apps.rest.beans.EntityManagerProvider;

@ApplicationPath("resources")
public class JerseyAppConfig extends ResourceConfig {
    public JerseyAppConfig(){
        register(new JerseyAppBinder());
        packages("tech.flapweb.apps.rest.resources");
    }
}

class JerseyAppBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(EntityManagerProvider.class).to(EntityManager.class).in(RequestScoped.class);
        bindFactory(new EntityManagerFactoryProvider()).to(EntityManagerFactory.class);
    }
}