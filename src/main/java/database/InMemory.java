package database;

import repositories.StudentRepo;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class InMemory {

    private StudentRepo stuffRepo;

    public InMemory() {

    }

    public StudentRepo getStudent() {
        return stuffRepo;
    }
}
