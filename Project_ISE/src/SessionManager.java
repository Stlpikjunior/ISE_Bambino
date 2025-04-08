import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionManager {

    public static int startNewSession(int userID, Connection conn) throws SQLException {
        String insertSQL = "INSERT INTO Session (UserID) VALUES (?)";

        try (PreparedStatement stmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userID);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Return new SessionID
            } else {
                throw new SQLException("Failed to create session.");
            }
        }
    }
}
