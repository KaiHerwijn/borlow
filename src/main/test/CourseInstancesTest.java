import endpoints.CourseInstanceResource;
import endpoints.ResourceException;
import models.CourseInstance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repositories.CourseInstanceRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Gebruiker on 24-1-2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CourseInstancesTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    CourseInstanceRepo courseInstanceRepo = new CourseInstanceRepo();

    @InjectMocks
    CourseInstanceResource cir;

    @Before
    public void setup() throws ResourceException {
        try {
            when(courseInstanceRepo.getAll()).thenReturn(new ArrayList<CourseInstance>());
        } catch (SQLException e) {
            throw new ResourceException("SQL-exception", e);
        } catch (ClassNotFoundException e) {
            throw new ResourceException("Class not found", e);
        }
    }

    @Test
    public void getCourseInstances_ToSuccess() throws ResourceException {

        try {
            List<CourseInstance> studentList = cir.getAll();

            verify(courseInstanceRepo, times(1)).getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
