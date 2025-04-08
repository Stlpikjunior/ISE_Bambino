import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int coins;
    private final List<String> items; // You can replace String with a real Item class later

    public Inventory() {
        this.coins = 0;
        this.items = new ArrayList<>();
    }

    // Coin methods
    public int getCoins() { return coins; }
    public void addCoins(int amount) { this.coins += amount; }
    public void spendCoins(int amount) { this.coins -= amount; }

    // Item methods (placeholder)
    public List<String> getItems() { return items; }
    public void addItem(String item) { items.add(item); }

    // Optional: show inventory summary
    public void printInventory() {
        System.out.println("\n🎒 Inventory");
        System.out.println("💰 Coins: " + coins);
        System.out.println("📦 Items: " + (items.isEmpty() ? "[Empty]" : items));
    }

    public static void openInventory(UserProfile user) {
        user.getInventory().printInventory();
    }
}
