public abstract class Item {
    protected final String name;
    protected final int price;
    protected final String description;
    protected boolean isLocked;

    public Item(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.isLocked = true;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getDescription() { return description; }
    public boolean isLocked() { return isLocked; }
    public void unlock() { this.isLocked = false; }

    @Override
    public String toString() {
        return name + " - " + price + " coins" + (isLocked ? " (locked)" : " (unlocked)") +
                "\n   ✏️ " + description;
    }
}
