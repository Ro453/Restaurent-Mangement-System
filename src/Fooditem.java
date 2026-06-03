public abstract class Fooditem {
    private String itemName;
    private double itemPrice;

    public Fooditem(String itemName, double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
    public abstract double totalPrice();

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }
}