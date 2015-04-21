import java.util.*;

/**
 * 
 * @author Conor Ebbs and Rocco Basso
 * 
 */
public class Customer {

    /** 
     * the customers id
     */
    int customerID;

    /**
     * the customers last name
     */
    String lastName;

    /** 
     * the customers address
     */
    String address;

    /** 
     * the customers city
     */
    String city;

    /**
     * the customers state
     */
    String state;

    /**
     * the customers zip
     */
    String zipcode;

    /**
     * whether or not the customer has a loyalty card
     */
    boolean hasLoyaltyCard;

    /**
     * the customers available balance
     */
    double availableBalance;

    /**
     * the customers current loyalty
     */
    double currentLoyalty;

    /**
     * a list of all the customers past and current orders
     */
    ArrayList<Order> customerOrders = new ArrayList<Order>();

    /**
     * Constructor
     * 
     * @param customerID
     * @param lastName
     * @param email
     * @param customerBillingInfo
     * @param customerPreferred
     * @param availableBalance
     * @param customerOrders
     */
    Customer(int customerID, String lastName, String address, String city,
            String state, String zipcode, boolean hasLoyaltyCard,
            double availableBalance, double currentLoyalty) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.hasLoyaltyCard = hasLoyaltyCard;
        this.availableBalance = availableBalance;
        this.currentLoyalty = currentLoyalty;
    }



    /**
     * interactions that go into updating a customers orders
     */
    public void updateCustomerInfo() {
        Bakery.printHeader("Update Customer - [" + this.customerID + "] "
                + this.lastName);
        System.out.println("What do you want to update?\n");
        System.out.println("[1] - Last Name");
        System.out.println("[2] - Address");
        System.out.println("[3] - City");
        System.out.println("[4] - State");
        System.out.println("[5] - Zipcode");
        System.out.println("[6] - Loyalty Card Status");
        System.out.println("\n[7] - Nothing - Main Menu");
        System.out.print("\n>> ");
        
        int a = Bakery.readInputAndCheckValidity(7);
        
        if (a == 1) {
            Bakery.printHeader("Update Customer - Last Name");
            System.out.print("\nUpdated last name: >> ");
            Scanner console = new Scanner(System.in);
            this.lastName = console.next();
        }
        else if (a == 2) {
            Bakery.printHeader("Update Customer - Address");
            System.out.print("\nUpdated address: >> ");
            Scanner console = new Scanner(System.in);
            this.lastName = console.next();
        }
        else if (a == 3) {
            Bakery.printHeader("Update Customer - City");
            System.out.print("\nUpdated city: >> ");
            Scanner console = new Scanner(System.in);
            this.lastName = console.next();
        }
        else if (a == 4) {
            Bakery.printHeader("Update Customer - State");
            System.out.print("\nUpdated state: >> ");
            Scanner console = new Scanner(System.in);
            this.lastName = console.next();
        }
        else if (a == 5) {
            Bakery.printHeader("Update Customer - Zipcode");
            System.out.print("\nUpdated zipcode: >> ");
            Scanner console = new Scanner(System.in);
            this.lastName = console.next();
        }
        else if (a == 6) {
            Bakery.printHeader("Update Customer - Loyalty Status");
            if (this.hasLoyaltyCard) {
                System.out.println("This customer currently has a loyalty "
                        + "card. Do you want to remove the loyalty "
                        + "status of this customer?\n");
                System.out.println("[1] - No");
                System.out.println("[2] - Yes");
            }
            else {
                System.out
                        .println("This customer currently does not have a loyalty "
                                + "card. Do you want to add loyalty "
                                + "status to this customer?\n");
                System.out.println("[1] - Yes");
                System.out.println("[2] - No");
            }

            System.out.print("\n>> ");
            a = Bakery.readInputAndCheckValidity(2);

            if (a == 1) {
                this.hasLoyaltyCard = true;
            }
            else if (a == 2) {
                this.hasLoyaltyCard = false;
            }
        }
        else if (a == 7) {
            Bakery.mainMenuInteraction();
        }

        Bakery.printHeader("\nCustomer Information Updated!\n");

        System.out.println("What would you like to do next?\n");
        System.out.println("[1] - Update more information for this customer");
        System.out.println("[2] - Update another customer");
        System.out.println("\n[3] - Main Menu");

        a = Bakery.readInputAndCheckValidity(3);

        if (a == 1) {
            updateCustomerInfo();
        }
        else if (a == 2) {
            Bakery.updateCustomerInteraction();
        }
        else if (a == 3) {
            Bakery.mainMenuInteraction();
        }

    }

    /**
     * gives an advanced view of the customer (all their info)
     * when requested
     */
    public void advancedCustomerView() {
        Bakery.printHeader("Customers - Advanced View");
        System.out.println("Customer ID: \t" + this.customerID);
        System.out.println("Last Name: \t" + this.lastName);
        System.out.println("Address: \t" + this.address);
        System.out.println("City: \t\t" + this.city);
        System.out.println("State: \t\t" + this.state);
        System.out.println("Zipcode: \t" + this.zipcode);
        System.out.println("\nHas Loyalty Card? \t" + this.hasLoyaltyCard);
        System.out.println("Available balance: \t" + this.availableBalance);
        System.out.println("Current loyalty: \t" + this.currentLoyalty);

        System.out.println("\nCustomer Orders...");
        System.out.println("\n\tID\t# of Items\tTotal\tLast Name  \t"
                + "Order Date\tPickup Date\n");

        for (Order o : this.customerOrders) {
            System.out.println("\t" + o.toString());
        }

        System.out.println("\n\n[Order ID] - View Additional Info");
        System.out.println("\n[1] - Update this customer");
        System.out.println("[2] - Main Menu");
        System.out.print("\n>> ");

        int a = Bakery.readInputAndCheckValidity(2, this.customerOrders);

        if (a == 1) {
            this.updateCustomerInfo();
        }
        else if (a == 2) {
            Bakery.mainMenuInteraction();
        }
        else {
            for (Order o : this.customerOrders) {
                if (o.orderID == a) {
                    o.advancedOrderViewInteraction();
                }
            }
        }
    }

    /**
     * prints out all customer information while formating the spacing
     * based on the longest name
     * @param longestName
     *      the longest name in the list of customers
     * @param longestAddress
     *      the longest address in the list of customers
     * @return String
     *      with all customer information
     */
    public String toString(int longestName, int longestAddress) {
        int nameDiff = longestName - this.lastName.length();
        int addressDiff = longestAddress - this.address.length();
        String nameBuffer = "";
        String addressBuffer = "";
        for (int i = 0; i < nameDiff; i++) {
            nameBuffer = nameBuffer + " ";
        }
        for (int i = 0; i < addressDiff; i++) {
            addressBuffer = addressBuffer + " ";
        }
        return "[" + this.customerID + "]\t" + this.lastName + nameBuffer
                + "\t" + this.address + addressBuffer + "\t" + this.city
                + "\t\t" + this.state + "\t" + this.zipcode;
    }

    /**
     * prints all customer information
     * @return String
     *      a string with all customer information
     */
    public String toString() {
        return "[" + this.customerID + "]\t" + this.lastName + "\t"
                + this.address + "\t" + this.city
                + "\t\t" + this.state + "\t" + this.zipcode;
    }
}
