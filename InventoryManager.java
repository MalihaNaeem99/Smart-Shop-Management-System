
public class InventoryManager {

    private ProductManager productManager;

    // Constructor
    public InventoryManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    // 1. VIEW AVAILABLE STOCK
    public void viewStock() {

        if (productManager.getProductCount() == 0) {
            System.out.println("No stock available!");
            return;
        }

        System.out.println("\n===== AVAILABLE STOCK =====");

        productManager.displayProducts();
    }

    // 2. UPDATE STOCK
    public void updateStock(int productId, int newQuantity) {

        Product p = productManager.getProductById(productId);

        if (p == null) {
            System.out.println("Product not found!");
            return;
        }

        if (newQuantity < 0) {
            System.out.println("Invalid quantity!");
            return;
        }

        p.setQuantity(newQuantity);

        System.out.println("Stock updated successfully!");
    }

    // 3. REDUCE STOCK AFTER SALE
    public boolean reduceStock(int productId, int soldQuantity) {

        Product p = productManager.getProductById(productId);

        if (p == null) {
            System.out.println("Product not found!");
            return false;
        }

        if (soldQuantity <= 0) {
            System.out.println("Invalid sale quantity!");
            return false;
        }

        if (p.getQuantity() < soldQuantity) {
            System.out.println("Insufficient stock!");
            return false;
        }

        int remainingStock = p.getQuantity() - soldQuantity;

        p.setQuantity(remainingStock);

        System.out.println("Stock reduced successfully!");

        return true;
    }
}

