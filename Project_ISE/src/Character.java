//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Character {
//    private Outfit currentOutfit;
//    private Monster currentBuddy;
//
//    // Getters and setters
//    public Outfit getCurrentOutfit() {
//        return currentOutfit;
//    }
//
//    public void setCurrentOutfit(Outfit currentOutfit) {
//        this.currentOutfit = currentOutfit;
//    }
//
//    public Monster getCurrentBuddy() {
//        return currentBuddy;
//    }
//
//    public void setCurrentBuddy(Monster currentBuddy) {
//        this.currentBuddy = currentBuddy;
//    }
//
//    /**
//     * Opens the character customization interface. This allows the user to choose:
//     * 1. An unlocked outfit (to "wear") from the Wardrobe.
//     * 2. A buddy from unlocked monsters in the Monsterdex.
//     *
//     * @param user The current User instance.
//     * @param conn The database connection (reserved for future integration).
//     * @param scanner The Scanner for user input.
//     * @return A Character instance with the selected outfit and buddy.
//     */
//    public static Character openCharacterCustomization(User user, Connection conn, Scanner scanner) {
//        System.out.println("🎨 Welcome to Character Customization, " + user.getName() + "!");
//
//        // Retrieve unlocked outfits from the Wardrobe.
//        Wardrobe wardrobe = new Wardrobe();  // Assuming Wardrobe is implemented similarly to Monsterdex.
//        List<Outfit> unlockedOutfits = new ArrayList<>();
//        for (Outfit outfit : wardrobe.getItems()) {
//            if (!outfit.isLocked()) {
//                unlockedOutfits.add(outfit);
//            }
//        }
//        if (unlockedOutfits.isEmpty()) {
//            System.out.println("No unlocked outfits found. Defaulting to the first outfit in the wardrobe.");
//            unlockedOutfits.add(wardrobe.getItems().get(0));
//        }
//        System.out.println("\nChoose an outfit to wear:");
//        for (int i = 0; i < unlockedOutfits.size(); i++) {
//            System.out.println((i + 1) + ". " + unlockedOutfits.get(i));
//        }
//        System.out.print("Enter the number of your chosen outfit: ");
//        int outfitChoice = scanner.nextInt();
//        scanner.nextLine(); // consume newline
//
//        // Retrieve unlocked monsters (buddies) from Monsterdex.
//        Monsterdex monsterdex = new Monsterdex();
//        List<Monster> unlockedMonsters = new ArrayList<>();
//        for (Monster monster : monsterdex.getItems()) {
//            if (!monster.isLocked()) {
//                unlockedMonsters.add(monster);
//            }
//        }
//        if (unlockedMonsters.isEmpty()) {
//            System.out.println("No unlocked monsters found. Defaulting to the first monster.");
//            unlockedMonsters.add(monsterdex.getItems().get(0));
//        }
//        System.out.println("\nChoose a buddy from your unlocked monsters:");
//        for (int i = 0; i < unlockedMonsters.size(); i++) {
//            System.out.println((i + 1) + ". " + unlockedMonsters.get(i));
//        }
//        System.out.print("Enter the number of your chosen buddy: ");
//        int buddyChoice = scanner.nextInt();
//        scanner.nextLine(); // consume newline
//
//        // Create a Character instance and assign the selected outfit and buddy.
//        Character character = new Character();
//        character.setCurrentOutfit(unlockedOutfits.get(outfitChoice - 1));
//        character.setCurrentBuddy(unlockedMonsters.get(buddyChoice - 1));
//
//        System.out.println("\n✅ Customization complete!");
//        System.out.println("You are now wearing: " + character.getCurrentOutfit().getName());
//        System.out.println("Your buddy is: " + character.getCurrentBuddy().getName());
//
//        return character;
//    }
//}


