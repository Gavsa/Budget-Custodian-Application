package at.htl.gavric.rest;

import at.htl.gavric.entities.ExpensesCategory;
import at.htl.gavric.facades.ExpensesCategoryDao;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("expensescategory")
public class ExpensesCategoryEndpoint {

    @Inject
    ExpensesCategoryDao expensesCategoryDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getExpensesCategory (){
        return Response.ok(expensesCategoryDao.getAll()).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("id/{id}")
    public Response getById(@PathParam("id") Long id) {
        ExpensesCategory expensesCategory = expensesCategoryDao.getById(id);
        if (expensesCategory!= null)
            return Response.ok(expensesCategory).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(JsonObject expensesCategory) {
        expensesCategoryDao.save(ExpensesCategory.fromJSON(expensesCategory));
        return Response.ok().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(JsonObject expensesCategory) {
        ExpensesCategory h = ExpensesCategory.fromJSON(expensesCategory);
        if (h.getId() == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        expensesCategoryDao.update(h);
        return Response.ok().build();
    }

    @DELETE
    @Path("id/{id}")
    public Response delete(@PathParam("id") Long id) {
        ExpensesCategory h = expensesCategoryDao.getById(id);
        if (h == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        expensesCategoryDao.delete(h);
        return Response.ok().build();
    }
}
