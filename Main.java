
import java.util.*;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        ProductManager productManager = new ProductManager();
        InventoryManager inventoryManager =
                new InventoryManager(productManager);
        SalesManager salesManager =
                new SalesManager(productManager, inventoryManager);

        int choice;

        do {
            System.out.println("\n===== SMART SHOP MANAGEMENT SYSTEM =====");
            System.out.println("1. Product Management");
            System.out.println("2. Inventory Management");
            System.out.println("3. Sales and Invoice Management");
            System.out.println("4. Exit");

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    productMenu(productManager);
                    break;

                case 2:
                    inventoryMenu(inventoryManager);
                    break;

                case 3:
                    salesMenu(salesManager);
                    break;

                case 4:
                    System.out.println("Thank you for using Smart Shop!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 4);
    }

    // Product Management Menu
    public static void productMenu(ProductManager pm) {

        int choice;

        do {
            System.out.println("\n===== PRODUCT MANAGEMENT =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Sort Products by Price");
            System.out.println("7. Back to Main Menu");

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    try {
                        int id = readInt("Enter Product ID: ");
                        String name = readNonEmptyString("Enter Product Name: ");
                        double price = readDouble("Enter Product Price: ");
                        int quantity = readInt("Enter Product Quantity: ");

                        Product p = new Product(id, name, price, quantity);
                        pm.addProduct(p);

                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid product information: "
                                + e.getMessage());
                    }
                    break;

                case 2:
                    pm.displayProducts();
                    break;

                case 3:
                    int searchId = readInt("Enter Product ID to search: ");
                    pm.findProduct(searchId);
                    break;

                case 4:
                    int updateId = readInt("Enter Product ID to update: ");
                    String newName = readNonEmptyString("Enter New Name: ");
                    double newPrice = readDouble("Enter New Price: ");
                    int newQuantity = readInt("Enter New Quantity: ");

                    pm.updateProduct(updateId, newName,
                            newPrice, newQuantity);
                    break;

                case 5:
                    int deleteId = readInt("Enter Product ID to delete: ");
                    pm.deleteProduct(deleteId);
                    break;

                case 6:
                    pm.sortProducts();
                    break;

                case 7:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 7);
    }

    // Inventory Management Menu
    public static void inventoryMenu(InventoryManager im) {

        int choice;

        do {
            System.out.println("\n===== INVENTORY MANAGEMENT =====");
            System.out.println("1. View Available Stock");
            System.out.println("2. Update Stock Quantity");
            System.out.println("3. Back to Main Menu");

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    im.viewStock();
                    break;

                case 2:
                    int id = readInt("Enter Product ID: ");
                    int quantity = readInt("Enter New Quantity: ");

                    im.updateStock(id, quantity);
                    break;

                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 3);
    }

    // Sales and Invoice Management Menu
    public static void salesMenu(SalesManager sm) {

        int choice;

        do {
            System.out.println("\n===== SALES AND INVOICE =====");
            System.out.println("1. Record Sale");
            System.out.println("2. View Sales Records");
            System.out.println("3. View Total Sales");
            System.out.println("4. Back to Main Menu");

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    int id = readInt("Enter Product ID: ");
                    int quantity = readInt("Enter Quantity Sold: ");

                    sm.recordSale(id, quantity);
                    break;

                case 2:
                    sm.displaySales();
                    break;

                case 3:
                    System.out.println("Total Sales: Rs. "
                            + sm.getTotalSales());
                    break;

                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 4);
    }

    // Read valid integer input
    public static int readInt(String message) {

        while (true) {
            System.out.print(message);
            String value = input.nextLine().trim();

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number!");
            }
        }
    }

    // Read valid decimal input
    public static double readDouble(String message) {

        while (true) {
            System.out.print(message);
            String value = input.nextLine().trim();

            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    // Read non-empty text
    public static String readNonEmptyString(String message) {

        while (true) {
            System.out.print(message);
            String value = input.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty!");
        }
    }
}
