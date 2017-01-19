package database;

import repositories.StuffRepo;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class InMemory implements iBorlowRepo {

    private StuffRepo stuffRepo;

    public InMemory() {

    }

    @Override
    public StuffRepo getStuff() {
        return stuffRepo;
    }
}
