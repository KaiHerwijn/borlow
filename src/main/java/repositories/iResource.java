package repositories;

import java.util.List;

/**
 * Created by Gebruiker on 17-1-2017.
 */
public interface iResource<E> {

    void add(String name, String address, String company);
    List<E> getAll();
    E get(int id);
}
