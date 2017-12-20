package at.htl.gavric.rest;

import at.htl.gavric.facades.PlanDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("plan")
public class PlanEndpoint {

    @Inject
    PlanDao planDao;

    @GET
    public Response getHoliday (){
        return Response.ok(planDao).build();
    }
}
