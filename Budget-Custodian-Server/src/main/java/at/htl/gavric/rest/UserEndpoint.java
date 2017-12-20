package at.htl.gavric.rest;

import at.htl.gavric.facades.UserDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("user")
public class UserEndpoint {

    @Inject
    UserDao userDao;

    @GET
    public Response getHoliday (){
        return Response.ok(userDao).build();
    }
}
