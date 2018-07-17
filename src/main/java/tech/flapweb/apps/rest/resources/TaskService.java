package tech.flapweb.apps.rest.resources;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tech.flapweb.apps.rest.entities.Task;

@Path("task")
public class TaskService {
    
    @Inject 
    EntityManager em;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Task> tasks = em.createNamedQuery("Task.findAll").getResultList();
        return Response.ok().entity(tasks).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid TaskService t){
        //persist entity...
        return Response.ok().entity(t).build();
    }
    
}
