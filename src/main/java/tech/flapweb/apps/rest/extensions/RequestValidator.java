package tech.flapweb.apps.rest.extensions;

import javax.validation.ConstraintViolation;
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
 
    private String prepareMessage(ConstraintViolationException exception) {
        String msg = "{\"Bad Request\":\"";
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            msg+=  cv.getMessage() + ", ";
        }
        return msg.substring(0, msg.length() - 2) + "\"}";
    }
}
