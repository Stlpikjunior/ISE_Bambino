public class Wardrobe extends ItemCollection<Outfit> {

    @Override
    protected void initializeItems() {
        items.add(new Outfit(1, "Blue", "Cool and calm like the deep sea breeze.", 10, true));
        items.add(new Outfit(2, "Yellow", "Bright, cheerful, and full of sunshine.", 15, false));
        items.add(new Outfit(3, "Blue", "Moody and mysterious — a different shade of blue.", 20, false));
        items.add(new Outfit(4, "Green", "Fresh, lively, and ready for adventure.", 25, false));
        items.add(new Outfit(5, "Turquoise", "Serene and smooth like a tropical lagoon.", 30, false));
        items.add(new Outfit(6, "Purple", "Royal, bold, and a little bit magical.", 35, false));
        items.add(new Outfit(7, "Pink", "Sweet, sassy, and impossible to ignore.", 40, false));
        items.add(new Outfit(8, "Orange", "Vibrant and wild — never a dull moment.", 45, false));
        items.add(new Outfit(9, "Lime", "Zesty and electric with a tangy twist.", 50, false));
        items.add(new Outfit(10, "Danny DeVito", "The legendary and expensive skin. Enough said.", 99, false));
    }
}