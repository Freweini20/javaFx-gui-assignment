import java.util.Scanner;

/*================================================================================================
Author: Freweini Debesay
Class: CSC164
Class Section: 359
Data: 3/6/2026
Assignment: M5 OOP Assignment

I wrote all the code submitted, and I have provided citations and references where appropriate.
========================================================================*/
public class AssignmentOOP {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        boolean isRunning = true;
        String choice;

        Food[] menu = {
                new Food("Burger", 5.99, "Gluten, Dairy"),
                new Food("Salad", 4.49, "Nuts"),
                new Food("Pizza", 8.99, "Gluten, Dairy"),
                new Food("Soup", 3.99, "None"),
                new Food("Pasta", 7.49, "Gluten, Dairy")
        };

        do {
            System.out.println("Welcome to Java Bites Restaurant!");
            System.out.println("-----------Menu-----------");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + ". " + menu[i].getFoodName() + " - $" + menu[i].getFoodPrice() + " (Contains: " + menu[i].getFoodAllergen() + ")");
            }

            //prompt customer for their name
            System.out.print("\nPlease enter your name: ");
            String customerName = input.nextLine();

            int option1;

            //prompt user for the first order
            do {
                System.out.print("\nEnter the # of the first item you would like to order: ");
                option1 = input.nextInt();
                if (option1 < 1 || option1 > menu.length) {
                    System.out.println("Please enter a number between 1 and " + (menu.length));
                }
            } while (option1 < 1 || option1 > menu.length);

            //store option 1
            Food item1 = menu[option1 - 1];

            int option2;
            //prompt user for the second order
            do {
                System.out.print("Enter the # of the second item you would like to order (or 0 for none): ");
                option2 = input.nextInt();
                //  input.nextLine();
                if (option2 < 0 || option2 > menu.length) {
                    System.out.println("Please enter a number between 0 and " + (menu.length));
                }

            } while (option2 < 0 || option2 > menu.length);

            //store option 2 if customer ordered a second order
            Food item2;
            if (option2 == 0) {
                item2 = null;
            } else {
                item2 = menu[option2 - 1];
            }

            Order order = new Order(customerName, item1, item2);

            //combine allergens if they repeat duplicates
            String allergen1 = order.getItem1().getFoodAllergen();

            //print receipt
            System.out.println("\n------------Receipt Menu------------");
            System.out.println("Customer Name: " + order.getCustomerName());
            System.out.println("Ordered Items:-");
            System.out.println("- " + order.getItem1().getFoodName() + " - $" + order.getItem1().getFoodPrice());
            if (order.getItem2() != null) {
                System.out.println("- " + order.getItem2().getFoodName() + " - $" + order.getItem2().getFoodPrice());
                String allergen2 = order.getItem2().getFoodAllergen();
                if (!allergen2.equals(allergen1)) {
                    allergen1 += ", " + allergen2;
                }
            }
            System.out.println("Total cost: " + order.getTotalCost());
            System.out.println("Allergen: " + allergen1);
            System.out.println("--------------------------");


            System.out.print("Would you like to run the program again (Yes/No): ");
            input.nextLine();
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("Yes")) {
                System.out.println("Returning to the program......");
                System.out.println();
            } else if (choice.equalsIgnoreCase("No")) {
                isRunning = false;
                System.out.println("Thank you for dining with us! Enjoy your order.\n Exiting the program.........");
            } else {
                System.out.println("Invalid input");
            }

        } while (isRunning);
    }
}
class Food {
    private String foodName;
    private double foodPrice;
    private String foodAllergen;

    public Food(String foodName, double foodPrice, String foodAllergen) {
        if  (foodName == null || foodPrice < 0) {
            System.out.println("Food price must be positive!");
            this.foodName = "Unknown";
            this.foodPrice = 0;
        } else {
            this.foodName = foodName;
            this.foodPrice = foodPrice;
        }
        this.foodAllergen = foodAllergen;
    }

    public String getFoodName() {
            return foodName;

    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public double getFoodPrice() {
            return foodPrice;
    }
    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
    public String getFoodAllergen() {
        return foodAllergen;
    }
    public void setFoodAllergen(String foodAllergen) {
        this.foodAllergen = foodAllergen;
    }

}

class Order {
    private final Food item1;
    private final Food item2;
    private double totalCost;
    private final String customerName;

    public Order(String customerName, Food item1, Food item2) {
        if (customerName == null || customerName.isEmpty()) {
            System.out.println("Customer name is empty!");
            this.customerName = "Unknown";
        } else {
            this.customerName = customerName;
        }
        this.item1 = item1;
        this.item2 = item2;
        calculateTotalCost();

    }
    public String getCustomerName() {
        return customerName;

    }
    public Food getItem1() {
        return item1;
    }

    public Food getItem2() {
        return item2;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void calculateTotalCost() {
        totalCost = item1.getFoodPrice();
        if (item2 != null) {
            totalCost += item2.getFoodPrice();
        }
    }

}