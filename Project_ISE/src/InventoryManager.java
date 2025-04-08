public class InventoryManager {
    public static void openInventory(UserProfile user) {
        System.out.println("🎒 Inventory");
        System.out.println("💰 Coins: " + user.getCoins());
    }
}
