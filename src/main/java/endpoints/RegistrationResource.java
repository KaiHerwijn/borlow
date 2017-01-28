package endpoints;

import repositories.RegistrationRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Gebruiker on 25-1-2017.
 */
@Path("/registration")
public class RegistrationResource {

    private RegistrationRepo registrationRepo = new RegistrationRepo();

    final static Logger logger = Logger.getLogger(RegistrationRepo.class.toString());

    @POST
    public void add(@HeaderParam("code") String code, @HeaderParam("start") String start, @HeaderParam("student") int student) throws ResourceException {
        try {
            registrationRepo.add(code, Date.valueOf(start), student);
        } catch (SQLException e) {
            throw new ResourceException("SQL-exception", e);
        } catch (ClassNotFoundException e) {
            throw new ResourceException("Class not found", e);
        } catch (RegistrationException e) {
            logger.info(e.getMessage());
            throw new ResourceException("De registratie kon niet voltooid worden.", e);
        }
    }
}
