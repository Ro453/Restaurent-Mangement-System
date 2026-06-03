public class Waiter {


    private String name;


    public Waiter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void takeOrder(String foodName) {
        System.out.println(name + " took the order for " + foodName);
    }


    public void displayInfo() {
        System.out.println("Waiter Name: " + name);
    }
}