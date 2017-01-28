package endpoints;

import java.sql.SQLException;

/**
 * Created by Gebruiker on 24-1-2017.
 */
public class ResourceException extends Exception {
    public ResourceException(String s, Exception e) {
        super(s, e);
    }
}
