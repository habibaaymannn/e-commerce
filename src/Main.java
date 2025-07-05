import java.time.LocalDate;

public static void main(String[] args) {
    Product cheese = new ShippableItem("Cheese", 100, 5,400, LocalDate.now().plusDays(10));
    Product biscuits = new ShippableItem("Biscuits", 150, 3, 700,LocalDate.now().plusDays(5));
    Product tv = new ShippableItem("TV", 500, 2, 5000,null);
    Product scratchCard = new ShippableItem("Mobile Scratch Card", 50, 10,30, LocalDate.now().plusDays(30));

    Customer customer = new Customer(1000);
    customer.addToCart(cheese, 2);
    customer.addToCart(biscuits, 1);
    customer.addToCart(tv,1);
    customer.addToCart(scratchCard,1);

    customer.checkout();

}
