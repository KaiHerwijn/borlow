import models.Student;
import repositories.StudentRepo;
import endpoints.studentResource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
    StudentRepo stuffRepo = new StudentRepo(null);

    @Test
    public void getAll_ToSuccess() {
        studentResource sr = new studentResource();
        List<Student> studentList = sr.getAll();

        verify(stuffRepo, times(1)).getAll();
    }

    @Test
    public void addStuff_ToSuccess() {
        studentResource sr = new studentResource();
        sr.add("Bart", "Bartbaan", "");

        verify(stuffRepo, times(1)).add("Bus", "Bartbaan", "");
    }

    @Test
    public void getStuff_ToNotFoundException() {

    }
}
