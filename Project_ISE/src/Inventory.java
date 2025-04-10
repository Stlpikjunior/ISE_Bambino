import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int coins;
    private final List<Item> items;
    private final Monsterdex monsterdex;
    private final Wardrobe wardrobe;

    public Inventory() {
        this.coins = 0;
        this.items = new ArrayList<>();
        this.monsterdex = new Monsterdex();
        this.wardrobe = new Wardrobe();


        for (Monster m: monsterdex.getItems()) {
            if (!m.isLocked()) {
                items.add(m);
            }
        }

        for (Outfit o : wardrobe.getItems()) {
            if (!o.isLocked()) {
                items.add(o);
            }
        }
    }

    // Coin methods
    public int getCoins() { return coins; }
    public void setCoins(int coins) {
        this.coins = coins;
    }
    public void addCoins(int amount, int userID, CoinSQL coinSQL) {
        this.coins += amount;
        coinSQL.saveCoins(userID, this.coins);
    }
    public void spendCoins(int amount, int userID, CoinSQL coinSQL) {
        this.coins -= amount;
        coinSQL.saveCoins(userID, this.coins);
    }

    public Monsterdex getMonsterdex() {
        return monsterdex;
    }
    public Wardrobe getWardrobe() {
        return wardrobe;
    }

    // Item methods
    public List<Item> getItems() { return items; }

    public void addItem(Item item, int userID, InventorySQL dao) {
        item.unlock();
        items.add(item);

        String type = item instanceof Monster ? "Monster" : "Outfit";
        try {
            dao.saveItem(userID, item.getName(), type);
        } catch (Exception e) {
            System.out.println("❌ Failed to save item to database: " + e.getMessage());
        }
    }


    public void printInventory() {
        System.out.println("\n🎒 Inventory");
        System.out.println("💰 Coins: " + coins);
        if (items.isEmpty()) {
            System.out.println("📦 Items: [Empty]");
        } else {
            System.out.println("📦 Items:");
            for (Item item : items) {
                System.out.println(" - " + item);
            }
        }
    }

    public static void openInventory(User user) {
        user.getInventory().printInventory();
    }
}
