import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Customer {
    private Cart cart;
    private double balance;

    public Customer(double balance) {
        this.cart = new Cart();
        this.balance = balance;
    }

    void addToCart(Product product, int quantity) {
        cart.add(product, quantity);
    }

    void checkout() {
        if (cart.isEmpty()) {
            System.out.println("cart is empty, please add a product");
            return;
        }
        double subtotal = cart.calculateSubtotal();
        List<shippable> shippables = cart.getShippableItems();
        double shipping = shippables.size() * 15; //15 shipping fees per shipping item
        double total = subtotal + shipping;

        if (total > balance) {
            System.out.println("Total shipping is greater than balance");
            return;
        }

        // Validate stock and expiration
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (quantity > product.getAvailQuantity()) {
                System.out.println("Not enough stock for product: " + product.getName());
                return;
            }
            if (product instanceof Expirable && ((Expirable) product).isExpired()) {
                System.out.println("Product is expired: " + product.getName());
                return;
            }

        }
        if (!shippables.isEmpty()) {
            ShippingService.shipItems(shippables);
        }

        // Print receipt
        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%dx %s %.0f%n", quantity, product.getName(), product.getPrice() * quantity);
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);
        System.out.printf("Shipping %.0f%n", shipping);
        System.out.printf("Amount %.0f%n", total);
        System.out.printf("Customer balance %.0f%n", balance - total);

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            entry.getKey().reduceQuantity(entry.getValue());
        }
        balance -= total;

        // Clear cart
        cart = new Cart();
    }
    public double getBalance() {return balance;}
}
