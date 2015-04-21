import java.io.*;
import java.util.*;

/**
 * 
 * @author Conor Ebbs and Rocco Basso
 * 
 */
public class Bakery {

    /** */
    static ArrayList<Customer> customers = new ArrayList<Customer>();

    /** */
    static ArrayList<Order> orders = new ArrayList<Order>();

    /** */
    static ArrayList<Item> inventory = new ArrayList<Item>();

    /**
     * Constructor to make an empty Bakery
     */
    Bakery() {
        customers.clear();
        orders.clear();
        inventory.clear();
    }

    /**
     * Main method to run the Bakery software
     * 
     * @param args
     *            Arguments from the command line
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length < 2) {
            System.err
            .println("Not enough text files specified. Must specify"
                            + " exactly two text files (one containing the "
                            + "inventory and one containing the orders).");
        }
        else if (args.length > 2) {
            System.err
            .println("Too many text files specified. Must specify"
                    + " exactly two text files (one containing the "
                    + " inventory and one containing the orders).");
        }
        else {
            String f1 = args[0].toString();
            String f2 = args[1].toString();

            initialize(f1, f2);

            mainMenuInteraction();
        }
    }

    /**
     * imports all information from the input file and uses it to populate
     * the appropriate fields (customers, orders, inventory)
     * @param f1 
     *      the first file
     * @param f2
     *      the second file
     */
    static void initialize(String f1, String f2) throws FileNotFoundException {

        Scanner itemInput;
        Scanner orderInput;

        if (f1.equals("bakeryItems.txt") && f2.equals("orders.txt")) {
            File itemFile = new File(f1);
            File orderFile = new File(f2);
            itemInput = new Scanner(itemFile);
            orderInput = new Scanner(orderFile);
            constructInventory(itemInput);
            constructOrdersAndCustomers(orderInput);
        }
        else if (f1.equals("orders.txt") && f2.equals("bakeryItems.txt")) {
            File itemFile = new File(f2);
            File orderFile = new File(f1);
            orderInput = new Scanner(orderFile);
            itemInput = new Scanner(itemFile);
            constructInventory(itemInput);
            constructOrdersAndCustomers(orderInput);
        }
        else {
            System.err.println("The incorrect files were specified. "
                    + "This program requires two files: "
                    + "'bakeryItems.txt' and 'orders.txt'");
        }
    }

    /**
     * prints the given string with a divider and
     * several new lines below it for readability
     * @param title
     *      the input string
     */
    public static void printHeader(String title) {

        System.out.println("\n---------------------------------------------");
        System.out.println("\n" + title + "...\n");
    }

    /**
     * Method to inform the user that the int they gave
     * was incorrect and the range that the int can be in
     * @param n
     *      the max value that can be given as an input
     * @return String 
     *      tells that the input is invalid and the acceptable range
     */
    public static String invalidInput(int n) {
        return "\nInvalid input. Input a number from 1 - " + n
                + ". >> ";
    }

    /**
     * used when an ID is required but an invalid ID
     * is given (either wrong type or if no such ID exists)
     * @return String
     *      tells that the input is invalid and give options
     */
    public static String invalidInputErrorIDOrZero() {
        return "\nInvalid input. Input a valid"
                + " ID, or 0 to return to the Menu. >> ";
    }

    /**
     * 
     * @param n
     * @return
     */
    public static String invalidInputErrorID(int n) {
        return "\nInvalid input. Input a valid ID or a number " + "from 1 - "
                + n + ". >> ";
    }

    /**
     * checks to see if the number given is in the realm
     * of available options else forces a different input
     * @param numberOfOptions
     *      the max number that can be input
     * @return int
     *      the number given, if it is returned it is valid
     */
    public static int readInputAndCheckValidity(int numberOfOptions) {

        Scanner console = new Scanner(System.in);

        boolean validInput = false;
        int a = -1;

        while (!validInput) {

            if (!console.hasNextInt()) {
                System.out.print(invalidInput(numberOfOptions));
                console.next();
                continue;
            }

            a = console.nextInt();

            if (a < 1 || a > numberOfOptions) {
                System.out.print(invalidInput(numberOfOptions));
            }
            else {
                validInput = true;
            }
        }

        return a;
    }


    /**
     * checks the input, finds it's type by the first element in the list
     * and creates a list of the ids of that element and checks to see if
     * the users input is in the list of ids, if so returns it 
     * @param list
     *      the list of Objects specified at runTime
     * @return int
     *      the users input, if returned must be valid
     */
    @SuppressWarnings("unchecked")
    public static <T> int readInputAndCheckValidity(ArrayList<T> list) {


        ArrayList<Integer> ids = new ArrayList<Integer>();

        T t = list.get(0);
        if (t instanceof Order) {
            for (Order o : (ArrayList<Order>) list) {
                ids.add(o.orderID);
            }
        }
        if (t instanceof Customer) {
            for (Customer c : (ArrayList<Customer>) list) {
                ids.add(c.customerID);
            }
        }
        if (t instanceof Item) {
            for (Item i : (ArrayList<Item>) list) {
                ids.add(i.itemID);
            }
        }

        Scanner console = new Scanner(System.in);

        boolean validInput = false;
        int a = -1;

        while (!validInput) {

            if (!console.hasNextInt()) {
                System.out.print(invalidInputErrorID(2));
                console.next();
                continue;
            }

            a = console.nextInt();

            if (!ids.contains(a) && a != 0) {
                System.out.print(invalidInputErrorID(2));
            }
            else {
                validInput = true;
            }
        }

        return a;
    }

