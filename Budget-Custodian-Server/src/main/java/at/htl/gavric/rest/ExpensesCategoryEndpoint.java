package at.htl.gavric.rest;

import at.htl.gavric.entities.ExpensesCategory;
import at.htl.gavric.facades.ExpensesCategoryDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("expensescategory")
public class ExpensesCategoryEndpoint {

    @Inject
    ExpensesCategoryDao expensesCategoryDao;

    @GET
    public Response getHoliday (){
        return Response.ok(expensesCategoryDao).build();
    }
}
