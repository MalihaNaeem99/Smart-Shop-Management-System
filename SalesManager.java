
public class SalesManager {

    private Sale[] sales = new Sale[200];

    private int count = 0;

    private ProductManager productManager;
    private InventoryManager inventoryManager;

    // Constructor
    public SalesManager(ProductManager productManager,
                        InventoryManager inventoryManager) {

        this.productManager = productManager;
        this.inventoryManager = inventoryManager;
    }

    // 1. RECORD NEW SALE
    public void recordSale(int productId, int quantitySold) {

        if (count == sales.length) {
            System.out.println("Sales records are full!");
            return;
        }

        if (productId <= 0 || quantitySold <= 0) {
            System.out.println("Invalid product ID or quantity!");
            return;
        }

        Product p = productManager.getProductById(productId);

        if (p == null) {
            System.out.println("Product not found!");
            return;
        }

        if (p.getQuantity() < quantitySold) {
            System.out.println("Insufficient stock!");
            return;
        }

        if (p.getPrice() <= 0 ||
            Double.isNaN(p.getPrice()) ||
            Double.isInfinite(p.getPrice())) {

            System.out.println("Invalid product price!");
            return;
        }

        double totalAmount = p.getPrice() * quantitySold;

        if (Double.isInfinite(totalAmount) ||
            Double.isNaN(totalAmount)) {

            System.out.println("Total amount is too large!");
            return;
        }

        // Generate sale ID
        int saleId = count + 1;

        // Create sale record
        Sale newSale;

        try {
            newSale = new Sale(
                saleId,
                productId,
                p.getProductName(),
                quantitySold,
                totalAmount
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to create sale record!");
            return;
        }

        // Reduce stock
        boolean updated = inventoryManager.reduceStock(
            productId, quantitySold
        );

        if (!updated) {
            return;
        }

        // Store sale
        sales[count] = newSale;
        count++;

        System.out.println("\nSale recorded successfully!");

        generateInvoice(newSale);
    }

    // 2. GENERATE INVOICE
    public void generateInvoice(Sale s) {

        if (s == null) {
            System.out.println("Invalid invoice!");
            return;
        }

        System.out.println("\n================================");
        System.out.println("          SMART SHOP");
        System.out.println("             INVOICE");
        System.out.println("================================");

        System.out.println("Sale ID: " + s.getSaleId());
        System.out.println("Product ID: " + s.getProductId());
        System.out.println("Product Name: " + s.getProductName());
        System.out.println("Quantity: " + s.getQuantitySold());

        System.out.println("--------------------------------");

        System.out.println("Total Amount: Rs. " +
            s.getTotalAmount());

        System.out.println("================================");
        System.out.println("       THANK YOU!");
    }

    // 3. VIEW ALL SALES
    public void displaySales() {

        if (count == 0) {
            System.out.println("No sales records available!");
            return;
        }

        System.out.println("\n===== SALES RECORDS =====");

        System.out.println(
            "Sale ID\tProduct ID\tProduct\tQuantity\tAmount"
        );

        for (int i = 0; i < count; i++) {

            System.out.println(
                sales[i].getSaleId() + "\t" +
                sales[i].getProductId() + "\t\t" +
                sales[i].getProductName() + "\t" +
                sales[i].getQuantitySold() + "\t\t" +
                sales[i].getTotalAmount()
            );
        }
    }

    // 4. GET TOTAL SALES
    public double getTotalSales() {

        double total = 0;

        for (int i = 0; i < count; i++) {
            total += sales[i].getTotalAmount();
        }

        return total;
    }
}
