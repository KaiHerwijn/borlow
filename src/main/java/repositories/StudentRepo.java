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
public class StudentRepo extends Database<Student> {

    public int add(String name, String address, String company) throws SQLException, ClassNotFoundException {
        super.getConnection();

        String query = "INSERT INTO student (name, address, company) VALUES (?,?,?)";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString(1, name);
        preparedStmt.setString(2, address);
        preparedStmt.setString(3, company);
        int result = preparedStmt.executeUpdate();
        connection.close();

        return result;
    }

    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        super.getConnection();
        String query = "SELECT * FROM student";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        ArrayList<Student> studentList = new ArrayList<Student>();
        while (resultSet.next())
        {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setAddress(resultSet.getString("address"));
            student.setCompany(resultSet.getString("company"));
            studentList.add(student);
        }
        super.closeAll();

        return studentList;
    }

    public Student get(int id) throws SQLException, ClassNotFoundException, NotFoundException {
        super.getConnection();

        String query = "SELECT * FROM student WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        Student student = new Student();
        if (resultSet.next()) {
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setAddress(resultSet.getString("address"));
            student.setCompany(resultSet.getString("company"));
        } else {
            throw new NotFoundException("Student met id " + id + " is niet gevonden.");
        }
        super.closeAll();
        return student;
    }
}