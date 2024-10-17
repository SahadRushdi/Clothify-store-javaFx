package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;

    static {
        try {
            instance = new DBConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection connection;
    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clothfystore", "root", "12345");
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws SQLException {
        return null == instance?instance = new DBConnection():instance;
    }
}
