import java.time.LocalDate;

public class ShippableItem extends Product implements shippable, Expirable{

    private double weight;
    private LocalDate expirationDate;

    public ShippableItem(String name, double price, double availQuantity, double weight, LocalDate expirationDate) {
        super(name, price, availQuantity);
        this.weight = weight;
        this.expirationDate = expirationDate;
    }
    @Override
    public String getName(){
        return super.getName();
    }
    @Override
    public double getWeight() {
        return weight ;
    }

    @Override
    public boolean isExpired() {
        return expirationDate != null && expirationDate.isBefore(LocalDate.now());    }
}
