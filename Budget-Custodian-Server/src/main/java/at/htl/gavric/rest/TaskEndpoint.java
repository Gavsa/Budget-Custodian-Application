package at.htl.gavric.rest;

import at.htl.gavric.facades.TaskDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("task")
public class TaskEndpoint {

    @Inject
    TaskDao taskDao;

    @GET
    public Response getHoliday (){
        return Response.ok(taskDao).build();
    }
}
