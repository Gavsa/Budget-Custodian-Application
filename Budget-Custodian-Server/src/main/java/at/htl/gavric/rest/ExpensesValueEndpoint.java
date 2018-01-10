package at.htl.gavric.rest;

import at.htl.gavric.entities.ExpensesValue;
import at.htl.gavric.facades.ExpensesValueDao;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("expensesvalue")
public class ExpensesValueEndpoint {

    @Inject
    ExpensesValueDao expensesValueDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getExpensesValue (){
        return Response.ok(expensesValueDao.getAll()).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("id/{id}")
    public Response getById(@PathParam("id") Long id) {
        ExpensesValue expensesValue = expensesValueDao.getById(id);
        if (expensesValue != null)
            return Response.ok(expensesValue).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(JsonObject holiday) {
        expensesValueDao.save(ExpensesValue.fromJSON(holiday));
        return Response.ok().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(JsonObject holiday) {
        ExpensesValue h = ExpensesValue.fromJSON(holiday);
        if (h.getId() == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        expensesValueDao.update(h);
        return Response.ok().build();
    }

    @DELETE
    @Path("id/{id}")
    public Response delete(@PathParam("id") Long id) {
        ExpensesValue h = expensesValueDao.getById(id);
        if (h == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        expensesValueDao.delete(h);
        return Response.ok().build();
    }
}
