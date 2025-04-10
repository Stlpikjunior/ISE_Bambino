import java.sql.*;

public class CoinSQL {
    private final Connection conn;

    public CoinSQL(Connection conn) {
        this.conn = conn;
    }

    public void saveCoins(int userID, int coins) {
        String sql = "INSERT INTO UserCoins (UserID, Coins) VALUES (?, ?) " +
                "ON CONFLICT(UserID) DO UPDATE SET Coins = excluded.Coins";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, coins);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Failed to save coins: " + e.getMessage());
        }
    }

    public int loadCoins(int userID) {
        String sql = "SELECT Coins FROM UserCoins WHERE UserID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Coins");
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to load coins: " + e.getMessage());
        }

        return 0; // Default if user not found
    }
}
