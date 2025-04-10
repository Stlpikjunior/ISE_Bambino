import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

public class GameApp {

    private static User user;
    private static Character character;

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnector.connect();
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter your (User)name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // flush newline

            user = UserManager.getOrCreateUser(name, age, conn);
            System.out.println("👋 Hello, " + user.getName() + "!");

            CoinSQL coinSQL = new CoinSQL(conn);
            int coins = coinSQL.loadCoins(user.getUserID());
            user.getInventory().setCoins(coins);

            InventorySQL inventorySQL = new InventorySQL(conn);
            List<String> savedItemNames = inventorySQL.loadUserItems(user.getUserID());

            for (Monster m : user.getInventory().getMonsterdex().getItems()) {
                if (!m.isLocked() && !savedItemNames.contains(m.getName())) {
                    // Default unlocked monster that wasn't in DB — save it
                    inventorySQL.saveItem(user.getUserID(), m.getName(), "Monster");
                    user.getInventory().addItem(m, user.getUserID(), inventorySQL); // optional: also add if not already
                } else if (savedItemNames.contains(m.getName())) {
                    m.unlock();
                    user.getInventory().addItem(m, user.getUserID(), inventorySQL);
                }
            }

            for (Outfit o : user.getInventory().getWardrobe().getItems()) {
                if (!o.isLocked() && !savedItemNames.contains(o.getName())) {
                    inventorySQL.saveItem(user.getUserID(), o.getName(), "Outfit");
                    user.getInventory().addItem(o, user.getUserID(), inventorySQL);
                } else if (savedItemNames.contains(o.getName())) {
                    o.unlock();
                    user.getInventory().addItem(o, user.getUserID(), inventorySQL);
                }
            }

            boolean running = true;
            while (running) {
                System.out.println("\n🌟 Welcome to the JDM Exercise Game 🌟");
                System.out.println("1. Start Session");
                System.out.println("2. Open Inventory");
                System.out.println("3. Open Shop");
                System.out.println("4. Character Customization");
                System.out.println("5. View Monsterdex");
                System.out.println("6. View Wardrobe");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        int sessionID = SessionManager.startNewSession(user.getUserID(), conn);
                        SessionLogic.runSession(conn, user, sessionID, scanner, coinSQL);
                        scanner.nextLine();
                        break;
                    case "2":
                        Inventory.openInventory(user);
                        break;
                    case "3":
                        Shop shop = new Shop();
                        shop.open(user, scanner, inventorySQL, coinSQL);
                        break;
                    case "4":
                        if (character == null) {
                            character = new Character();
                        }
                        Character.openCharacterCustomization(user, scanner, character);
                        break;
                    case "5":
                        user.getInventory().getMonsterdex().displayCollection();
                        break;
                    case "6":
                        user.getInventory().getWardrobe().displayCollection();
                        break;
                    case "7":
                        running = false;
                        System.out.println("👋 Goodbye!");
                        break;
                    default:
                        System.out.println("❌ Invalid choice. Try again.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




