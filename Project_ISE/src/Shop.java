import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Shop {

    public void open(User user, Scanner scanner, InventorySQL inventorySQL, CoinSQL coinSQL) {
        Inventory inventory = user.getInventory();
        Monsterdex monsterdex = inventory.getMonsterdex();
        Wardrobe wardrobe = inventory.getWardrobe();
        boolean shopping = true;

        while (shopping) {
            System.out.println("\n🛒 Welcome to the Monster Shop!");
            System.out.println("💰 Coins: " + inventory.getCoins());
            displayAvailableMonsters(monsterdex, inventory);
            displayAvailableOutfits(wardrobe, inventory);

            int maxOption = monsterdex.getItems().size() + wardrobe.getItems().size();
            System.out.println((maxOption + 1) + ". Chest (35 coins, gives a random outfit or monster)");
            System.out.println((maxOption + 2) + ". Exit");

            System.out.print("Choose an option: ");
            int choice;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
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
            } else if (choice >= 1 && choice <= monsterdex.getItems().size()) {
                Monster selected = monsterdex.getItems().get(choice - 1);
                buyMonster(selected, inventory, user, inventorySQL, coinSQL);
            } else if (choice > monsterdex.getItems().size() && choice <= maxOption) {
                Outfit selectedOutfit = wardrobe.getItems().get(choice - monsterdex.getItems().size() - 1);
                buyOutfit(selectedOutfit, inventory, user, inventorySQL, coinSQL);
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

        System.out.println("-------------------------------");
    }

    private void displayAvailableOutfits(Wardrobe wardrobe, Inventory inv) {
        List<Outfit> outfits = wardrobe.getItems();
        System.out.println("Available Outfits:");
        for (int i = 0; i < outfits.size(); i++) {
            Outfit o = outfits.get(i);
            boolean owned = inv.getItems().stream()
                    .anyMatch(item -> item.getName().equals(o.getName()));
            if (!owned) {
                System.out.println((i+11) + ". " + o.getName() + " - " + o.getPrice() + " coins (" + o.getDescription() + ")");
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

        inv.spendCoins(monster.getPrice(), user.getUserID(), coinSQL);
        inv.addItem(monster, user.getUserID(), inventorySQL);
        monster.unlock();

        System.out.println("✅ You bought " + monster.getName() + "! 🎉");
    }

    private void buyOutfit(Outfit outfit, Inventory inv, User user, InventorySQL inventorySQL, CoinSQL coinSQL) {
        boolean alreadyOwned = inv.getItems().stream()
                .anyMatch(item -> item.getName().equals(outfit.getName()));
        if (alreadyOwned) {
            System.out.println("⚠️ You already own " + outfit.getName());
            return;
        }

        if (inv.getCoins() < outfit.getPrice()) {
            System.out.println("❌ Not enough coins to buy " + outfit.getName());
            return;
        }

        inv.spendCoins(outfit.getPrice(), user.getUserID(), coinSQL);
        inv.addItem(outfit, user.getUserID(), inventorySQL);
        outfit.unlock();

        System.out.println("✅ You bought " + outfit.getName() + "! 🎉");
    }

    private void gamble(Inventory inv, User user, InventorySQL inventorySQL, CoinSQL coinSQL) {
        int gambleCost = 35;

        if (inv.getCoins() < gambleCost) {
            System.out.println("❌ Not enough coins to gamble.");
            return;
        }

        List<Monster> lockedMonsters = inv.getMonsterdex().getItems().stream()
                .filter(m -> inv.getItems().stream()
                        .noneMatch(item -> item.getName().equals(m.getName())))
                .toList();


        List<Outfit> lockedOutfits = inv.getWardrobe().getItems().stream()
                .filter(o -> inv.getItems().stream()
                        .noneMatch(item -> item.getName().equals(o.getName())))
                .toList();

        if (lockedMonsters.isEmpty() && lockedOutfits.isEmpty()) {
            System.out.println("🎉 You already own everything!");
            return;
        }

        Random random = new Random();
        if (random.nextBoolean() && !lockedMonsters.isEmpty()) {
            Monster reward = lockedMonsters.get(random.nextInt(lockedMonsters.size()));
            inv.spendCoins(gambleCost, user.getUserID(), coinSQL);
            inv.addItem(reward, user.getUserID(), inventorySQL);
            reward.unlock();
            System.out.println("🎲 You gambled and won: " + reward.getName() + "!");
        } else if (!lockedOutfits.isEmpty()) {
            Outfit reward = lockedOutfits.get(random.nextInt(lockedOutfits.size()));
            inv.spendCoins(gambleCost, user.getUserID(), coinSQL);
            inv.addItem(reward, user.getUserID(), inventorySQL);
            reward.unlock();
            System.out.println("🎲 You gambled and won: " + reward.getName() + "!");
        }
    }
}