import java.io.*;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;



public class BakeryTest {


    // @Test
    public void testCreateItem() {
        System.out.println("\ntestCreateItem...\n");

        Bakery.createItem("1    Almond Croissant    Pastries    3.00");

    }

    @Test
    public void testConstructInventory() throws FileNotFoundException {
        System.out.println("\ntestConstructInventory...\n");
        File f = new File("bakeryItems.txt");
        Scanner file = new Scanner(f);


        Bakery.constructInventory(file);
    }
    
    // @Test
    public void testCreateOrdersAndCustomers() {
        System.out.println("\ntestCreateOrdersAndCustomers...\n");
        String line = "56  Robinson    634 Oak Ave.    Raleigh NC  27612   101 Yes 11/5/10 12/31/10    8   Blackberry  Pies    1   17.00   17.00   0.00    17.00   0.00    17.00";
        
        Bakery.createOrdersAndCustomers(line);
    }
    
    @Test
    public void testConstructOrdersAndCustomers()
            throws FileNotFoundException {
        System.out.println("\ntestConstructOrdersAndCustomers...\n");
        File f = new File("orders.txt");
        Scanner file = new Scanner(f);
        
        
        Bakery.constructOrdersAndCustomers(file);
    }
    
    @Test
    public void testInvalidInput() {
        assertEquals(Bakery.invalidInput(5), "\nInvalid input."
                + " Input a number from 1 - " + 5 + ". >> ");
    }
    
    @Test
    public void testInvalidInputErrorIDOrZero() {
        assertEquals(Bakery.invalidInputErrorIDOrZero(), "\nInvalid input. "
                + "Input a valid ID, or 0 to return to the Menu. >> ");
    }
   
    @Test
    public void testInvalidInputErrorID() {
        assertEquals(Bakery.invalidInputErrorID(10), "\nInvalid input."
                + " Input a valid ID or a number " + "from 1 - "
                + 10 + ". >> ");
    }
    
    @Test
    public void testInputAndCheckValidity() { 
        
    }

}
