package tech.flapweb.apps.rest.extensions;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class JWTFilter implements ContainerRequestFilter{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTFilter.class);
    
    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        LOGGER.info("filter triggered");
    }
    
}
