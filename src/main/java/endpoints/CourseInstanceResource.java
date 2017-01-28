package endpoints;

import models.CourseInstance;
import repositories.CourseInstanceRepo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Gebruiker on 24-1-2017.
 */
@Path("/courseInstance")
public class CourseInstanceResource {

    private CourseInstanceRepo courseinstanceRepo = new CourseInstanceRepo();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CourseInstance> getAll() throws ResourceException {
        try {
            return courseinstanceRepo.getAll();
        } catch (SQLException e) {
            throw new ResourceException("SQL-exception", e);
        } catch (ClassNotFoundException e) {
            throw new ResourceException("Class not found", e);
        }
    }
}
