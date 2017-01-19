package repositories;

import database.Database;
import models.Student;

import javax.ws.rs.NotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gebruiker on 12-1-2017.
 */
public class StudentRepo implements iResource<Student> {

    private final Database database;

    public StudentRepo(Database database) {
        this.database = database;
    }

    public void add(String name, String address, String company) {
        try {
            Connection connection = database.getStatement();
            Statement statement = connection.createStatement();

            PreparedStatement preparedStmt = connection.prepareStatement("INSERT INTO `student` (name, address, company) VALUES (?,?,?)");
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, address);
            preparedStmt.setString(3, company);
            preparedStmt.execute();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAll() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        try {
            Connection connection = database.getStatement();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
            while (resultSet.next())
            {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAddress(resultSet.getString("address"));
                student.setCompany(resultSet.getString("company"));
                studentList.add(student);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public Student get(int id) {
        Student student = null;
        try {
            Connection connection = database.getStatement();
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM stuff WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            student = new Student();
            if (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
            } else {
                throw new NotFoundException("Student met id " + id + " is niet gevonden.");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
}