    /**
     * checks the input, finds it's type by the first element in the list
     * and creates a list of the ids of that element and checks to see if
     * the users input is in the list of ids, if so returns it 
     * @param list
     *      the list of Objects specified at runTime
     * @param n
     *      the maximum valid input
     * @return int
     *      the users input, if returned must be valid
     */
    @SuppressWarnings("unchecked")
    public static <T> int readInputAndCheckValidity(int n, ArrayList<T> list) {

        ArrayList<Integer> ids = new ArrayList<Integer>();

        T t = list.get(0);
        if (t instanceof Order) {
            for (Order o : (ArrayList<Order>) list) {
                ids.add(o.orderID);
            }
        }
        if (t instanceof Customer) {
            for (Customer c : (ArrayList<Customer>) list) {
                ids.add(c.customerID);
            }
        }
        if (t instanceof Item) {
            for (Item i : (ArrayList<Item>) list) {
                ids.add(i.itemID);
            }
        }

        Scanner console = new Scanner(System.in);

        boolean validInput = false;
        int a = -1;

        while (!validInput) {

            if (!console.hasNextInt()) {
                System.out.print(invalidInputErrorID(2));
                console.next();
                continue;
            }

            a = console.nextInt();

            if (!ids.contains(a) && (a < 1 || a > n)) {
                System.out.print(invalidInputErrorID(2));
            }
            else {
                validInput = true;
            }
        }

        return a;
    }

