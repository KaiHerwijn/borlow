package repositories;

import database.Database;
import endpoints.RegistrationException;
import models.Registration;
import models.Student;

import javax.ws.rs.NotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Gebruiker on 25-1-2017.
 */
public class RegistrationRepo extends Database<Registration> {

    public int add(String code, Date start, int student) throws SQLException, ClassNotFoundException, RegistrationException {
        super.getConnection();

        // bestaan de gekozen entities wel?
        try {
            StudentRepo studentRepo = new StudentRepo();
            studentRepo.get(student);
        } catch (NotFoundException e) {
            throw new RegistrationException("Student bestaat niet en kon daarom niet worden ingeschreven voor cursusinstantie. Student: " + student + " Cursus: " + code + " Startdatum: " + start.toString(), e);
        }
        try {
            CourseInstanceRepo courseInstanceRepo = new CourseInstanceRepo();
            courseInstanceRepo.get(code, start);
        } catch (NotFoundException e) {
            throw new RegistrationException("Cursusinstantie bestaat niet en daarom kon de studen niet worden ingeschreven voor deze cursusinstantie. Student: " + student + " Cursus: " + code + " Startdatum: " + start.toString(), e);
        }

        // is de student op deze cursus niet al ingeschreven?
        RegistrationRepo registrationRepo = new RegistrationRepo();
        if (registrationRepo.exist(code, start, student)) {
            throw new RegistrationException("Student met id " + student + " is al ingeschreven voor de cursus " + code + " startende op " + start.toString() + " en kon daarom niet ingeschreven worden.", null);
        }

        // is de cursus niet al begonnen?
        if (start.before(new Date(Calendar.getInstance().getTime().getTime()))) {
            throw new RegistrationException("De cursus " + code + " is al begonnen en daarom kon de student niet ingeschreven worden.", null);
        }

        String query = "INSERT INTO registration (code, start, student) VALUES (?,?,?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, code);
        preparedStatement.setDate(2, start);
        preparedStatement.setInt(3, student);
        int result = preparedStatement.executeUpdate();
        connection.close();
        return result;

    }

    public boolean exist(String code, Date start, int student) throws SQLException, ClassNotFoundException {
        super.getConnection();

        String query = "SELECT count(*) FROM registration WHERE code = ? AND start = ? AND student = ?";
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, code);
        preparedStatement.setString(2, start.toString());
        preparedStatement.setInt(3, student);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean result = false;
        if (resultSet.next()) {
            if (resultSet.getInt("count(*)") >= 1) {
                result = true;
            } else {
                result = false;
            }
        }
        super.closeAll();
        return result;
    }
}
