package tech.flapweb.apps.rest.extensions;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestValidator implements ExceptionMapper<ConstraintViolationException> {
 
    @Override
    public Response toResponse(final ConstraintViolationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(prepareMessage(exception))
                       .type("application/json")
                       .build();
    }
 
    private JsonObject prepareMessage(ConstraintViolationException exception) {
        
        JsonObjectBuilder responseObjectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder errors = Json.createArrayBuilder();
        exception.getConstraintViolations().forEach(v -> errors.add(v.getMessage()));
        responseObjectBuilder
                .add("status", "error")
                .add("errors", errors.build());
        
        return responseObjectBuilder.build();
    }
}
