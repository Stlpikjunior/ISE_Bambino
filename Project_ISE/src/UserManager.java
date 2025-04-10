import java.sql.*;

public class UserManager {

    public static User getOrCreateUser(String name, int age, Connection conn) throws SQLException {
        String selectSQL = "SELECT UserID FROM User WHERE Name = ?";
        String insertSQL = "INSERT INTO User (Name, Age) VALUES (?, ?)";

        try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
            selectStmt.setString(1, name);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("UserID");
                return new User(userID, name, age);
            } else {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                    insertStmt.setString(1, name);
                    insertStmt.setInt(2, age);
                    insertStmt.executeUpdate();

                    ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int userID = generatedKeys.getInt(1);
                        return new User(userID, name, age);
                    } else {
                        throw new SQLException("Failed to create user.");
                    }
                }
            }
        }
    }
}
