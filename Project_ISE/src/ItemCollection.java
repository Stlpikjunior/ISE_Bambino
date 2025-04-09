import java.util.ArrayList;
import java.util.List;

public abstract class ItemCollection<T extends Item> {
    protected List<T> items;

    public ItemCollection() {
        this.items = new ArrayList<>();
        initializeItems();  // each subclass must implement this
    }

    public List<T> getItems() {
        return items;
    }

    public void addItem(T item) {
        items.add(item);
    }

    public void displayCollection() {
        System.out.println("---- " + this.getClass().getSimpleName() + " ----");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("------------------------------");
    }

    protected abstract void initializeItems();
}
