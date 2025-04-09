
//import java.sql.Connection;
//import java.util.Scanner;
//
//public class CustomizationManager {
//    public static void openCharacterCustomization(User user, Connection conn, Scanner scanner) {
//        System.out.println("🎨 Character customization coming soon!");
//    }
//}


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Character {
    private Outfit currentOutfit;
    private Monster currentBuddy;

    // Getters and setters
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
     * Opens the character customization interface so the user can select
     * an unlocked outfit (to wear) and a buddy (from unlocked monsters).
     *
     * @param user The current user profile.
     * @param conn The database connection (reserved for future use).
     * @param scanner The Scanner for user input.
     * @return A Character instance with the chosen outfit and buddy.
     */
    public static Character openCharacterCustomization(UserProfile user, Connection conn, Scanner scanner) {
        System.out.println("🎨 Welcome to Character Customization!");

        // Retrieve unlocked outfits from the Wardrobe.
        // (Wardrobe is similar in structure to Monsterdex.)
        Wardrobe wardrobe = new Wardrobe();
        List<Outfit> unlockedOutfits = new ArrayList<>();
        for (Outfit outfit : wardrobe.getOutfits()) {
            if (!outfit.isLocked()) {
                unlockedOutfits.add(outfit);
            }
        }
        // Ensure at least one outfit is available.
        if (unlockedOutfits.isEmpty()) {
            System.out.println("No unlocked outfits found. Defaulting to the first outfit in the wardrobe.");
            unlockedOutfits.add(wardrobe.getOutfits().get(0));
        }
        System.out.println("\nChoose an outfit to wear:");
        for (int i = 0; i < unlockedOutfits.size(); i++) {
            System.out.println((i + 1) + ". " + unlockedOutfits.get(i));
        }
        System.out.print("Enter the number of your chosen outfit: ");
        int outfitChoice = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        // Retrieve unlocked monsters from Monsterdex.
        Monsterdex monsterdex = new Monsterdex();
        List<Monster> unlockedMonsters = new ArrayList<>();
        for (Monster monster : monsterdex.getMonsters()) {
            if (!monster.isLocked()) {
                unlockedMonsters.add(monster);
            }
        }
        // Ensure at least one monster is available.
        if (unlockedMonsters.isEmpty()) {
            System.out.println("No unlocked monsters found. Defaulting to the first monster.");
            unlockedMonsters.add(monsterdex.getMonsters().get(0));
        }
        System.out.println("\nChoose a buddy from your unlocked monsters:");
        for (int i = 0; i < unlockedMonsters.size(); i++) {
            System.out.println((i + 1) + ". " + unlockedMonsters.get(i));
        }
        System.out.print("Enter the number of your chosen buddy: ");
        int buddyChoice = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        // Create the Character with the selected options.
        Character character = new Character();
        character.setCurrentOutfit(unlockedOutfits.get(outfitChoice - 1));
        character.setCurrentBuddy(unlockedMonsters.get(buddyChoice - 1));

        System.out.println("\n✅ Customization complete!");
        System.out.println("You are now wearing: " + character.getCurrentOutfit().getName());
        System.out.println("Your buddy is: " + character.getCurrentBuddy().getName());

        return character;
    }

    // Additional character customization methods can be added here later.
}
