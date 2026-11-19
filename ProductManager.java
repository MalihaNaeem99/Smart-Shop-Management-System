
public class ProductManager {

    // Product array
    private Product[] products = new Product[100];

    private int count = 0;

    // 1. INSERTION
    public void addProduct(Product p) {

        if (p == null) {
            System.out.println("Invalid product!");
            return;
        }

        if (count == products.length) {
            System.out.println("Product storage is full!");
            return;
        }

        if (p.getProductId() <= 0 ||
            p.getProductName() == null ||
            p.getProductName().trim().isEmpty() ||
            p.getPrice() <= 0 ||
            Double.isNaN(p.getPrice()) ||
            Double.isInfinite(p.getPrice()) ||
            p.getQuantity() < 0) {

            System.out.println("Invalid product information!");
            return;
        }

        // Check duplicate ID
        for (int i = 0; i < count; i++) {

            if (products[i].getProductId() == p.getProductId()) {
                System.out.println("Product ID already exists!");
                return;
            }
        }

        products[count] = p;
        count++;

        System.out.println("Product added successfully!");
    }

    // 2. TRAVERSAL
    public void displayProducts() {

        if (count == 0) {
            System.out.println("No products available!");
            return;
        }

        System.out.println("\nID\tName\tPrice\tQuantity");

        for (int i = 0; i < count; i++) {

            System.out.println(
                products[i].getProductId() + "\t" +
                products[i].getProductName() + "\t" +
                products[i].getPrice() + "\t" +
                products[i].getQuantity()
            );
        }
    }

    // 3. SEARCHING - LINEAR SEARCH
    public int searchProduct(int id) {

        for (int i = 0; i < count; i++) {

            if (products[i].getProductId() == id) {
                return i;
            }
        }

        return -1;
    }

    // Display searched product
    public void findProduct(int id) {

        int index = searchProduct(id);

        if (index == -1) {
            System.out.println("Product not found!");
        } else {

            System.out.println("Product Found!");

            System.out.println("ID: " +
                products[index].getProductId());

            System.out.println("Name: " +
                products[index].getProductName());

            System.out.println("Price: " +
                products[index].getPrice());

            System.out.println("Quantity: " +
                products[index].getQuantity());
        }
    }

    // 4. UPDATING
    public void updateProduct(int id, String name,
                              double price, int quantity) {

        int index = searchProduct(id);

        if (index == -1) {
            System.out.println("Product not found!");
            return;
        }

        if (name == null || name.trim().isEmpty() ||
            price <= 0 ||
            Double.isNaN(price) ||
            Double.isInfinite(price) ||
            quantity < 0) {

            System.out.println("Invalid information!");
            return;
        }

        products[index].setProductName(name);
        products[index].setPrice(price);
        products[index].setQuantity(quantity);

        System.out.println("Product updated successfully!");
    }

    // 5. DELETION
    public void deleteProduct(int id) {

        int index = searchProduct(id);

        if (index == -1) {
            System.out.println("Product not found!");
            return;
        }

        for (int i = index; i < count - 1; i++) {
            products[i] = products[i + 1];
        }

        products[count - 1] = null;
        count--;

        System.out.println("Product deleted successfully!");
    }

    // 6. SORTING - BUBBLE SORT
    public void sortProducts() {

        if (count == 0) {
            System.out.println("No products available to sort!");
            return;
        }

        for (int i = 0; i < count - 1; i++) {

            for (int j = 0; j < count - i - 1; j++) {

                if (products[j].getPrice() >
                    products[j + 1].getPrice()) {

                    Product temp = products[j];

                    products[j] = products[j + 1];

                    products[j + 1] = temp;
                }
            }
        }

        System.out.println("Products sorted by price!");
        displayProducts();
    }

    // Get product for other modules
    public Product getProductById(int id) {

        int index = searchProduct(id);

        if (index == -1) {
            return null;
        }

        return products[index];
    }

    // Get total product count
    public int getProductCount() {
        return count;
    }
}
