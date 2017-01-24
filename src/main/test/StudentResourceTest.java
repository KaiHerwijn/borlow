import endpoints.ResourceException;
import models.Student;
import org.junit.Before;
import org.mockito.InjectMocks;
import repositories.StudentRepo;
import endpoints.StudentResource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.jboss.modules.filter.PathFilters.any;
import static org.mockito.Mockito.*;

/**
 * Created by Gebruiker on 18-1-2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentResourceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    StudentRepo stuffRepo = new StudentRepo();

    @InjectMocks
    StudentResource sr;

    @Before
    public void setup() throws ResourceException {
        try {
            when(stuffRepo.getAll()).thenReturn(new ArrayList<Student>());
        } catch (SQLException e) {
            throw new ResourceException("SQL-exception");
        } catch (ClassNotFoundException e) {
            throw new ResourceException("Class not found");
        }
    }

    @Test
    public void getAll_ToSuccess() throws ResourceException {

        try {
            List<Student> studentList = sr.getAll();

            verify(stuffRepo, times(1)).getAll();
        } catch (SQLException e) {
            throw new ResourceException("SQL-exception");
        } catch (ClassNotFoundException e) {
            throw new ResourceException("Class not found");
        }
    }

    @Test
    public void addStudent_ToSuccess() throws ResourceException {

        try {
            sr.add("Bart", "Bartbaan", "");

            verify(stuffRepo, times(1)).add("Bart", "Bartbaan", "");
        } catch (SQLException e) {
            throw new ResourceException("SQL-exception");
        } catch (ClassNotFoundException e) {
            throw new ResourceException("Class not found");
        }
    }

}
