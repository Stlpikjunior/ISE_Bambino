import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Shop {

    public void open(User user, Scanner scanner, InventorySQL inventorySQL, CoinSQL coinSQL) {
        Inventory inventory = user.getInventory();
        Monsterdex monsterdex = inventory.getMonsterdex();
//        Wardrobe wardrobe = inventory.getWardrobe();
        boolean shopping = true;

        while (shopping) {
            System.out.println("\n🛒 Welcome to the Monster Shop!");
            System.out.println("💰 Coins: " + inventory.getCoins());
            displayAvailableMonsters(monsterdex, inventory);
//            displayAvailableOutfits(wardrobe, inventory);


            int maxOption = monsterdex.getItems().size();
            System.out.println((maxOption + 1) + ". Gamble (35 coins)");
            System.out.println((maxOption + 2) + ". Exit");

            System.out.print("Choose an option: ");
            int choice;


            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // flush
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("❌ Invalid input.");
                continue;
            }

            if (choice == maxOption + 2) {
                shopping = false;
                System.out.println("👋 Exiting shop...");
            } else if (choice == maxOption + 1) {
                gamble(inventory, user, inventorySQL, coinSQL);
            } else if (choice >= 1 && choice <= maxOption) {
                Monster selected = monsterdex.getItems().get(choice - 1);
                buyMonster(selected, inventory, user, inventorySQL, coinSQL);
            } else {
                System.out.println("❌ Invalid choice.");
            }
        }
    }

    private void displayAvailableMonsters(Monsterdex dex, Inventory inv) {
        List<Monster> monsters = dex.getItems();
        System.out.println("Available Monsters:");
        for (int i = 0; i < monsters.size(); i++) {
            Monster m = monsters.get(i);
            boolean owned = inv.getItems().stream()
                    .anyMatch(item -> item.getName().equals(m.getName()));
            if (!owned) {
                System.out.println((i + 1) + ". " + m.getName() + " - " + m.getPrice() + " coins (" + m.getDescription() + ")");
            }
        }
    }

    private void buyMonster(Monster monster, Inventory inv, User user, InventorySQL inventorySQL, CoinSQL coinSQL) {
        boolean alreadyOwned = inv.getItems().stream()
                .anyMatch(item -> item.getName().equals(monster.getName()));
        if (alreadyOwned) {
            System.out.println("⚠️ You already own " + monster.getName());
            return;
        }

        if (inv.getCoins() < monster.getPrice()) {
            System.out.println("❌ Not enough coins to buy " + monster.getName());
            return;
        }

        inv.spendCoins(monster.getPrice(), user.getUserID(),coinSQL);
        inv.addItem(monster, user.getUserID(), inventorySQL);
        monster.unlock();

        System.out.println("✅ You bought " + monster.getName() + "! 🎉");
    }

    private void gamble(Inventory inv, User user, InventorySQL inventorySQL, CoinSQL coinSQL) {
        int gambleCost = 35;

        if (inv.getCoins() < gambleCost) {
            System.out.println("❌ Not enough coins to gamble.");
            return;
        }

        List<Monster> locked = inv.getMonsterdex().getItems().stream()
                .filter(m -> inv.getItems().stream()
                        .noneMatch(item -> item.getName().equals(m.getName())))
                .toList();

        if (locked.isEmpty()) {
            System.out.println("🎉 You already own all monsters!");
            return;
        }

        Monster reward = locked.get(new Random().nextInt(locked.size()));
        inv.spendCoins(gambleCost, user.getUserID(), coinSQL);
        inv.addItem(reward, user.getUserID(), inventorySQL);
        reward.unlock();

        System.out.println("🎲 You gambled and won: " + reward.getName() + "!");
    }
}
