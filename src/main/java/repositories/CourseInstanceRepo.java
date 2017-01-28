package repositories;

import database.Database;
import models.Course;
import models.CourseInstance;
import models.Student;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.ws.rs.NotFoundException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gebruiker on 24-1-2017.
 */
public class CourseInstanceRepo extends Database<CourseInstance> {

    public int add(String name, String address, String company) throws SQLException, ClassNotFoundException {
        return 0;
    }

    public List<CourseInstance> getAll() throws SQLException, ClassNotFoundException {
        super.getConnection();
        String query = "SELECT * FROM courseInstance INNER JOIN course ON courseInstance.code = course.code";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        ArrayList<CourseInstance> courseInstanceList = new ArrayList<CourseInstance>();
        while (resultSet.next())
        {
            CourseInstance courseInstance = new CourseInstance();
            courseInstance.setCode(resultSet.getString("code"));
            courseInstance.setStart(resultSet.getDate("start"));

            Course course = new Course();
            course.setTitle(resultSet.getString("title"));
            course.setDuration(resultSet.getInt("duration"));
            course.setCode(resultSet.getString("code"));
            courseInstance.setParentCourse(course);

            courseInstanceList.add(courseInstance);
        }
        super.closeAll();

        return courseInstanceList;
    }


    public CourseInstance get(String code, Date start) throws SQLException, ClassNotFoundException, NotFoundException {
        super.getConnection();

        String query = "SELECT * FROM courseInstance INNER JOIN course ON course.code = courseInstance.code WHERE courseInstance.code = ? AND start = ?";
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, code);
        preparedStatement.setString(2, start.toString());

        ResultSet resultSet = preparedStatement.executeQuery();

        CourseInstance courseInstance = new CourseInstance();
        boolean b = resultSet.isBeforeFirst(); // false als er geen rijen zijn
        if (resultSet.next()) {
            courseInstance.setCode(resultSet.getString("code"));
            courseInstance.setStart(resultSet.getDate("start"));

            Course course = new Course();
            course.setTitle(resultSet.getString("title"));
            course.setDuration(resultSet.getInt("duration"));
            course.setCode(resultSet.getString("code"));
            courseInstance.setParentCourse(course);
        } else {
            throw new NotFoundException("Cursusinstantie met code " + code + ", en startdatum " + start.toString() + " is niet gevonden.");
        }
        super.closeAll();
        return courseInstance;
    }
}
