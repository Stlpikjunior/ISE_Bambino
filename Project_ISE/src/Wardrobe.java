public class Wardrobe extends ItemCollection<Outfit> {

    @Override
    protected void initializeItems() {
        items.add(new Outfit(1, "Blue", "A friendly, cute monster.", 10, true));
        items.add(new Outfit(2, "Yellow", "A quick and energetic creature.", 15, false));
        items.add(new Outfit(3, "Blue", "A grumpy, lovable beast.", 20, false));
        items.add(new Outfit(4, "Green", "An electrifying monster.", 25, false));
        items.add(new Outfit(5, "Turqoise", "A calm and soothing spirit.", 30, false));
        items.add(new Outfit(6, "Purple", "A fierce and powerful challenger.", 35, false));
        items.add(new Outfit(7, "Pink", "A mysterious figure from the dark.", 40, false));
        items.add(new Outfit(8, "Orange", "Shimmering with magical light.", 45, false));
        items.add(new Outfit(9, "Lime", "A strong and valorous guardian.", 50, false));
        items.add(new Outfit(10, "Danny DeVito", "The legendary and expensive  skin.", 99, false));
    }
}