public class Wardrobe extends ItemCollection<Outfit> {

    @Override
    protected void initializeItems() {
        items.add(new Outfit(1, "Blue🔵", "Cool and calm like a clear summer sky.", 10, true));
        items.add(new Outfit(2, "Yellow🟡", "Bright and cheerful, like a burst of sunshine.", 15, false));
        items.add(new Outfit(3, "Red🔴", "Bold and dynamic, igniting passion and energy.", 20, false));
        items.add(new Outfit(4, "Green🟢", "Fresh and natural, reminiscent of a verdant forest.", 25, false));
        items.add(new Outfit(7, "Pink🩷", "Sweet, sassy, and impossible to ignore.", 30, false));
        items.add(new Outfit(6, "Purple🟣", "Royal, bold, and a little bit magical.", 35, false));
        items.add(new Outfit(6, "Silver🪙", "Sleek and sophisticated with a shimmering metallic touch.", 40, false));
        items.add(new Outfit(7, "Gold👑", "Luxurious and radiant, fit for royalty.", 45, false));
        items.add(new Outfit(9, "Diamond💎", "Sparkling and exclusive, the pinnacle of style.", 50, false));
        items.add(new Outfit(10, "Danny DeVito👘", "The legendary and rare skin. Enough said.", 200, false));
    }
}