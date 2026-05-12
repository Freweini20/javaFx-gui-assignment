/*================================================================================================
Author: Freweini Debesay
Class: CSC164
Class Section: 359
Data: 3/30/2026
Assignment: M6-7 Inheritance And Polymorphism Assignment

I wrote all the code submitted, and I have provided citations and references where appropriate.
==================================================================================================*/
import java.util.ArrayList;
import java.util.Scanner;

public class InheritanceAndPolymorphisim {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        do {
            Menu restaurant1Menu = new Menu();
            // anonymous object
            restaurant1Menu.addMenuItem(new Food("Hot Dog", 1.5D, "sesame, gluten"));
            restaurant1Menu.addMenuItem(new Food("Burger", 5.5D, "sesame, gluten"));
            restaurant1Menu.addMenuItem(new Food("Fries", 2.5D, "none"));
            restaurant1Menu.addMenuItem(new Food("Pizza", 8.9D, "Gluten, Dairy"));
            restaurant1Menu.addMenuItem(new Food("Pasta", 7.5D, "Gluten"));
            restaurant1Menu.addMenuItem(new Drink("Soda", 6.67, "Large", true));
            restaurant1Menu.addMenuItem(new Drink("Coffee", 3.49, "Medium", false));
            restaurant1Menu.addMenuItem(new Drink("Milk", 5.89, "Small", false));
            restaurant1Menu.addMenuItem(new Drink("Juice", 4.50, "Medium", false));
            restaurant1Menu.addMenuItem(new GiftCard("Gift Card", 7.06, 8.88, "Enjoy your meal"));

            System.out.println("\nWelcome to Java Bites Restaurant!\n");

            //print the restaurant items
            System.out.println(restaurant1Menu);

            System.out.print("Enter your name: ");
            String name = input.nextLine();

            Order order = new Order(name);

            int count;

            do {
                System.out.print("how many items would you like to order (1 - 10): ");
                count = input.nextInt();

                if (count < 1 || count > restaurant1Menu.size()) {
                    System.out.println("Please enter a number between 1 and " + restaurant1Menu.size());
                }

            } while (count < 1 || count > restaurant1Menu.size());

            int option = 0;

            for (int i = 0; i < count; i++) {
                System.out.print("Enter item #" + (i + 1) + ": ");
                option = input.nextInt();

                if (option >= 1 && option <= restaurant1Menu.size()) {
                    order.addItem(restaurant1Menu.getItem(option - 1));
                } else {
                    System.out.println("Please enter a number between 1 and " + restaurant1Menu.size());
                    i--;
                }
            }

            //calculate total cost
            order.calculateTotal();

            System.out.println(order.toString());

            input.nextLine();         //clear buffer

            while (true) {
                System.out.print("Would you like place another order? (yes/no): ");
                String answer = input.nextLine();

                if (answer.equalsIgnoreCase("yes")) {
                    System.out.println("Order Placed!");
                    break;
                } else if (answer.equalsIgnoreCase("no")) {
                    isRunning = false;
                    System.out.println("Thank you for visiting Java Bites Restaurant!");
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }

        } while (isRunning);
    }
}

    class RestaurantItem {
        private String name;
        private double price;

        public RestaurantItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            if (!name.isEmpty()) {
                this.name = name;
            } else {
                System.out.println("Name is empty");
            }
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            if (price > 0) {
                this.price = price;
            } else {
                System.out.println("Price is negative");
            }
        }

        @Override
        public String toString() {
            return name +
                    "($" + price +
                    ")";
        }
    }

    class Food extends RestaurantItem {
        private final String allergens;

        public Food(String name, double price, String allergens) {
            super(name, price);
            this.allergens = allergens;
        }

        @Override
        public String toString() {
            return super.toString()
                    + " [Allergens: " + allergens +
                    "]";
        }
    }

    class Drink  extends RestaurantItem {
        private final String size;
        private final boolean hasIce;

        public Drink(String name, double price, String size, boolean hasIce) {
            super(name, price);
            this.size = size;
            this.hasIce = hasIce;
        }

        @Override
        public String toString() {
            return super.toString() + " (" + size +
                    ", " + (hasIce ? "Ice" : "No Ice") +
                    ") ";
        }
    }

    class GiftCard extends RestaurantItem {
        private final double balance;
        private final String message;

        public GiftCard(String name, double price, double balance, String message) {
            super(name, price);
            this.balance = balance;
            this.message = message;
        }

        @Override
        public String toString() {
            return super.toString() + " (" +
                    "Balance: $" + balance +
                    ") - " + message + "!";
        }
    }

    class Menu {
        private final ArrayList<RestaurantItem> menuItems = new ArrayList<>();

        public void addMenuItem(RestaurantItem item) {
            menuItems.add(item);
        }

        public RestaurantItem getItem(int index) {
            return menuItems.get(index);
        }

        @Override
        public String toString() {
            if (menuItems.isEmpty()) {
                return "Menu is empty.";
            }

            StringBuilder output = new StringBuilder("Menu:\n");
            for (int i = 0; i < menuItems.size(); i++) {
                output.append(String.format("%d: %s%n", (i + 1), menuItems.get(i).toString()));
            }

            return output.toString();
        }

        //Returns the number of items in the menu
        public int size() {
            return menuItems.size();
        }
    }

    class Order {
        public Order(String customerName) {
            this.customerName = customerName;
        }
        private String customerName;
        private final ArrayList<RestaurantItem> itemsOrdered = new ArrayList<>();
        private double totalCost;


        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            if (customerName != null && !customerName.isEmpty()) {
                this.customerName = customerName;
            } else {
                this.customerName = "Unknown";
                System.out.println("Customer name is empty.");
            }
        }

        public void addItem(RestaurantItem item) {
            itemsOrdered.add(item);
        }

        public double calculateTotal() {
            totalCost = 0;
            for (int i = 0; i < itemsOrdered.size(); i++) {
                totalCost += itemsOrdered.get(i).getPrice();
            }
            return totalCost;
        }

        @Override
        public String toString() {
            if (itemsOrdered.isEmpty()) {
                return "Ordered items are empty.";
            }

            System.out.println("\nThank you! Generating your receipt...\n");
            StringBuilder output = new StringBuilder("Receipt:\n");
            output.append(String.format("customer Name: %s%n", customerName));
            output.append("Ordered Items:\n");
            for (int i = 0; i < itemsOrdered.size(); i++) {
                output.append(String.format("%s%n", itemsOrdered.get(i).toString()));
            }

            output.append(String.format("\nTotal cost: $%.2f%n", totalCost));

            return output.toString();
        }

    }

