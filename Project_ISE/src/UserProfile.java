public class UserProfile {
    private final int userID;
    private final String name;
    private final int age;
    private final Inventory inventory;

    public UserProfile(int userID, String name, int age) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.inventory = new Inventory();
    }

    // Getters
    public int getUserID() { return userID; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public Inventory getInventory() { return inventory; }
}

