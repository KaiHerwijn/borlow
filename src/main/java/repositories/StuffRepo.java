package repositories;

import database.Database;
import models.Stuff;

import javax.ws.rs.NotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gebruiker on 12-1-2017.
 */
public class StuffRepo implements iResource<Stuff> {

    private final Database database;

    public StuffRepo(Database database) {
        this.database = database;
    }

    public void add(String name) {
        try {
            Connection connection = database.getStatement();
            Statement statement = connection.createStatement();

            PreparedStatement preparedStmt = connection.prepareStatement("INSERT INTO `stuff` (name) VALUES (?)");
            preparedStmt.setString(1, name);
            preparedStmt.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Stuff> getAll() {
        ArrayList<Stuff> stuffList = new ArrayList<Stuff>();
        try {
            Connection connection = database.getStatement();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM stuff");
            while (resultSet.next())
            {
                Stuff stuff = new Stuff();
                stuff.setId(resultSet.getInt("id"));
                stuff.setName(resultSet.getString("name"));
                stuffList.add(stuff);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stuffList;
    }

    public Stuff get(int id) {
        Stuff stuff = null;
        try {
            Connection connection = database.getStatement();
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM stuff WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            stuff = new Stuff();
            if (resultSet.next()) {
                stuff.setId(resultSet.getInt("id"));
                stuff.setName(resultSet.getString("name"));
            } else {
                throw new NotFoundException("Stuff met id " + id + " is niet gevonden.");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stuff;
    }
}