import endpoints.CourseInstanceResource;
import endpoints.RegistrationException;
import endpoints.RegistrationResource;
import endpoints.ResourceException;
import models.CourseInstance;
import models.Registration;
import models.Student;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repositories.CourseInstanceRepo;
import repositories.RegistrationRepo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Gebruiker on 25-1-2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    RegistrationRepo registrationRepo = new RegistrationRepo();

    @InjectMocks
    RegistrationResource rr = new RegistrationResource();

    @Test
    public void getAll_ToResourceException_BecauseCursusAlBegonnen() throws ResourceException, RegistrationException, SQLException, ClassNotFoundException {

        rr.add("ADCSB", "2017-03-05", 4);

        verify(registrationRepo, times(1)).add("ADCSB", Date.valueOf("2017-03-05"), 4);
    }
}
