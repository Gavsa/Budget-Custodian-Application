package at.htl.gavric.rest;

import at.htl.gavric.facades.ExpensesValueDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("expensesvalue")
public class ExpensesValueEndpoint {

    @Inject
    ExpensesValueDao expensesValueDao;

    @GET
    public Response getHoliday (){
        return Response.ok(expensesValueDao).build();
    }
}
