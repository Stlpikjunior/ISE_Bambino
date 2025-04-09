public class Outfit extends Item {
    private final int id;

    public Outfit(int id, String name, String description, int price, boolean unlocked) {
        super(name, price, description);
        this.id = id;
        this.isLocked = !unlocked;
    }

    public int getId() { return id; }

    @Override
    public String toString() {
        return "#" + id + " " + name + " - " + (isLocked ? "🔒 Locked" : "\uD83E\uDDE5 Unlocked") + " | " + description;
    }
}
