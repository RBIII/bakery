import java.util.*;

/**
 * 
 * @author Conor Ebbs and Rocco Basso
 * 
 */
public class Item {

    /**
     * the items id
     */
    int itemID;

    /**
     * the items name
     */
    String name;

    /**
     * the items category
     */
    String category;

    /**
     * the items price
     */
    double price;

    /**
     * Constructor
     * 
     * @param id
     *            The unique identifier for this Item
     * @param name
     *            The name of this Item
     * @param category
     *            The category of this Item
     * @param price
     *            The price of this Item
     */
    Item(int id, String name, String category, double price) {
        this.itemID = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    /**
     * gets all item information and changes the to a presentable string
     */
    public String toString() {
        return "[" + this.itemID + "]\t" + this.name + "\t\t" + this.price;
    }

    /**
     * gets all item information and changes to a string with spacing based 
     * on the longest name
     * @param longestName
     *      the longest item name to base the spacing off of
     * @return String
     *      all item information appropriately spaced
     */
    public String toString(int longestName) {
        int diff = longestName - this.name.length();
        String buffer = "";
        for (int i = 0; i < diff; i++) {
            buffer = buffer + " ";
        }
        return "[" + this.itemID + "]\t" + this.name + buffer + "\t\t"
                + this.price;
    }

    /**
     * interactions involved in updating an item
     */
    public void updateItem() {
        Bakery.printHeader("Inventory - Updating Item");
        Scanner console = new Scanner(System.in);

        System.out.println("\n" + this.toString() + "\n");
        System.out.print("\nUpdated item name: >> ");
        String newName = console.next();

        System.out.print("\nUpdated item category: >>");
        String newCategory = console.next();

        System.out.print("\nUpdated item price: >> ");
        while (!console.hasNextDouble()) {
            System.out.print("\nInvalid input. Price must be a double"
                    + " (ex: 5.75)");
            console.next();
        }
        double newPrice = console.nextDouble();

        this.name = newName;
        this.category = newCategory;
        this.price = newPrice;

        System.out.println("Item successfully updated!");
        System.out.println("\n[1] - Update another item");
        System.out.println("[2] - Inventory Menu");
        System.out.println("[3] - Main Menu");
        
        int a = Bakery.readInputAndCheckValidity(3);
        
        if (a == 1) {
            Bakery.updateItemInteraction();
        }
        else if (a == 2) {
            Bakery.inventoryMenuInteraction();
        }
        else if (a == 3) {
            Bakery.mainMenuInteraction();
        }
    }

}
