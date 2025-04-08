import java.sql.*;

public class UserManager {

    public static int getOrCreateUser(String name, int Age, Connection conn) throws SQLException {
        String selectSQL = "SELECT UserID FROM User WHERE Name = ?";
        String insertSQL = "INSERT INTO User (Name, Age) VALUES (?,?)";

        try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
            selectStmt.setString(1, name);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("UserID");
            } else {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                    insertStmt.setString(1, name);
                    insertStmt.setInt(2, Age);
                    insertStmt.executeUpdate();
                    ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Failed to create user.");
                    }
                }
            }
        }
    }
}
