package at.htl.gavric.rest;

import at.htl.gavric.entities.Holiday;
import at.htl.gavric.facades.HolidayDao;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("holiday")
public class HolidayEndpoint {

    @Inject
    HolidayDao holidayDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHoliday (){
        return Response.ok(holidayDao.getAll()).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("id/{id}")
    public Response getById(@PathParam("id") Long id) {
        Holiday holiday = holidayDao.getById(id);
        if (holiday != null)
            return Response.ok(holiday).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(JsonObject holiday) {
        holidayDao.save(Holiday.fromJSON(holiday));
        return Response.ok().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(JsonObject holiday) {
        Holiday h = Holiday.fromJSON(holiday);
        if (h.getId() == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        holidayDao.update(h);
        return Response.ok().build();
    }

    @DELETE
    @Path("id/{id}")
    public Response delete(@PathParam("id") Long id) {
        Holiday h = holidayDao.getById(id);
        if (h == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        holidayDao.delete(h);
        return Response.ok().build();
    }
}
