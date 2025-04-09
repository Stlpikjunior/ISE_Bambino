import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class GameApp {

    private static UserProfile user;

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnector.connect();
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter your name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // flush newline

            user = UserManager.getOrCreateUser(name, age, conn);
            System.out.println("👋 Hello, " + user.getName() + "!");


            boolean running = true;
            while (running) {
                System.out.println("\n🌟 Welcome to the JDM Exercise Game 🌟");
                System.out.println("1. Start Session");
                System.out.println("2. Open Inventory");
                System.out.println("3. Open Shop");
                System.out.println("4. Character Customization");
                System.out.println("5. View Monsterdex");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        int sessionID = SessionManager.startNewSession(user.getUserID(), conn);
                        SessionLogic.runSession(conn, user, sessionID, scanner);
                        scanner.nextLine();
                        break;
                    case "2":
                        Inventory.openInventory(user);
                        break;
                    case "3":
                        Shop shop = new Shop();
                        shop.open(user, scanner);
                        break;
                    case "4":
                        CustomizationManager.openCharacterCustomization(user, conn, scanner);
                        break;
                    case "5":
                        user.getInventory().getMonsterdex().displayMonsterdex();
                        break;
                    case "6":
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




