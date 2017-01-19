package endpoints;


import database.Database;
import models.Stuff;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Gebruiker on 9-1-2017.
 */

@Path("/stuff")
public class StuffResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stuff> getAll() {
        return Database.getInstance().getStuff().getAll();
    }

    @GET
    @Path("/{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Stuff get(@PathParam("id") int id ) throws NotFoundException{
        try {
            return Database.getInstance().getStuff().get(id);
        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Stuff met id " + id + " is niet gevonden.");
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(@HeaderParam("name") String name) {
        Database.getInstance().getStuff().add(name);
        return Response.ok().build();
    }
}