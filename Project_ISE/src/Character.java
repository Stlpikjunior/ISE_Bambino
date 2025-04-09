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
     * Opens the character customization interface. This allows the user to choose:
     * 1. An unlocked outfit (to "wear") from the Wardrobe.
     * 2. A buddy from unlocked monsters in the Monsterdex.
     *
     * @param user The current User instance.
     * @param conn The database connection (reserved for future integration).
     * @param scanner The Scanner for user input.
     * @return A Character instance with the selected outfit and buddy.
     */
    public static Character openCharacterCustomization(User user, Connection conn, Scanner scanner) {
        System.out.println("🎨 Welcome to Character Customization, " + user.getName() + "!");

        // Retrieve unlocked outfits from the Wardrobe.
        Wardrobe wardrobe = new Wardrobe();  // Assuming Wardrobe is implemented similarly to Monsterdex.
        List<Outfit> unlockedOutfits = new ArrayList<>();
        for (Outfit outfit : wardrobe.getItems()) {
            if (!outfit.isLocked()) {
                unlockedOutfits.add(outfit);
            }
        }
        if (unlockedOutfits.isEmpty()) {
            System.out.println("No unlocked outfits found. Defaulting to the first outfit in the wardrobe.");
            unlockedOutfits.add(wardrobe.getItems().get(0));
        }
        System.out.println("\nChoose an outfit to wear:");
        for (int i = 0; i < unlockedOutfits.size(); i++) {
            System.out.println((i + 1) + ". " + unlockedOutfits.get(i));
        }
        System.out.print("Enter the number of your chosen outfit: ");
        int outfitChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Retrieve unlocked monsters (buddies) from Monsterdex.
        Monsterdex monsterdex = new Monsterdex();
        List<Monster> unlockedMonsters = new ArrayList<>();
        for (Monster monster : monsterdex.getItems()) {
            if (!monster.isLocked()) {
                unlockedMonsters.add(monster);
            }
        }
        if (unlockedMonsters.isEmpty()) {
            System.out.println("No unlocked monsters found. Defaulting to the first monster.");
            unlockedMonsters.add(monsterdex.getItems().get(0));
        }
        System.out.println("\nChoose a buddy from your unlocked monsters:");
        for (int i = 0; i < unlockedMonsters.size(); i++) {
            System.out.println((i + 1) + ". " + unlockedMonsters.get(i));
        }
        System.out.print("Enter the number of your chosen buddy: ");
        int buddyChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Create a Character instance and assign the selected outfit and buddy.
        Character character = new Character();
        character.setCurrentOutfit(unlockedOutfits.get(outfitChoice - 1));
        character.setCurrentBuddy(unlockedMonsters.get(buddyChoice - 1));

        System.out.println("\n✅ Customization complete!");
        System.out.println("You are now wearing: " + character.getCurrentOutfit().getName());
        System.out.println("Your buddy is: " + character.getCurrentBuddy().getName());

        return character;
    }
}
