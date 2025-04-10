import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Character {
    private Outfit currentOutfit;
    private Monster currentBuddy;

    public Outfit getCurrentOutfit() {
        return currentOutfit;
    }

    public void setCurrentOutfit(Outfit currentOutfit) {
        this.currentOutfit = currentOutfit;
    }

    public Monster getCurrentBuddy() {
        return currentBuddy;
    }

    public void setCurrentBuddy(Monster currentBuddy) {
        this.currentBuddy = currentBuddy;
    }

    public static void openCharacterCustomization(User user, Scanner scanner, Character character) {
        System.out.println("🎨 Welcome to Character Customization, " + user.getName() + "!");

        boolean exitMenu = false;
        while (!exitMenu) {
            // Show current equipment
            System.out.println("\n👕 Currently equipped outfit: "
                    + (character.getCurrentOutfit() != null ? character.getCurrentOutfit().getName() : "None"));
            System.out.println("🐉 Currently equipped buddy: "
                    + (character.getCurrentBuddy() != null ? character.getCurrentBuddy().getName() : "None"));

            System.out.println("\n1. 🧥 Change Outfit");
            System.out.println("2. 🐲 Change Buddy");
            System.out.println("3. ❌ Exit Customization");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    Outfit newOutfit = chooseOutfit(user, scanner);
                    if (newOutfit != null) {
                        character.setCurrentOutfit(newOutfit);
                        System.out.println("🧥 New outfit equipped: " + newOutfit.getName());
                    }
                    break;
                case "2":
                    Monster newBuddy = chooseBuddy(user, scanner);
                    if (newBuddy != null) {
                        character.setCurrentBuddy(newBuddy);
                        System.out.println("🐲 New buddy equipped: " + newBuddy.getName());
                    }
                    break;
                case "3":
                    exitMenu = true;
                    break;
                default:
                    System.out.println("⚠️ Invalid choice. Please try again.");
            }
        }
    }

    private static Outfit chooseOutfit(User user, Scanner scanner) {
        List<Item> items = user.getInventory().getItems();
        List<Outfit> unlockedOutfits = new ArrayList<>();

        for (Item item : items) {
            if (item instanceof Outfit && !item.isLocked()) {
                unlockedOutfits.add((Outfit) item);
            }
        }

        if (unlockedOutfits.isEmpty()) {
            System.out.println("No unlocked outfits found.");
            return null;
        }

        System.out.println("\nAvailable unlocked outfits:");
        for (int i = 0; i < unlockedOutfits.size(); i++) {
            System.out.println((i + 1) + ". " + unlockedOutfits.get(i));
        }

        System.out.print("Enter the number of the outfit to equip: ");
        String input = scanner.nextLine().trim();

        int index;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid number. Canceling outfit selection.");
            return null;
        }

        if (index < 1 || index > unlockedOutfits.size()) {
            System.out.println("⚠️ Selection out of range. Canceling outfit selection.");
            return null;
        }

        return unlockedOutfits.get(index - 1);
    }


    private static Monster chooseBuddy(User user, Scanner scanner) {
        List<Item> items = user.getInventory().getItems();
        List<Monster> unlockedMonsters = new ArrayList<>();

        for (Item item : items) {
            if (item instanceof Monster && !item.isLocked()) {
                unlockedMonsters.add((Monster) item);
            }
        }

        if (unlockedMonsters.isEmpty()) {
            System.out.println("No unlocked monsters found.");
            return null;
        }

        System.out.println("\nAvailable unlocked monsters:");
        for (int i = 0; i < unlockedMonsters.size(); i++) {
            System.out.println((i + 1) + ". " + unlockedMonsters.get(i));
        }

        System.out.print("Enter the number of the monster to equip: ");
        String input = scanner.nextLine().trim();

        int index;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid number. Canceling buddy selection.");
            return null;
        }

        if (index < 1 || index > unlockedMonsters.size()) {
            System.out.println("⚠️ Selection out of range. Canceling buddy selection.");
            return null;
        }

        return unlockedMonsters.get(index - 1);
    }
}