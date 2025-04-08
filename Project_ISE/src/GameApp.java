import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class GameApp {

    private static int userID;

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnector.connect();
             Scanner scanner = new Scanner(System.in)) {

            // 🔐 User login
            System.out.print("Enter your name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter your age: ");
            int Age = scanner.nextInt();
            scanner.nextLine();
            System.out.println("👋 Hello, " + name + "!");

            boolean running = true;
            while (running) {
                System.out.println("\n🌟 Welcome to the JDM Exercise Game 🌟");
                System.out.println("1. Start Session");
                System.out.println("2. Open Inventory");
                System.out.println("3. Open Shop");
                System.out.println("4. Character Customization");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        int sessionID = SessionManager.startNewSession(userID, conn);
                        SessionLogic.runSession(conn, userID, sessionID, scanner);
                        scanner.nextLine();
                        break;
                    case "2":
                        InventoryManager.openInventory(userID, conn);
                        break;
                    case "3":
                        ShopManager.openShop(userID, conn, scanner);
                        break;
                    case "4":
                        CustomizationManager.openCharacterCustomization(userID, conn, scanner);
                        break;
                    case "5":
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




