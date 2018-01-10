package at.htl.gavric.rest;

import at.htl.gavric.entities.Plan;
import at.htl.gavric.facades.PlanDao;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("plan")
public class PlanEndpoint {

    @Inject
    PlanDao planDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPlan (){
        return Response.ok(planDao.getAll()).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("id/{id}")
    public Response getById(@PathParam("id") Long id) {
        Plan plan = planDao.getById(id);
        if (plan != null)
            return Response.ok(plan).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(JsonObject plan) {
        planDao.save(Plan.fromJSON(plan));
        return Response.ok().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(JsonObject plan) {
        Plan h = Plan.fromJSON(plan);
        if (h.getId() == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        planDao.update(h);
        return Response.ok().build();
    }

    @DELETE
    @Path("id/{id}")
    public Response delete(@PathParam("id") Long id) {
        Plan h = planDao.getById(id);
        if (h == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        planDao.delete(h);
        return Response.ok().build();
    }
}
