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
    public List<Student> getAll() throws ResourceException {
        try {
            return studentRepo.getAll();
        } catch (SQLException e) {
            throw new ResourceException("SQL-exception");
        } catch (ClassNotFoundException e) {
            throw new ResourceException("Class not found");
        }
    }

    @GET
    @Path("/{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student get(@PathParam("id") int id ) throws ResourceException {
        try {
            return studentRepo.get(id);
        } catch (SQLException e) {
            throw new ResourceException("SQL-exception");
        } catch (ClassNotFoundException e) {
            throw new ResourceException("Class not found");
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void add(@HeaderParam("name") String name, @HeaderParam("address") String address, @HeaderParam("company") String company) throws ResourceException {
        int a = 0;
        try {
            a = studentRepo.add(name, address, company);
        } catch (SQLException e) {
            throw new ResourceException("SQL-exception");
        } catch (ClassNotFoundException e) {
            throw new ResourceException("Class not found");
        }
        if (a != 0) {
            throw new ResourceException("NotAllowedException - Student kon niet toegevoegd worden.");
        }
    }
}