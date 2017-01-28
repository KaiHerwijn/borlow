package endpoints;

import javax.ws.rs.NotFoundException;

/**
 * Created by Gebruiker on 26-1-2017.
 */
public class RegistrationException extends Exception {
    public RegistrationException(String s, Exception e) {
        super(s, e);
    }
}
