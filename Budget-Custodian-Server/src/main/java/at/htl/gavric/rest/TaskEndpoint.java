package at.htl.gavric.rest;

import at.htl.gavric.facades.TaskDao;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import at.htl.gavric.entities.Task;


@Path("task")
public class TaskEndpoint {

    @Inject
    TaskDao taskDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getTask (){
        return Response.ok(taskDao.getAll()).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("id/{id}")
    public Response getById(@PathParam("id") Long id) {
        Task task = taskDao.getById(id);
        if (task != null)
            return Response.ok(task).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(JsonObject task) {
        taskDao.save(Task.fromJSON(task));
        return Response.ok().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(JsonObject task) {
        Task h = Task.fromJSON(task);
        if (h.getId() == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        taskDao.update(h);
        return Response.ok().build();
    }

    @DELETE
    @Path("id/{id}")
    public Response delete(@PathParam("id") Long id) {
        Task h = taskDao.getById(id);
        if (h == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        taskDao.delete(h);
        return Response.ok().build();
    }
}
