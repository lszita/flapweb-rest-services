package tech.flapweb.apps.rest.extensions;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.IOException;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.flapweb.apps.rest.beans.JWTVerifierProvider;

@Provider
public class JWTFilter implements ContainerRequestFilter{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTFilter.class);
    
    @Inject
    JWTVerifierProvider vp;
    
    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        String authHeader = ctx.getHeaderString("Authorization");
        String token = "";
        if(authHeader != null){ token = authHeader.replace("Bearer ",""); }
        
        try {
            DecodedJWT jwt = vp.getVerifier().verify(token);
            LOGGER.info("Token sub: {}", jwt.getSubject());
            LOGGER.info("USER: {}", ctx.getUriInfo().getPathParameters().getFirst("userId"));
            LOGGER.info("PATH: {}", ctx.getUriInfo().getPath());
        } catch (JWTVerificationException ex){
            LOGGER.error("Invalid token",ex);
            ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
    
}
