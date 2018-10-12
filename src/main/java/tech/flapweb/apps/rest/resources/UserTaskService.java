package tech.flapweb.apps.rest.resources;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.flapweb.apps.rest.entities.Task;

@Path("{userId}/task")
public class UserTaskService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserTaskService.class);
    
    @Inject 
    EntityManager em;
       
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> findAll(@Context UriInfo uri) {
        String owner = uri.getPathParameters().getFirst("userId");
        return em.createNamedQuery("Task.findByOwner").setParameter("owner", owner).getResultList();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Task t){
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        return Response.status(201).entity(t).build();
    }
    
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Task complete(@PathParam("id") Long id, Task t){
        Task original =  em.find(Task.class, id);
        em.getTransaction().begin();
        original.setCompleted(t.getCompleted());
        em.getTransaction().commit();
        return original;
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){
        Task t =  em.find(Task.class, id);
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        return Response.ok().build();
    }
    
}
