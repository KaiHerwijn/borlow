import repositories.StuffRepo;
import endpoints.StuffResource;
import models.Stuff;
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
    StuffRepo stuffRepo = new StuffRepo(null);

    @Test
    public void getAll_ToSuccess() {
        StuffResource sr = new StuffResource();
        List<Stuff> stuffList = sr.getAll();

        verify(stuffRepo, times(1)).getAll();
    }

    @Test
    public void addStuff_ToSuccess() {
        StuffResource sr = new StuffResource();
        sr.add("Bus");

        verify(stuffRepo, times(1)).add("Bus");
    }

    @Test
    public void getStuff_ToNotFoundException() {

    }
}
