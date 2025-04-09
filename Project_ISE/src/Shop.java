import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Shop {

    public void open(UserProfile user, Scanner scanner) {
        Inventory inventory = user.getInventory();
        Monsterdex monsterdex = inventory.getMonsterdex();
        boolean shopping = true;

        while (shopping) {
            System.out.println("\n🛒 Welcome to the Monster Shop!");
            System.out.println("💰 Coins: " + inventory.getCoins());
            displayAvailableMonsters(monsterdex, inventory);

            int maxOption = monsterdex.getMonsters().size();
            System.out.println((maxOption + 1) + ". Gamble (6 coins)");
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
                gamble(inventory);
            } else if (choice >= 1 && choice <= maxOption) {
                Monster selected = monsterdex.getMonsters().get(choice - 1);
                buyMonster(selected, inventory);
            } else {
                System.out.println("❌ Invalid choice.");
            }
        }
    }

    private void displayAvailableMonsters(Monsterdex dex, Inventory inv) {
        List<Monster> monsters = dex.getMonsters();
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

    private void buyMonster(Monster monster, Inventory inv) {
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

        inv.spendCoins(monster.getPrice());
        inv.addItem(monster);
        monster.unlock();

        System.out.println("✅ You bought " + monster.getName() + "! 🎉");
    }

    private void gamble(Inventory inv) {
        int gambleCost = 6;

        if (inv.getCoins() < gambleCost) {
            System.out.println("❌ Not enough coins to gamble.");
            return;
        }

        List<Monster> locked = inv.getMonsterdex().getMonsters().stream()
                .filter(m -> inv.getItems().stream()
                        .noneMatch(item -> item.getName().equals(m.getName())))
                .toList();

        if (locked.isEmpty()) {
            System.out.println("🎉 You already own all monsters!");
            return;
        }

        Monster reward = locked.get(new Random().nextInt(locked.size()));
        inv.spendCoins(gambleCost);
        inv.addItem(reward);
        reward.unlock();

        System.out.println("🎲 You gambled and won: " + reward.getName() + "!");
    }
}
