package endpoints;


import database.Database;
import models.Student;
import repositories.StudentRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Gebruiker on 9-1-2017.
 */

@Path("/student")
public class StudentResource {

    private StudentRepo studentRepo = new StudentRepo();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        return studentRepo.getAll();
    }

    @GET
    @Path("/{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student get(@PathParam("id") int id ) throws NotFoundException, SQLException, ClassNotFoundException {
        return studentRepo.get(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(@HeaderParam("name") String name, @HeaderParam("address") String address, @HeaderParam("company") String company) throws SQLException, ClassNotFoundException {
        int a = studentRepo.add(name, address, company);
        if (a == 1) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Student kon niet toegevoegd worden.").build();
        }
    }
}