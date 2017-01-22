import database.Database;
import database.InMemory;
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
public class StuffResourceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    StudentRepo stuffRepo = new StudentRepo();

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        when(stuffRepo.getAll()).thenReturn(new ArrayList<Student>());
    }

    @Test
    public void getAll_ToSuccess() throws SQLException, ClassNotFoundException {
        StudentResource sr = new StudentResource();
        List<Student> studentList = sr.getAll();

        verify(stuffRepo, times(1)).getAll();
    }

    @Test
    public void addStuff_ToSuccess() throws SQLException, ClassNotFoundException {
        StudentResource sr = new StudentResource();
        sr.add("Bart", "Bartbaan", "");

        verify(stuffRepo, times(1)).add("Bus", "Bartbaan", "");
    }

}
