package compulsory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL =
            "jdbc:mysql://localhost:3306/pa_db";
    private static final String USER = "root";
    private static final String PASSWORD = "toor";

    private static Connection connection = null;

    private Database() {
    }

    public static Connection getConnection() {
        if (connection != null)
            return connection;

        createConnection();
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {

    }
}