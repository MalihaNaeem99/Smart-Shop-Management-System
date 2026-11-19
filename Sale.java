
public class Sale {

    private int saleId;
    private int productId;
    private String productName;
    private int quantitySold;
    private double totalAmount;

    // Constructor
    public Sale(int saleId, int productId, String productName,
                int quantitySold, double totalAmount) {

        if (saleId <= 0 || productId <= 0) {
            throw new IllegalArgumentException("Invalid ID!");
        }

        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }

        if (quantitySold <= 0) {
            throw new IllegalArgumentException("Invalid quantity sold!");
        }

        if (totalAmount < 0 || Double.isNaN(totalAmount) ||
            Double.isInfinite(totalAmount)) {
            throw new IllegalArgumentException("Invalid total amount!");
        }

        this.saleId = saleId;
        this.productId = productId;
        this.productName = productName.trim();
        this.quantitySold = quantitySold;
        this.totalAmount = totalAmount;
    }

    // Getters
    public int getSaleId() {
        return saleId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
