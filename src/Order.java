import java.util.*;

/**
 * 
 * @author Conor Ebbs and Rocco Basso
 * 
 */
public class Order {

    /** */
    int customerID;

    /** */
    String lastName;

    /** */
    int orderID;

    /** */
    boolean paid;

    /** */
    HashMap<Item, Integer> items = new HashMap<Item, Integer>();

    /** */
    String orderDate;

    /** */
    String pickupDate;

    /** */
    double total;

    /** */
    double discountUsedOnOrder;

    /** */
    double totalDue;


    /**
     * 
     * @param customerID
     * @param lastName
     * @param orderID
     * @param paid
     * @param orderDate
     * @param pickupDate
     * @param total
     * @param discountUsedOnOrder
     * @param totalDue
     */
    Order(int customerID, String lastName, int orderID, boolean paid,
            String orderDate, String pickupDate, double total,
            double discountUsedOnOrder, double totalDue) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.orderID = orderID;
        this.paid = paid;
        this.orderDate = orderDate;
        this.pickupDate = pickupDate;
        this.total = total;
        this.discountUsedOnOrder = discountUsedOnOrder;
        this.totalDue = totalDue;
    }


    /**
     * gives an advanced view of the order
     */
    public void advancedOrderViewInteraction() {
        Bakery.printHeader("Order - Advanced View");
        System.out.println("\nOrder ID:\t" + this.orderID);
        System.out.println("Order Date:\t" + this.orderDate);
        System.out.println("Ordered by:\t" + "[" + customerID + "] "
                + lastName);
        System.out.println("\nItems in order...\n");

        int longestName = 0;
        String buffer = "";
        for (Item i : this.items.keySet()) {
            if (i.name.length() > longestName) {
                longestName = i.name.length();
            }
        }

        for (int i = 0; i < longestName; i++) {
            buffer = buffer + " ";
        }

        System.out.println("\tID\tItem Name" + buffer
                + "Price (each)\tQuantity\n");
        for (Item i : this.items.keySet()) {
            System.out.println("\t" + i.toString(longestName) + "\t\t"
                    + this.items.get(i));
        }

        System.out.println("\n\tTotal Order Price: " + this.total + "\n");
        System.out.println("\n[1] - View customer linked to this order");
        System.out.println("[2] - Update this Order");
        System.out.println("[3] - Main Menu");
        System.out.print("\n>> ");

        int a = Bakery.readInputAndCheckValidity(3);

        if (a == 1) {
            for (Customer c : Bakery.customers) {
                if (c.customerID == this.customerID) {
                    c.advancedCustomerView();
                }
            }
        }
        else if (a == 2) {
            this.updateOrder();
        }
        else if (a == 3) {
            Bakery.mainMenuInteraction();
        }
    }

    /**
     * interactions that go into updating an order
     */
    public void updateOrder() {
        Bakery.printHeader("Update Order - Order # " + this.orderID);
        System.out.println("What do you want to update?\n");
        System.out.println("[1] - Payment status");
        System.out.println("[2] - Order items");
        System.out.println("[3] - Order date");
        System.out.println("[4] - Pickup date");
        System.out.println("[5] - Total due");
        System.out.println("\n[6] - Main Menu");
        System.out.print("\n>> ");

        int a = Bakery.readInputAndCheckValidity(6);

        if (a == 1) {
            if (this.paid) {
                System.out.println("This order is currently marked as "
                        + "'paid'. Would you like to mark it as unpaid?");
                System.out.println("[1] - Yes");
                System.out.println("[2] - No");
            }
            else {
                System.out.println("This order is currently marked as "
                        + "'unpaid'. Would you like to mark it as paid?");
                System.out.println("[1] - No");
                System.out.println("[2] - Yes");
            }
            System.out.println("\n[3] - Go Back");
            System.out.print("\n[4] - Main Menu\n\n>> ");
            a = Bakery.readInputAndCheckValidity(2);

            if (a == 1) {
                this.paid = false;
            }
            else if (a == 2) {
                this.paid = true;
            }
        }
        else if (a == 2) {
            updateOrderItems();
        }
        else if (a == 3) {
            System.out.print("\nEnter updated Order Date: \n\n>> ");
            Scanner console = new Scanner(System.in);
            this.orderDate = console.next();
        }
        else if (a == 4) {
            System.out.print("\nEnter updated Pickup Date: \n\n>> ");
            Scanner console = new Scanner(System.in);
            this.pickupDate = console.next();
        }
        else if (a == 5) {

        }
        else if (a == 6) {
            Bakery.mainMenuInteraction();
        }
    }

    /**
     * interactions that allow user to update an order
     */
    @SuppressWarnings("unchecked")
    public void updateOrderItems() {
        System.out.println("Would you like to add or remove items from"
                + " this order");

        System.out.println("[1] - Add items");
        System.out.println("[2] - Remove items");
        System.out.println("\n[3] - Go back");
        System.out.println("[4] - Main Menu");

        int a = Bakery.readInputAndCheckValidity(4);

        if (a == 1) {
            
        }
        else if (a == 2) {
            System.out.println("Enter the Item ID of the item that you"
                    + " want to remove?");

            ArrayList<Item> items = (ArrayList<Item>) this.items.keySet();

            a = Bakery.readInputAndCheckValidity(items);

            if (a == 0) {
                Bakery.mainMenuInteraction();
            }
            else {
                for (Item i : this.items.keySet()) {
                    if (a == i.itemID) {
                        this.items.remove(i);
                    }
                }
                System.out.println("Item successfully removed!");
                System.out.println("\n[1] - Update another order");
                System.out.println("[2] - Main Menu");

                a = Bakery.readInputAndCheckValidity(2);

                if (a == 1) {
                    Bakery.updateOrderInteraction();
                }
                else if (a == 2) {
                    Bakery.mainMenuInteraction();
                }
            }
        }
        else if (a == 3) {
            this.updateOrder();
        }
        else if (a == 4) {
            Bakery.mainMenuInteraction();
        }
                
    }

    /**
     * takes all order informations an returns it as a presentable string
     * @return String representation of this Order
     */
    public String toString() {

        return "[" + this.orderID + "]\t" + this.items.size() + "\t\t"
                + this.total + "\t\t" + this.lastName + " "
                + "      \t" + orderDate
                + "   \t" + pickupDate;
    }
}
