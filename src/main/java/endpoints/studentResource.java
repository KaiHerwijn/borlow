package endpoints;


import database.Database;
import models.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Gebruiker on 9-1-2017.
 */

@Path("/student")
public class studentResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAll() {
        return Database.getInstance().getStudent().getAll();
    }

    @GET
    @Path("/{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student get(@PathParam("id") int id ) throws NotFoundException{
        try {
            return Database.getInstance().getStudent().get(id);
        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Student met id " + id + " is niet gevonden.");
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(@HeaderParam("name") String name, @HeaderParam("address") String address, @HeaderParam("company") String company) {
        Database.getInstance().getStudent().add(name, address, company);
        return Response.ok().build();
    }
}