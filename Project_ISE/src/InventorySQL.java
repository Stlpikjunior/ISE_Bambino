import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventorySQL {
    private final Connection conn;

    public InventorySQL(Connection conn) {
        this.conn = conn;
    }

    public List<String> loadUserItems(int userID) throws SQLException {
        List<String> itemNames = new ArrayList<>();
        String sql = "SELECT ItemName FROM UserInventory WHERE UserID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                itemNames.add(rs.getString("ItemName"));
            }
        }

        return itemNames;
    }

    public void saveItem(int userID, String itemName, String itemType) {
        String sql = "INSERT OR IGNORE INTO UserInventory (UserID, ItemName, ItemType) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.setString(2, itemName);
            stmt.setString(3, itemType);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Failed to save item to DB: " + e.getMessage());
        }
    }
}

