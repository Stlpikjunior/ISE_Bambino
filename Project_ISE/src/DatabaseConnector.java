import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:sqlite:JDM_An_Unexpected_Journey.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("✅ Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        connect();
    }
}
