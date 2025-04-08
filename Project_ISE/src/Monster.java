public class Monster extends Item {
    private final int id;

    public Monster(int id, String name, String description, int price, boolean unlocked) {
        super(name, price, description);
        this.id = id;
        this.isLocked = !unlocked;
    }

    public int getId() { return id; }

    @Override
    public String toString() {
        return "#" + id + " " + name + " - " + (isLocked ? "🔒 Locked" : "🔓 Unlocked") + " | " + description;
    }
}


