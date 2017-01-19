package database;

import repositories.StudentRepo;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class InMemory implements iBorlowRepo {

    private StudentRepo stuffRepo;

    public InMemory() {

    }

    @Override
    public StudentRepo getStudent() {
        return stuffRepo;
    }
}
