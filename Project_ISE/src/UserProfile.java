public class UserProfile {
    private final int userID;
    private final String name;
    private final int age;
    private int coins;

    public UserProfile(int userID, String name, int age) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.coins = 0; // Default starting amount
    }

    // Getters
    public int getUserID() { return userID; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getCoins() { return coins; }

    // Coin management
    public void addCoins(int amount) { this.coins += amount; }
    public void spendCoins(int amount) { this.coins -= amount; }
}

