// ORIGINAL CODE

//import java.util.ArrayList;
//import java.util.List;
//
//public abstract class ItemCollection<T extends Item> {
//    protected List<T> items;
//
//    public ItemCollection() {
//        this.items = new ArrayList<>();
//        initializeItems();  // each subclass must implement this
//    }
//
//    public List<T> getItems() {
//        return items;
//    }
//
//    public void addItem(T item) {
//        items.add(item);
//    }
//
//    public void displayCollection() {
//        System.out.println("---- " + this.getClass().getSimpleName() + " ----");
//        for (Item item : items) {
//            System.out.println(item);
//        }
//        System.out.println("------------------------------");
//    }
//
//    protected abstract void initializeItems();
//}



// Code chat

//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//
//public abstract class ItemCollection<T extends Item> {
//    protected List<T> items;
//
//    public ItemCollection() {
//        this.items = new ArrayList<>();
//        initializeItems();  // each subclass must implement this
//    }
//
//    // New constructor that accepts a Connection
//    public ItemCollection(Connection conn) {
//        this();
//        loadItemsFromDatabase(conn);  // each subclass may implement this to load items from DB
//    }
//
//    public List<T> getItems() {
//        return items;
//    }
//
//    public void addItem(T item) {
//        items.add(item);
//    }
//
//    public void displayCollection() {
//        System.out.println("---- " + this.getClass().getSimpleName() + " ----");
//        for (Item item : items) {
//            System.out.println(item);
//        }
//        System.out.println("------------------------------");
//    }
//
//    // Each subclass must implement its own initialization logic
//    protected abstract void initializeItems();
//
//    // Subclasses can implement this method to load items from a database.
//    // If not needed, the implementation can be left empty.
//    protected abstract void loadItemsFromDatabase(Connection conn);
//}

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

    // Subclasses must define how they initialize their items
    protected abstract void initializeItems();
}
