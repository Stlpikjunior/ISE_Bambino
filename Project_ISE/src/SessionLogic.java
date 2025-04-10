import java.sql.*;
import java.util.Scanner;

public class SessionLogic {

    public static void runSession(Connection conn, User user, int sessionID, Scanner scanner, CoinSQL coinSQL) throws SQLException {
        System.out.println("🎮 Starting exercise session...");

        // Prepare SQL statements
        PreparedStatement selectExercises = conn.prepareStatement("SELECT ExerciseID, Name, Description FROM Exercise");
        ResultSet rs = selectExercises.executeQuery();

        PreparedStatement insertSessionExercise = conn.prepareStatement(
                "INSERT INTO Session_Exercise (SessionID, ExerciseID, Completed, Feedback) VALUES (?, ?, ?, ?)");

        int completedCount = 0;

        while (rs.next()) {
            int exerciseID = rs.getInt("ExerciseID");
            String exerciseName = rs.getString("Name");
            String exerciseDescription = rs.getString("Description");

            System.out.println("\n=====================================");
            System.out.println("\n➡️ Exercise: " + exerciseName);
            System.out.println("\n➡️ Description: " + exerciseDescription);
            System.out.println("\nDid you complete it? (1 = yes, 0 = no, type 'skip' to skip this one, or 'end' to finish early): ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("end")) {
                System.out.println("⏭️ Ending session early...");
                break;
            }

            int completed;
            if (input.equalsIgnoreCase("skip")) {
                System.out.println("⏭️ Skipping this exercise (marking as not completed).");
                completed = 0;
            } else {
                try {
                    completed = Integer.parseInt(input);
                    if (completed != 0 && completed != 1) {
                        System.out.println("❌ Invalid input. Marking as not completed.");
                        completed = 0;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid input. Marking as not completed.");
                    completed = 0;
                }
            }

            System.out.print("How tough was the exercise? (1–5): ");
            int feedback = 3;
            try {
                feedback = scanner.nextInt();
                while (feedback < 1 || feedback > 5) {
                    System.out.print("Invalid. Please enter a number between 1 and 5: ");
                    feedback = scanner.nextInt();
                }
            } catch (Exception e) {
                System.out.println("❌ Invalid input. Using default rating 3.");
                scanner.nextLine(); // Clear buffer
            }

            if (completed == 1) completedCount++;

            insertSessionExercise.setInt(1, sessionID);
            insertSessionExercise.setInt(2, exerciseID);
            insertSessionExercise.setInt(3, completed);
            insertSessionExercise.setInt(4, feedback);
            insertSessionExercise.executeUpdate();
        }

        // Update Total_Score
        PreparedStatement updateScore = conn.prepareStatement(
                "UPDATE Session SET Total_Score = ? WHERE SessionID = ?");
        updateScore.setInt(1, completedCount);
        updateScore.setInt(2, sessionID);
        updateScore.executeUpdate();

        System.out.println("\n✅ Session complete! You finished " + completedCount + " out of 14 exercises.");

        user.getInventory().addCoins(500, user.getUserID(), coinSQL);
        System.out.println("\n🏅 You earned " + completedCount + " coins this session!");
        System.out.println("💰 Total coins: " + user.getInventory().getCoins());
    }
}