//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Character {
//    private Outfit currentOutfit;
//    private Monster currentBuddy;
//
//    // Getters and setters
//    public Outfit getCurrentOutfit() {
//        return currentOutfit;
//    }
//
//    public void setCurrentOutfit(Outfit currentOutfit) {
//        this.currentOutfit = currentOutfit;
//    }
//
//    public Monster getCurrentBuddy() {
//        return currentBuddy;
//    }
//
//    public void setCurrentBuddy(Monster currentBuddy) {
//        this.currentBuddy = currentBuddy;
//    }
//
//    /**
//     * Opens the character customization menu directly.
//     * Shows the current outfit and buddy, and offers the user:
//     *   1) Change outfit
//     *   2) Change buddy
//     *   3) Exit
//     *
//     * @param user    The current User instance.
//     * @param conn    The database connection (for future or ongoing use).
//     * @param scanner The Scanner for user input.
//     */
//
//    public static void openCharacterCustomization(User user, Connection conn, Scanner scanner, Character character) {
//
//        System.out.println("🎨 Welcome to Character Customization, " + user.getName() + "!");
//
//        boolean exitMenu = false;
//        while (!exitMenu) {
//            // Show current equipment
//            System.out.println("\nCurrently equipped outfit: " +
//                    (character.getCurrentOutfit() != null
//                            ? character.getCurrentOutfit().getName()
//                            : "None"));
//            System.out.println("Currently equipped buddy: " +
//                    (character.getCurrentBuddy() != null
//                            ? character.getCurrentBuddy().getName()
//                            : "None"));
//
//            System.out.println("\n1. 🧥 Change Outfit");
//            System.out.println("2. 🐲 Change Buddy");
//            System.out.println("3. ❌ Exit Customization");
//            System.out.print("Enter your choice: ");
//
//            String choice = scanner.nextLine().trim();
//            switch (choice) {
//                case "1":
//                    Outfit newOutfit = chooseOutfit(conn, scanner);
//                    if (newOutfit != null) {
//                        character.setCurrentOutfit(newOutfit);
//                        System.out.println("🧥 New outfit equipped: " + newOutfit.getName());
//                    }
//                    break;
//                case "2":
//                    Monster newBuddy = chooseBuddy(conn, scanner);
//                    if (newBuddy != null) {
//                        character.setCurrentBuddy(newBuddy);
//                        System.out.println("🐲 New buddy equipped: " + newBuddy.getName());
//                    }
//                    break;
//                case "3":
//                    exitMenu = true;
//                    break;
//                default:
//                    System.out.println("⚠️ Invalid choice. Please try again.");
//            }
//        }
//    }
//
//    /**
//     * Lets the user pick from all unlocked Outfits in the Wardrobe.
//     * @param conn    The database connection (if needed for loading).
//     * @param scanner The Scanner for user input.
//     * @return The chosen unlocked Outfit, or null if none are found.
//     */
//    private static Outfit chooseOutfit(Connection conn, Scanner scanner) {
//        Wardrobe wardrobe = new Wardrobe();  // Adjust constructor if needed
//        List<Outfit> unlockedOutfits = new ArrayList<>();
//
//        // Gather all unlocked Outfits
//        for (Item item : wardrobe.getItems()) {
//            if (item instanceof Outfit && !item.isLocked()) {
//                unlockedOutfits.add((Outfit) item);
//            }
//        }
//        if (unlockedOutfits.isEmpty()) {
//            System.out.println("No unlocked outfits found.");
//            return null;
//        }
//
//        System.out.println("\nAvailable unlocked outfits:");
//        for (int i = 0; i < unlockedOutfits.size(); i++) {
//            System.out.println((i + 1) + ". " + unlockedOutfits.get(i).toString());
//        }
//        System.out.print("Enter the number of the outfit to equip: ");
//
//        String input = scanner.nextLine().trim();
//        int index;
//        try {
//            index = Integer.parseInt(input);
//        } catch (NumberFormatException e) {
//            System.out.println("⚠️ Invalid number. Canceling outfit selection.");
//            return null;
//        }
//
//        if (index < 1 || index > unlockedOutfits.size()) {
//            System.out.println("⚠️ Selection out of range. Canceling outfit selection.");
//            return null;
//        }
//
//        return unlockedOutfits.get(index - 1);
//    }
//
//    /**
//     * Lets the user pick from all unlocked Monsters in the Monsterdex.
//     * @param conn    The database connection (if needed for loading).
//     * @param scanner The Scanner for user input.
//     * @return The chosen unlocked Monster, or null if none are found.
//     */
//    private static Monster chooseBuddy(Connection conn, Scanner scanner) {
//        Monsterdex monsterdex = new Monsterdex();  // Adjust constructor if needed
//        List<Monster> unlockedMonsters = new ArrayList<>();
//
//        // Gather all unlocked Monsters
//        for (Item item : monsterdex.getItems()) {
//            if (item instanceof Monster && !item.isLocked()) {
//                unlockedMonsters.add((Monster) item);
//            }
//        }
//        if (unlockedMonsters.isEmpty()) {
//            System.out.println("No unlocked monsters found.");
//            return null;
//        }
//
//        System.out.println("\nAvailable unlocked monsters:");
//        for (int i = 0; i < unlockedMonsters.size(); i++) {
//            System.out.println((i + 1) + ". " + unlockedMonsters.get(i).toString());
//        }
//        System.out.print("Enter the number of the monster to equip: ");
//
//        String input = scanner.nextLine().trim();
//        int index;
//        try {
//            index = Integer.parseInt(input);
//        } catch (NumberFormatException e) {
//            System.out.println("⚠️ Invalid number. Canceling buddy selection.");
//            return null;
//        }
//
//        if (index < 1 || index > unlockedMonsters.size()) {
//            System.out.println("⚠️ Selection out of range. Canceling buddy selection.");
//            return null;
//        }
//
//        return unlockedMonsters.get(index - 1);
//    }
//}

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

    /**
     * Opens the character customization menu directly.
     * Shows the current outfit and buddy, and offers the user:
     * 1) Change outfit
     * 2) Change buddy
     * 3) Exit
     *
     * @param user      The current User instance.
     * @param scanner   The Scanner for user input.
     * @param character The Character instance being customized.
     */
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


