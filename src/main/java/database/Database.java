package database;

import repositories.StudentRepo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Gebruiker on 16-1-2017.
 */
public class Database implements iBorlowRepo {
    private static Database instance = null;
    private StudentRepo stuffRepo;
    private Connection connection;

    protected Database() {
        LoadDriver();

        stuffRepo = new StudentRepo(this);
    }

    public Connection getStatement() {
        connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/borlow?serverTimezone=UTC", "root", "");
            Statement statement = connection.createStatement();
            return connection;

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        return null;
    }

    private void LoadDriver() {
        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        System.out.println("MySQL JDBC Driver Registered!");
    }

    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public StudentRepo getStudent() {
        return stuffRepo;
    }

}
