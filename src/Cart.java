import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }


    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive");
            return;
        }
        if (product.getAvailQuantity() < quantity) {
            System.out.println("Not enough quantity for product: " + product.getName());
            return;
        }
        if (product instanceof Expirable && ((Expirable) product).isExpired()) {
            System.out.println("Product is expired: " + product.getName());
            return;
        }
        items.merge(product, quantity, Integer::sum);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }
    public List<shippable> getShippableItems() {
        List<shippable> result = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            if (product instanceof shippable) {
                for (int i = 0; i < qty; i++) {
                    result.add((shippable) product);
                }
            }
        }
        return result;
    }
    public double calculateSubtotal() {
        double subtotal = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            subtotal += entry.getKey().getPrice() * entry.getValue();
        }
        return subtotal;
    }
}
