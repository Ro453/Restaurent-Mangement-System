public class Drive {

    public static void main(String[] args) {

        Restaurant r = new Restaurant("Food Palace", "Dhaka");

        Order o = new Order(1);

        o.addItem(250);
        o.addItem(180);

        r.addOrder(o.getTotal());

        r.displayInfo();
        o.displayInfo();

        System.out.println("Restaurant Name: " + r.getName());
        System.out.println("Location: " + r.getLocation());
        System.out.println("Order ID: " + o.getOrderId());
        System.out.println("Order Total: " + o.getTotal());
        System.out.println("Total Sales: " + r.getTotalSales());


        /*Monira*/
        Fooditem item = new Fooditem("Burger", 250.0) {
            @Override
            public double totalPrice() {
                // AbstractionTest.java
                return this.getItemPrice();
            }
        };
        System.out.println("Item Name: " + item.getItemName());
        System.out.println("Total Price: " + item.totalPrice());



        /*Marzia*/
        Waiter waiter1 = new Waiter("Maherin");

        waiter1.displayInfo();
        waiter1.takeOrder("Pizza");

        /*Nila*/
        Chef chef1 = new Chef("Nila", "Pizza");

        chef1.displayInfo();
        chef1.cook();

 // Dessert Example
        Dessert dessert = new Dessert("Ice Cream", 150.0);
        System.out.println("Dessert Name: " + dessert.getItemName());
        System.out.println("Dessert Price: " + dessert.totalPrice());

        // MainCourse Example
        MainCourse mainCourse = new MainCourse("Biryani", 300.0);
        System.out.println("Main Course Name: " + mainCourse.getItemName());
        System.out.println("Main Course Price: " + mainCourse.totalPrice());




    }
}