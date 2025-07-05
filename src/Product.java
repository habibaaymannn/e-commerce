public class Product{
    private String name;
    private double price;
    public double availQuantity;

    public Product(String name, double price, double availQuantity) {
        this.name = name;
        this.price = price;
        this.availQuantity = availQuantity;
    }

    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public double getAvailQuantity(){
        return availQuantity;
    }
    public void reduceQuantity(int quantity){
        if(quantity <= availQuantity){
            availQuantity -= quantity;
        }
    }
}
