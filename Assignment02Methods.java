/*================================================================================================
Author: Freweini Debesay
Class: CSC164
Class Section: 359
Date: 2/16/2026
Assignment: Java Methods Assignment

I wrote all the code submitted, and I have provided citations and references where appropriate.
==================================================================================================*/
import java.util.Scanner;

public class Assignment02Methods {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        //Declare menu data arrays
        String[] foodNames = {"Burger", "Salad", "Pizza", "Soup", "Pasta"};
        double[] foodPrices = {5.99, 4.49, 8.99, 3.99, 7.49};
        String[] foodAllergens = {"Gluten, Dairy", "Nuts", "Gluten, Dairy", "None", "Gluten, Dairy"};
        boolean running = true;

        //loop the program until the customer selects no to exit
        do {

            displayMenu(foodNames, foodPrices, foodAllergens);

            System.out.print("Enter your name: ");
            String customerName = keyboard.nextLine();

            String[] orderedNames = new String[2];
            double[] orderedPrices = new double[2];
            String[] orderedAllergens = new String[2];

           //get the customer's first menu selection
            int firstItemChoice = getValidItemChoice(foodNames, keyboard);

           //store selected first item
            orderedNames[0] = foodNames[firstItemChoice];
            orderedPrices[0] = foodPrices[firstItemChoice];
            orderedAllergens[0] = foodAllergens[firstItemChoice];

            int secondItemChoice;

            //prompt user if they would like to place a second order(for nothing or to skip select 0) and validate input
            do {
                System.out.print("Enter a second item number (0 to skip): ");
                secondItemChoice = keyboard.nextInt();
                System.out.println();
                keyboard.nextLine();

                if (secondItemChoice < 0 || secondItemChoice > foodNames.length) {
                    System.out.println("Error: number must be 0-5: ");
                }

            } while (secondItemChoice < 0 || secondItemChoice > foodNames.length);

            double totalCost = orderedPrices[0];

            /*if customer doesn't want to place a second order, they can select 0.
            so this is if the customer selects a number other than 0, I stored the second item
            */
            if (secondItemChoice != 0) {
                orderedNames[1] = foodNames[secondItemChoice - 1];
                orderedPrices[1] = foodPrices[secondItemChoice - 1];
                orderedAllergens[1] = foodAllergens[secondItemChoice - 1];

                totalCost += orderedPrices[1];

            }

            printReceipt(customerName, orderedNames, orderedPrices, orderedAllergens, totalCost);

            System.out.print("Would you like to place another order (y/n): ");
            char choice = keyboard.next().charAt(0);
            keyboard.nextLine();

            if (choice == 'y' || choice == 'Y') {
                // running = true;
                System.out.println("Starting new order.........");
            } else if (choice == 'n' || choice == 'N') {
                System.out.println("Program Exiting...........");
                running = false;
            } else {
                System.out.println("Invalid choice......");
            }
        } while (running);
    }

    //Display menu
    static void displayMenu(String[] names, double[] prices, String[] allergens) {
        //display menu
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d. %s $%.2f contains %s %n", (i + 1), names[i], prices[i], allergens[i]);
        }
        System.out.println();

    }

   //prompts the user for a valid menu item and return a value(index)
    static int getValidItemChoice(String[] names, Scanner keyboard) {
        int itemChoice;

        //prompt user to select a first option and validate input
        do {
            System.out.print("Enter the menu item number 1-5: ");
            itemChoice = keyboard.nextInt();

            if (itemChoice < 1 || itemChoice > names.length)
                System.out.println("Error: input number must be 1-5");
            keyboard.nextLine();

        } while (itemChoice < 1 || itemChoice > names.length);

        return itemChoice - 1;
    }

    //prints the final receipt with items and total
    static void printReceipt(String clientName, String[] chosenNames, double[] selectedPrices, String[] selectedAllergens, double total) {
        System.out.println("-----------------RECEIPT------------------");
        System.out.printf("Name: %s %n", clientName);
        System.out.println("Ordered Items: ");

        for (int i = 0; i < chosenNames.length; i++) {
            if (chosenNames[i] != null) {
                System.out.printf("%s ($%.2f) contains %s %n", chosenNames[i], selectedPrices[i], selectedAllergens[i]);
            }
        }

        System.out.printf("Total Price: $%.2f %n", total);
        System.out.println("------------------------------------------");


    }

}
