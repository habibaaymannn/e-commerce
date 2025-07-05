import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    public static void shipItems(List<shippable> items) {
        if (items.isEmpty()) return;

        System.out.println("** Shipment notice **");

        Map<String, Double> weightMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();
        double totalWeight = 0;

        for (shippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();

            countMap.put(name, countMap.getOrDefault(name, 0) + 1);
            weightMap.put(name, weight);
            totalWeight += weight;
        }

        for (String name : countMap.keySet()) {
            int count = countMap.get(name);
            double weight = weightMap.get(name);
            System.out.printf("%dx %s %.0fg%n", count, name, weight * count);
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight / 1000);
    }

}
