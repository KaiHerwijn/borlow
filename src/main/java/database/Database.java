package database;

import java.sql.*;
import java.util.List;

/**
 * Created by Gebruiker on 16-1-2017.
 */
public abstract class Database <E> {

    private final static String driver = "com.mysql.cj.jdbc.Driver";
    private final static String database = "jdbc:mysql://localhost:3306/borlow?serverTimezone=UTC";
    private final static String username = "root";
    private final static String  password = "";

    protected Connection connection = null;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet = null;

    protected void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(database, username, password);
    }

    protected void closeAll() throws SQLException {
        preparedStatement.close();
        if(resultSet != null) {
            resultSet.close();
        }
        connection.close();
    }
}