    /**
     * takes the information stored from the input files along with any other
     * customers/orders/items that were added and prints it to a txt file
     */
    public static void writeToFiles() {
        try {
            PrintWriter orderWriter = new PrintWriter(new BufferedWriter(
                    new FileWriter("orders.txt")));
            orderWriter.write("CustomerID\t");
            orderWriter.write("LastName\t");
            orderWriter.write("Address\t");
            orderWriter.write("City\t");
            orderWriter.write("State\t");
            orderWriter.write("ZipCode\t");
            orderWriter.write("OrderID\t");
            orderWriter.write("Paid?\t");
            orderWriter.write("OrderDate\t");
            orderWriter.write("PickupDate\t");
            orderWriter.write("BakeryItemID\t");
            orderWriter.write("BakeryItemName\t");
            orderWriter.write("BakeryItemCategory\t");
            orderWriter.write("Quantity\t");
            orderWriter.write("Price\t");
            orderWriter.write("Total\t");
            orderWriter.write("DiscountUsedOnOrder\t");
            orderWriter.write("TotalDue\t");
            orderWriter.write("AvailableDiscount\t");
            orderWriter.write("CurrentLoyalty\n");
            for (Customer c : customers) {
                for (Order o : c.customerOrders) {
                    for (Item i : o.items.keySet()) {
                        orderWriter.write(c.customerID + "\t");
                        orderWriter.write(c.lastName + "\t");
                        orderWriter.write(c.address + "\t");
                        orderWriter.write(c.city + "\t");
                        orderWriter.write(c.state + "\t");
                        orderWriter.write(c.zipcode + "\t");
                        orderWriter.write(o.orderID + "\t");

                        String paidString = "";
                        if (o.paid) {
                            paidString = "Yes";
                        }
                        else {
                            paidString = "No";
                        }
                        orderWriter.write(paidString + "\t");

                        orderWriter.write(o.orderDate + "\t");
                        orderWriter.write(o.pickupDate + "\t");
                        orderWriter.write(i.itemID + "\t");
                        orderWriter.write(i.name + "\t");
                        orderWriter.write(i.category + "\t");
                        orderWriter.write(o.items.get(i) + "\t");
                        orderWriter.write(i.price + "\t");
                        orderWriter.write(o.total + "\t");
                        orderWriter.write(o.discountUsedOnOrder + "\t");
                        orderWriter.write(o.totalDue + "\t");
                        orderWriter.write(c.availableBalance + "\t");
                        orderWriter.write(c.currentLoyalty + "\n");
                    }
                }
            }
            orderWriter.close();

            PrintWriter itemWriter = new PrintWriter(new BufferedWriter(
                    new FileWriter("bakeryItems.txt")));
            itemWriter.write("ID\t");
            itemWriter.write("Item Name\t");
            itemWriter.write("Item Category\t");
            itemWriter.write("Item Price\n");

            for (Item i : inventory) {
                itemWriter.write(i.itemID + "\t");
                itemWriter.write(i.name + "\t");
                itemWriter.write(i.category + "\t");
                itemWriter.write(i.price + "\n");
            }

            itemWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * the central directory of the Bakery, all Bakery options are reachable 
     * from this point including customer info/add, order info/add, item
     * info/add, searching and writing to files
     */
    public static void mainMenuInteraction() {

        printHeader("Welcome to the Bakery 15 Application!");
        System.out.println("What would you like to do? (Enter one of the"
                + " numbers below to select an option)\n");
        System.out.println("[1] - Customers");
        System.out.println("[2] - Orders");
        System.out.println("[3] - Inventory");

        System.out.println("\n[4] - Search\n");
        System.out.println("[5] - Close Program and write to files");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(5);

        if (a == 1) {
            customerMenuInteraction();
        }
        else if (a == 2) {
            orderInteraction();
        }
        else if (a == 3) {
            inventoryMenuInteraction();
        }
        else if (a == 4) {
            searchInteraction();
        }
        else if (a == 5) {
            closeProgramAndWriteToFiles();
        }
    }

    /**
     * used at the end of the day to stop the system and record
     * all the customers/orders/items information, from that day and prior,
     * in two txt files
     */
    static void closeProgramAndWriteToFiles() {
        printHeader("Are you sure you want to exit?");
        System.out.println("\n[1] - Yes");
        System.out.println("[2] - No. Take me back to the Main Menu");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(2);

        if (a == 1) {
            for (Customer c : customers) {
                if (c.customerOrders.size() == 0) {
                    printHeader("Error: Customer has no orders!");
                    System.out.println("\nCustomer \"" + c.lastName
                            + "\" does not have any"
                            + " orders. If you do not add an order to this\n"
                            + "customer, they will be deleted. Do you wish "
                            + "to add an order?");
                    System.out.println("\n[1] - Yes");
                    System.out.println("[2] - No, delete this customer\n");
                    System.out.print(">> ");

                    a = readInputAndCheckValidity(2);
                    if (a == 1) {
                        addOrderInteraction(c.customerID);
                        writeToFiles();
                        // writeItemsToFile();
                    }
                    else if (a == 2) {
                        writeToFiles();
                        // writeItemsToFile();
                    }
                }
            }
        }
        else if (a == 2) {
            mainMenuInteraction();
        }

    }

    /**
     * all available customer functions including view, add and update
     */
    private static void customerMenuInteraction() {

        Bakery.printHeader("Customer Information");
        System.out.println("[1] - View All Customers");
        System.out.println("[2] - Add a Customer ");
        System.out.println("[3] - Update Customer Information");
        System.out.println("\n[4] - Search");
        System.out.println("[5] - Main Menu");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(5);

        if (a == 1) {
            viewAllCustomersInteraction();
        }
        else if (a == 2) {
            addCustomerInteraction();
        }
        else if (a == 3) {
            updateCustomerInteraction();
        }
        else if (a == 4) {
            searchInteraction();
        }
        else if (a == 5) {
            mainMenuInteraction();
        }
    }

    /**
     * shows all customers with required info and gives
     * other options like returning to main or updating
     */
    static void viewAllCustomersInteraction() {
        printHeader("Customers - View All Customers");
        ArrayList<Integer> customerIDs = new ArrayList<Integer>();

        System.out.println("ID\tLast Name   \tAddress\t\t\tCity\t\tState"
                + "\tZipcode\n");

        int longestName = 0;
        int longestAddress = 0;

        for (Customer c : customers) {
            /** */
            if (c.lastName.length() > longestName) {
                longestName = c.lastName.length();
            }
            if (c.address.length() > longestAddress) {
                longestAddress = c.address.length();
            }

            /** If the list of Customer IDs doesnt contain this CID, add it */
            if (!customerIDs.contains(c.customerID)) {
                customerIDs.add(c.customerID);
            }
        }
        for (Customer c : customers) {
            System.out.println(c.toString(longestName, longestAddress));
        }

        System.out.println("\n\n[Customer ID] - View Additional Info / "
                + "Update Customer");
        System.out.println("[0] - Main Menu");
        System.out.print("\n>> ");
        Scanner console = new Scanner(System.in);

        int a = readInputAndCheckValidity(customers);

        if (a == 0) {
            mainMenuInteraction();
        }
        else {
            for (Customer c : customers) {
                if (c.customerID == a) {
                    c.advancedCustomerView();
                }
            }
        }
    }

    /**
     * interactions for updating customers
     */
    static void updateCustomerInteraction() {
        printHeader("Customers - Update Customer");
        System.out.println("\nDo you know the last name or the customer ID"
                + " number of the customer that you want to update?\n");
        System.out.println("[1] - Yes");
        System.out.println("[2] - No, let me view all customers\n");
        System.out.println("[3] - Main Menu");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(3);

        if (a == 1) {

            System.out.println("\nEnter the ID of the customer to update: ");
            System.out.print("\n>> ");

            a = readInputAndCheckValidity(customers);

            for (Customer c : customers) {
                if (a == c.customerID) {
                    c.updateCustomerInfo();
                }
            }
        }
        else if (a == 2) {
            viewAllCustomersInteraction();
        }
        else if (a == 3) {
            mainMenuInteraction();
        }
        else {

        }
    }

    /**
     * checks to see if the given int is contained in the list of ids
     * @param id
     *      the id we're checking for
     * @param list
     *      the list we're checking in, type is found at runtime
     * @return boolean
     *      telling if the id is contained in the list
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean idIsNotContainedIn(int id,
            ArrayList<T> list) {

        ArrayList<Integer> ids = new ArrayList<Integer>();

        T t = list.get(0);

        if (t instanceof Order) {
            for (Order o : (ArrayList<Order>) list) {
                ids.add(o.orderID);
            }
        }
        else if (t instanceof Item) {
            for (Item i : (ArrayList<Item>) list) {
                ids.add(i.itemID);
            }
        }
        else if (t instanceof Customer) {
            for (Customer c : (ArrayList<Customer>) list) {
                ids.add(c.customerID);
            }
        }
        return !ids.contains(id);
    }

    /**
     * interactions for adding a customer
     */
    static void addCustomerInteraction() {
        printHeader("Add new customer");

        System.out.print("\nLast name: >> ");
        Scanner console = new Scanner(System.in);
        console.useDelimiter(System.lineSeparator());
        String lastName = console.next();

        System.out.print("\nAddress: >> ");
        String address = console.next();

        System.out.print("\nCity: >> ");
        String city = console.next();

        System.out.print("\nState (two letter abbreviation): >> ");
        String state = console.next();

        System.out.print("\nZipcode: >> ");
        String zipcode = console.next();

        System.out.print("\nDoes the Customer have a loyalty card?");
        System.out.println("\n\n[1] - Yes");
        System.out.print("[2] - No\n\n>> ");
        System.out.println();

        int a = readInputAndCheckValidity(2);
        boolean hasLoyaltyCard = false;
        if (a == 1) {
            hasLoyaltyCard = true;
        }

        int highestCustomerID = 0;
        for (Customer c : customers) {
            if (c.customerID > highestCustomerID) {
                highestCustomerID = c.customerID;
            }
        }

        Customer newC = new Customer(highestCustomerID + 1, lastName,
                address, city, state, zipcode, hasLoyaltyCard, 0.0, 0.0);

        customers.add(newC);

        System.out.println(newC.toString());

        System.out.println("\n[1] - Add another customer");
        System.out.println("[2] - Customer Menu");
        System.out.println("[3] - Main Menu");
        System.out.print("\n>> ");

        a = readInputAndCheckValidity(3);


        if (a == 1) {
            addCustomerInteraction();
        }
        else if (a == 2) {
            customerMenuInteraction();
        }
        else if (a == 3) {
            mainMenuInteraction();
        }
    }

    /**
     * general interactions for orders including view, view unpaid,
     * adding an order and updating an order
     */
    private static void orderInteraction() {
        printHeader("Orders Menu");
        System.out.println("\n[1] - View all orders");
        System.out.println("[2] - View all unpaid orders");
        System.out.println("[3] - Add a new order"); // should output a receipt
        System.out.println("[4] - Update an order");
        System.out.println("\n[5] - Search");
        System.out.println("[6] - Main Menu\n");

        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(6);

        if (a == 1) {
            viewAllOrdersInteraction(true);
        }
        else if (a == 2) {
            viewAllOrdersInteraction(false);
        }
        else if (a == 3) {
            addOrderInteraction(-1);
        }
        else if (a == 4) {
            updateOrderInteraction();
        }
        else if (a == 5) {
            searchInteraction();
        }
        else if (a == 6) {
            mainMenuInteraction();
        }
    }

    /**
     * interactions involved in adding an order
     */
    @SuppressWarnings({ "resource" })
    public static void addOrderInteraction(int id) {

        int customerID = id;

        if (customerID == -1) {
            // needs to check if it's a valid customer id
            System.out.println("\n---\n");
            System.out.print("\nCustomer's ID:\n\n>> ");
            Scanner console = new Scanner(System.in);
            console.useDelimiter("\n");

            console = new Scanner(System.in);

            while (!console.hasNextInt()) {
                System.out.print(invalidInputErrorIDOrZero());
                console.next();
            }
            customerID = console.nextInt();

            while (idIsNotContainedIn(customerID, customers)) {
                System.out.println("Invalid customer ID. Please enter a"
                        + " valid ID");
                customerID = console.nextInt();
            }
        }

        Scanner console = new Scanner(System.in);

        System.out.println("\n------\n");
        System.out.println("\nWas the order paid?\n");
        System.out.println("[1] - Yes");
        System.out.println("[2] - No");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(2);
        boolean paid = false;
        if (a == 1) {
            paid = true;
        }
        else if (a == 2) {
            paid = false;
        }

        System.out.println("\n------\n");
        System.out.print("\nOrder date (MM/dd/yyyy): \n\n>> ");
        String orderDate = console.next();

        System.out.println("\n\n------\n");
        System.out.print("\nPickup date (MM/dd/yyyy): \n\n>> ");
        String pickupDate = console.next();

        HashMap<Item, Integer> orderItems = new HashMap<Item, Integer>();
        orderItems = addItemsToOrder(orderItems);

        /**
         * Add up the total of the order
         */
        double orderTotal = 0.0;
        for (Item item : orderItems.keySet()) {
            orderTotal = orderTotal + (item.price * orderItems.get(item));
        }

        String lastName = "";
        double discountUsedOnOrder = 0.0;

        for (Customer c : customers) {
            if (c.customerID == customerID) {
                c.currentLoyalty = c.currentLoyalty + orderTotal;
                lastName = c.lastName;
                if (c.currentLoyalty >= 0.0 && c.currentLoyalty <= orderTotal) {
                    discountUsedOnOrder = c.currentLoyalty;
                }
                else {

                }
            }
        }

        int orderID = orders.get(orders.size() - 1).orderID + 1;
        double totalDue = orderTotal - discountUsedOnOrder;

        Order newOrder = new Order(customerID, lastName, orderID, paid,
                orderDate, pickupDate, orderTotal, discountUsedOnOrder,
                totalDue);
        newOrder.items = orderItems;
        orders.add(newOrder);

        for (Customer c : customers) {
            if (c.customerID == customerID) {
                c.customerOrders.add(newOrder);
            }
        }

        printHeader("Order successfully added - Order Receipt");

        System.out.println("Order ID:\t" + orderID);
        System.out.println("Ordered by:\t" + "[" + customerID + "] "
                + lastName);
        System.out.println("\nItems in order...");
        System.out.println("\n\tID\tItem Name\tPrice" + "(each)\tQuantity\n");
        for (Item i : newOrder.items.keySet()) {
            System.out.println("\t" + i.toString() + "\t\t"
                    + newOrder.items.get(i));
        }
        System.out.println("\nTotal Price: " + newOrder.total + "\n\n");

        System.out.println("[1] - Add another order");
        System.out.println("[2] - Main Menu");
        System.out.print("\n>> ");

        a = readInputAndCheckValidity(2);

        if (a == 1) {
            addOrderInteraction(-1);
        }
        else if (a == 2) {
            mainMenuInteraction();
        }

    }

    
    /**
     * adds an item to an Orders list of Items with a quantity attached
     * @param items
     *      the list of Items with quantities currently in the order
     * @return HashMap<Item, Integer>
     *      a HashMap of all items in the order with their quantity
     */
    public static HashMap<Item, Integer> addItemsToOrder(
            HashMap<Item, Integer> items) {
        @SuppressWarnings("resource")
        Scanner console = new Scanner(System.in);

        System.out.println("\n\n------\n");
        System.out.print("\nWhich item ID would you like to add?\n\n>> ");
        int itemID = console.nextInt();

        while (idIsNotContainedIn(itemID, inventory)) {
            System.out.print(invalidInputErrorIDOrZero());
            itemID = console.nextInt();
        }

        System.out.println("\n------\n");
        System.out.print("\n\nHow many would you like to add?\n\n>> ");
        int itemQuantity = console.nextInt();


        for (Item i : inventory) {
            if (itemID == i.itemID) {
                Item newItem = new Item(i.itemID, i.name, i.category, i.price);
                items.put(newItem, itemQuantity);
            }
        }


        System.out.println("\n------\n");
        System.out.println("\nWould you like to add another item?");
        System.out.println("\n[1] - Yes");
        System.out.println("[2] - No");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(2);

        if (a == 1) {
            addItemsToOrder(items);
        }

        return items;
    }

    /**
     * interactions for viewing all orders
     */
    private static void viewAllOrdersInteraction(boolean all) {

        if (all) {
            printHeader("Orders - View All Orders");

        }
        else {
            printHeader("Orders - View Unpaid Orders");
        }
        System.out.println("\nID\t# of Items\tTotal\t\tLast Name  \t"
                + "Order Date\tPickup Date\n");

        ArrayList<Integer> orderIDs = new ArrayList<Integer>();
        for (Order o : orders) {
            if (all) {
                if (!orderIDs.contains(o.orderID)) {
                    orderIDs.add(o.orderID);
                }
                System.out.println(o.toString());
            }
            else {
                if (!o.paid) {
                    if (!orderIDs.contains(o.orderID)) {
                        orderIDs.add(o.orderID);
                    }
                    System.out.println(o.toString());
                }
            }
        }

        System.out.println("\n\n[Order ID] - View Additional Info");
        System.out.println("[0] - Main Menu");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(orders);


        if (a == 0) {
            mainMenuInteraction();
        }
        else {
            for (Order o : orders) {
                if (o.orderID == a) {
                    o.advancedOrderViewInteraction();
                }
            }
        }

    }

    /**
     * interactions involved with updating an Order
     */
    public static void updateOrderInteraction() {
        printHeader("Orders - Update Order");
        System.out.println("Do you know the Order ID of the item you'd like"
                + " to update?\n");
        System.out.println("[1] - Yes");
        System.out.println("[2] - No, let me look through the inventory");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(2);

        if (a == 1) {
            System.out.print("\nOrder ID: >>");

            a = readInputAndCheckValidity(orders);

            for (Order o : orders) {
                if (o.orderID == a) {
                    o.updateOrder();
                }
            }
        }
        else if (a == 2) {
            viewAllOrdersInteraction(true);
        }
    }

    /**
     * all Item interactions including viewing, adding and updating
     */
    public static void inventoryMenuInteraction() {
        printHeader("Inventory Menu");
        System.out.println("[1] - View all items");
        System.out.println("[2] - Add item");
        System.out.println("[3] - Update item");
        System.out.println("\n[4] - Search");
        System.out.println("[5] - Main Menu\n");

        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(5);

        if (a == 1) {
            viewAllItemsInteraction();
        }
        else if (a == 2) {
            addItemInteraction();
        }
        else if (a == 3) {
            updateItemInteraction();
        }
        else if (a == 4) {
            searchInteraction();
        }
        else if (a == 5) {
            mainMenuInteraction();
        }
    }

    /**
     * prints all available items to the console
     */
    private static void viewAllItemsInteraction() {
        printHeader("Inventory - All Items");
        System.out.println("\nID\tItem Name\t\t\t\tPrice\n");

        int longestName = 0;
        for (Item i : inventory) {
            if (i.name.length() > longestName) {
                longestName = i.name.length();
            }
        }
        for (Item i : inventory) {
            System.out.println(i.toString(longestName));
        }

        System.out.println("\n[1] - Inventory Menu");
        System.out.println("[2] - Main Menu");

        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(2);

        if (a == 1) {
            inventoryMenuInteraction();
        }
        else if (a == 2) {
            mainMenuInteraction();
        }
    }

    /**
     * interactions involved in adding an item
     */
    private static void addItemInteraction() {
        printHeader("Inventory - Add item");
        Scanner console = new Scanner(System.in);
        console.useDelimiter(System.lineSeparator());

        System.out.print("\nNew item name: ");
        String name = console.next();

        System.out.print("\nNew item category: ");
        String category = console.next();

        System.out.print("\nNew item price: ");
        while (!console.hasNextDouble()) {
            System.out.print("\nInvalid input. Price must be a double"
                    + " (ex: 5.75): >> ");
            console.next();
        }
        double price = console.nextDouble();

        inventory.add(new Item(inventory.size() + 1, name, category, price));

        System.out.println("\nItem successfully  added to inventory!\n");
        System.out.println("[1] - Add another item");
        System.out.println("[2] - Inventory Menu");
        System.out.println("[3] - Main Menu");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(3);

        if (a == 1) {
            addItemInteraction();
        }
        else if (a == 2) {
            inventoryMenuInteraction();
        }
        else if (a == 3) {
            mainMenuInteraction();
        }
    }

    /**
     * interactions involved in updating an item
     */
    public static void updateItemInteraction() {
        printHeader("Inventory - Update Item");
        System.out.println("Do you know the Item ID of the item you'd like"
                + " to update?\n");
        System.out.println("[1] - Yes");
        System.out.println("[2] - No, let me look through the inventory");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(2);

        if (a == 1) {
            updateItemSearchByID();
        }
        else if (a == 2) {
            viewAllItemsInteraction();
        }

    }

    /**
     * finds an item by item id and gives the option to update it
     */
    public static void updateItemSearchByID() {
        System.out.println("\nEnter the Item ID of the Item to "
                + "update, or enter 0 to return to the Main Menu.");
        System.out.print("Item ID: >> ");
        Scanner console = new Scanner(System.in);

        ArrayList<Integer> itemIDs = new ArrayList<Integer>();
        for (Item i : inventory) {
            if (!itemIDs.contains(i.itemID)) {
                itemIDs.add(i.itemID);
            }
        }

        int a = readInputAndCheckValidity(inventory);
        if (a == 0) {
            mainMenuInteraction();
        }

        else {
            for (Item i : inventory) {
                if (a == i.itemID) {
                    System.out
                            .println("\nAre you sure you want to update the "
                                    + "item \"" + i.name + "\"?\n");
                    System.out.println("[1] - Yes");
                    System.out.println("[2] - No, go back");
                    System.out.println("\n[3] - Inventory Menu");
                    System.out.println("[4] - Main Menu");
                    System.out.print("\n>> ");

                    a = readInputAndCheckValidity(4);

                    if (a == 1) {
                        i.updateItem();
                    }
                    else if (a == 2) {
                        updateItemSearchByID();
                    }
                    else if (a == 3) {
                        inventoryMenuInteraction();
                    }
                    else if (a == 4) {
                        mainMenuInteraction();
                    }
                }
            }
        }
    }

    /**
     * Allows user to search all customers and orders by different criteria
     */
    private static void searchInteraction() {
        printHeader("Search");
        System.out.println("\nWhat would you like to search for?\n");
        System.out.println("[1] - A customer");
        System.out.println("[2] - An order");
        System.out.println("\n[3] - Main Menu");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(4);

        if (a == 1) {
            customerSearchInteraction();
        }
        else if (a == 2) {
            orderSearchInteraction();
        }
        else if (a == 3) {
            mainMenuInteraction();
        }
    }

    /**
     * allows the user to search customers by id or if they don't know it
     * will allow them to view all customers and find the correct id 
     */
    private static void customerSearchInteraction() {
        printHeader("Customer Search\n");
        System.out.println("[1] - Search by Customer ID");
        System.out.println("[2] - I don't know the Customer "
                + "ID - View all customers");
        System.out.println("\n[3] - Main Menu");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(5);

        if (a == 1) {
            customerSearchByID();
        }
        else if (a == 2) {
            viewAllCustomersInteraction();
        }
        else if (a == 3) {
            mainMenuInteraction();
        }
    }

    /**
     * checks to see if the customers id exists, if it does prints customer
     * information to console, if not makes them reenter
     */
    public static void customerSearchByID() {
        printHeader("Customer Search - Search by Customer ID...");
        System.out.println("Enter a customer ID or enter 0 to return"
                + " to the Main Menu");

        ArrayList<Integer> customerIDs = new ArrayList<Integer>();

        for (Customer c : customers) {
            if (!customerIDs.contains(c.customerID)) {
                customerIDs.add(c.customerID);
            }
        }

        Collections.sort(customerIDs);

        System.out.print("\nValid Customer IDs: ");
        for (int i : customerIDs) {
            System.out.print(i + " ");
        }

        System.out.print("\n\nCustomer ID: >> ");

        int a = readInputAndCheckValidity(customers);
        for (Customer c : customers) {
            if (c.customerID == a) {
                c.advancedCustomerView();
            }
        }

    }

    /**
     * all order searches including search by order id, search by item, 
     * search by order date and search by pickup date
     */
    private static void orderSearchInteraction() {
        printHeader("Search Orders");
        System.out.println("[1] - Search by Order ID");
        System.out.println("[2] - Search Orders containing a specific item");
        System.out.println("[3] - Search by Order Date");
        System.out.println("[4] - Search by Pickup Date");
        System.out.println("\n[4] - Main Menu");
        System.out.print("\n>> ");

        Scanner console = new Scanner(System.in);

        int a = readInputAndCheckValidity(4);

        if (a == 1) {
            orderSearchByID();
        }
        else if (a == 2) {
            searchOrdersBySpecificItems();
        }
        else if (a == 3) {
            searchOrdersByDate(false);
        }
        else if (a == 4) {
            searchOrdersByDate(true);
        }

    }

    /**
     * searches all orders by item id, prints to results to the console
     * and prompts the user if no orders have the item
     */
    public static void searchOrdersBySpecificItems() {

        printHeader("Search Orders - By Specific Items");
        System.out.println("\n\nEnter an Item ID to view all Orders "
                + "containing that item\nOr enter 0 to return to "
                + "the Main Menu");
        System.out.print("\n>> ");

        int a = readInputAndCheckValidity(inventory);

        if (a == 0) {
            mainMenuInteraction();
        }
        else {
            ArrayList<Order> matchingOrders = new ArrayList<Order>();
            System.out.println("\nID\t# of Items\tTotal\t\tLast Name  \t"
                    + "Order Date\tPickup Date\n");
            for (Order o : orders) {
                for (Item i : o.items.keySet()) {
                    if (i.itemID == a) {
                        System.out.println(o.toString());
                        matchingOrders.add(o);
                    }
                }
            }

            if (matchingOrders.size() == 0) {
                System.out.println("\nNo orders found\n");
                System.out.println("[1] - Search for another Order");
                System.out.println("[2] - Main Menu");
                System.out.print("\n>> ");

                a = readInputAndCheckValidity(2);

                if (a == 1) {
                    orderSearchInteraction();
                }
                else if (a == 2) {
                    mainMenuInteraction();
                }
            }
            else {
                System.out.println("\n\n[Order ID] - View additional "
                        + "Order information");
                System.out.println("[0] - Main Menu");
                System.out.print("\n>> ");
                a = readInputAndCheckValidity(matchingOrders);

                if (a == 0) {
                    mainMenuInteraction();
                }
                else {
                    for (Order o : matchingOrders) {
                        if (a == o.orderID) {
                            o.advancedOrderViewInteraction();
                        }
                    }
                }
            }
        }
    }

    /**
     * finds the highest possible valid date based on the month
     * @param month
     *      the input month
     * @return int
     *      returns the highest valid day within that month
     */
    public static int getValidDays(int month) {
        if (month == 2) {
            return 28;
        }
        else if ((month == 4) || (month == 6) || (month == 8)
                || (month == 10) || (month == 12)) {
            return 30;
        }
        else {
            return 31;
        }

    }

    /**
     * searches all orders by ID and prints the result to console or
     * prompts the user if non are found
     */
    public static void orderSearchByID() {
        printHeader("Search Orders By Order ID");


        Scanner console = new Scanner(System.in);

        ArrayList<Integer> orderIDs = new ArrayList<Integer>();

        for (Order o : orders) {
            if (!orderIDs.contains(o.orderID)) {
                orderIDs.add(o.orderID);
            }
        }

        System.out.print("\nEnter an Order ID or 0 to return to the "
                + "Main Menu\n\n");

        System.out.print("Valid Order IDs: \n");
        Collections.sort(orderIDs);
        for (int i : orderIDs) {
            System.out.println("\t\t" + i);
        }

        System.out.print("\n\n>> ");

        int a = readInputAndCheckValidity(orders);

        if (a == 0) {
            mainMenuInteraction();
        }
        else {
            for (Order o : orders) {
                if (a == o.orderID) {
                    o.advancedOrderViewInteraction();
                }
            }
        }
    }

    /**
     * searches the order by date (either placed or pickup) and prints the 
     * results to the console or prompts the user if none are found
     * @param pickup 
     *      determines if we're searching for when the order was placed or
     *      when the order is/was picked up
     */
    public static void searchOrdersByDate(boolean pickup) {
        printHeader("Search Orders - By Date");

        System.out.println("\nEnter the date to search for:");
        System.out.print("\nInput Order Month (MM): \n\n>> ");
        int month = readInputAndCheckValidity(12);

        int validDays = getValidDays(month);
        System.out.print("\nInput Order Day (DD): \n\n>> ");
        int day = readInputAndCheckValidity(validDays);

        System.out.print("\nInput Order year (YY): \n\n>> ");
        int year = readInputAndCheckValidity(100);

        String dateGiven = month + "/" + day + "/" + year;
        System.out.println("\t" + "OrderID\t" + "Quantity\t" + "Total\t\t" + 
        "Last Name\t" + "Order Date\t" + "Pickup Date\n");
        ArrayList<Order> matchingOrders = new ArrayList<Order>();

        for (Order o : orders) {
            if (pickup) {
                if (o.pickupDate.equals(dateGiven)) {
                    matchingOrders.add(o);
                    System.out.println(o.toString());
                }
            }
            else {
                if (o.orderDate.equals(dateGiven)) {
                    matchingOrders.add(o);
                    System.out.println("\t" + o.toString());
                }
            }
        }




        if (matchingOrders.size() == 0) {
            System.out.println("No orders found.\n");
            System.out.println("[1] - Search for another Order");
            System.out.println("[2] - Main Menu");
            System.out.print("\n>> ");

            int a = readInputAndCheckValidity(2);

            if (a == 1) {
                orderSearchInteraction();
            }
            else if (a == 2) {
                mainMenuInteraction();
            }
        }
        else {

            System.out.println("\nTo see more information about a particular"
                    + " order, enter that order's Order ID number.\nOr"
                    + " enter 0 to return to the Main Menu.");
            System.out.print("\n>> ");


            int a = readInputAndCheckValidity(matchingOrders);

            if (a == 0) {
                mainMenuInteraction();
            }
            else {
                for (Order o : matchingOrders) {
                    if (o.orderID == a) {
                        o.advancedOrderViewInteraction();
                    }
                }
            }
        }
    }

    /**
     * Given a Scanner of an inventory file, constructs the inventory
     * 
     * @param file
     *            The given Scanner
     */
    static void constructInventory(Scanner file) {
        System.out.println("\n---------------------------------------------");
        System.out.println();

        file.nextLine();

        System.out.print("\nInitializing inventory...\t");
        long initStartTime = System.currentTimeMillis();
        /**
         * While the given Scanner file has another line, use that next line to
         * create an Item and put it in the inventory
         */
        while (file.hasNextLine()) {
            createItem(file.nextLine());
        }
        long initTotalTime = System.currentTimeMillis() - initStartTime;
        System.out.print("\tAll items initialized! - [" + initTotalTime
                + " ms]");
    }

    /**
     * creates an item from a line in the input file
     * @param line
     *      the line from the txt file
     */
    static void createItem(String line) {
        Scanner sc = new Scanner(line);
        sc.useDelimiter("\t");

        while (sc.hasNext()) {
            int id = sc.nextInt();
            // System.out.println(id);
            String name = sc.next();
            // System.out.println(name);
            String category = sc.next();
            // System.out.println(category);
            double price = sc.nextDouble();
            // System.out.println(price);
            inventory.add(new Item(id, name, category, price));
            // System.out.println("Item added to inventory! - [" + id + "]");
        }
        sc.close();
    }

    /**
     * initializes the orders/customers from the input files
     * @param file
     *            The given Scanner
     */
    static void constructOrdersAndCustomers(Scanner file) {

        file.nextLine();

        System.out.print("\nInitializing orders and customers...\t");
        long initStartTime = System.currentTimeMillis();

        while (file.hasNextLine()) {
            createOrdersAndCustomers(file.nextLine());
        }

        linkOrdersToCustomers();


        long initTotalTime = System.currentTimeMillis() - initStartTime;
        System.out.print("All orders and customers initialized! - ["
                + initTotalTime + " ms]\n\n");

    }

    /**
     * appropriately allocates the data from the input file to 
     * create customers and orders
     * @param line
     *      a line from the input txt file
     */
    static void createOrdersAndCustomers(String line) {

        Scanner sc = new Scanner(line);
        sc.useDelimiter("\t");

        while (sc.hasNext()) {

            int customerID = sc.nextInt();
            String lastName = sc.next();
            String address = sc.next();
            String city = sc.next();
            String state = sc.next();
            String zip = sc.next();
            int orderID = sc.nextInt();
            String paidString = sc.next();
            boolean paid = false;
            if (paidString.toLowerCase().equals("yes")) {
                paid = true;
            }
            String orderDate = sc.next();
            String pickupDate = sc.next();
            int bakeryItemID = sc.nextInt();
            String bakeryItemName = sc.next();
            String bakeryItemCategory = sc.next();

            int quantity = sc.nextInt();

            double price = sc.nextDouble();
            double total = sc.nextDouble();
            double discountUsedOnOrder = sc.nextDouble();
            double totalDue = sc.nextDouble();
            double availableDiscount = sc.nextDouble();
            double currentLoyalty = sc.nextDouble();

            ArrayList<Integer> orderIDs = new ArrayList<Integer>();

            for (Order o : orders) {
                if (o.orderID == orderID) {
                    o.items.put(new Item(bakeryItemID, bakeryItemName,
                            bakeryItemCategory, price), quantity);
                }
                orderIDs.add(o.orderID);
            }

            if (!orderIDs.contains(orderID)) {
                Order o = new Order(customerID, lastName, orderID, paid,
                        orderDate, pickupDate, total, discountUsedOnOrder,
                        totalDue);

                o.items.put(new Item(bakeryItemID, bakeryItemName,
                        bakeryItemCategory, price), quantity);

                orders.add(o);
            }


            boolean hasLoyaltyCard = false;

            if (currentLoyalty == 0.0 && discountUsedOnOrder == 0.0
                    && availableDiscount == 0.0) {
                hasLoyaltyCard = true;
            }

            if (customers.size() == 0) {
                customers.add(new Customer(customerID, lastName, address,
                        city, state, zip, hasLoyaltyCard, availableDiscount,
                        currentLoyalty));
            }
            else {
                if (idIsNotContainedIn(customerID, customers)) {
                    customers.add(new Customer(customerID, lastName, address,
                            city, state, zip, hasLoyaltyCard,
                            availableDiscount, currentLoyalty));
                }
            }
        }

        sc.close();

    }

    /**
     * links all orders to their appropriate customers
     */
    public static void linkOrdersToCustomers() {

        for (Order o : orders) {
            for (Customer c : customers) {
                if (o.customerID == c.customerID) {
                    if (!c.customerOrders.contains(o)) {
                        c.customerOrders.add(o);
                    }
                }
            }
        }
    }

    /**
     * calculates the total sales for a customer
     * @return double
     *      the total sales for all a customers orders 
     */
    public double totalSales() {
        double result = 0.0;

        for (Customer c : this.customers) {
            // result = result + c.totalOfOrders();
        }

        return result;
    }

    /**
     * calculates the total sales for a customer between two given dates
     * @param d1

    *      the first, earlier date
     * @param d2
     *      the second, later date
     * @return double
     *      the total sales for that customer between those dates
     */
    public double totalSales(Date d1, Date d2) {
        double result = 0.0;

        for (Customer c : this.customers) {
            // result = result + c.totalOfOrders();
        }

        return result;
    }

    /**
     * prints all the items to the console
     */
    public void viewItems() {

        for (Item i : this.inventory) {
            System.out.println(i.toString());
        }
    }

}
