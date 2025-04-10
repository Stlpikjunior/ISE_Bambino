public class Monsterdex extends ItemCollection<Monster> {

    @Override
    protected void initializeItems() {
        items.add(new Monster(1, "Fluffy", "A friendly, cute monster.", 10, true));
        items.add(new Monster(2, "Spark", "A quick and energetic creature.", 15, false));
        items.add(new Monster(3, "Grumble", "A grumpy, lovable beast.", 20, false));
        items.add(new Monster(4, "Zappy", "An electrifying monster.", 25, false));
        items.add(new Monster(5, "Breezy", "A calm and soothing spirit.", 30, false));
        items.add(new Monster(6, "Rumble", "A fierce and powerful challenger.", 35, false));
        items.add(new Monster(7, "Shadow", "A mysterious figure from the dark.", 40, false));
        items.add(new Monster(8, "Glitter", "Shimmering with magical light.", 45, false));
        items.add(new Monster(9, "Mighty", "A strong and valorous guardian.", 50, false));
        items.add(new Monster(10, "Danny DeVito", "The legendary and expensive monster skin.", 99, false));
    }
}

