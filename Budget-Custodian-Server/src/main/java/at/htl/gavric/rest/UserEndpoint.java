package at.htl.gavric.rest;

import at.htl.gavric.entities.User;
import at.htl.gavric.facades.UserDao;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserEndpoint {

    @Inject
    UserDao userDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser (){
        return Response.ok(userDao.getAll()).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("id/{id}")
    public Response getById(@PathParam("id") Long id) {
        User user = userDao.getById(id);
        if (user != null)
            return Response.ok(user).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(JsonObject user) {
        userDao.save(User.fromJSON(user));
        return Response.ok().build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(JsonObject user) {
        User h = User.fromJSON(user);
        if (h.getId() == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        userDao.update(h);
        return Response.ok().build();
    }

    @DELETE
    @Path("id/{id}")
    public Response delete(@PathParam("id") Long id) {
        User h = userDao.getById(id);
        if (h == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        userDao.delete(h);
        return Response.ok().build();
    }
}
