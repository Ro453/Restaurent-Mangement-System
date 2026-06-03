// MainCourse.java
public class MainCourse extends Fooditem {
    public MainCourse(String itemName, double itemPrice) {
        super(itemName, itemPrice);
    }

    @Override
    public double totalPrice() {
        return getItemPrice();
    }
}