// Dessert.java
public class Dessert extends Fooditem {
    public Dessert(String itemName, double itemPrice) {
        super(itemName, itemPrice);
    }

    @Override
    public double totalPrice() {
        return getItemPrice();
    }
}