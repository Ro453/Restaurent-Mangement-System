class  Restaurant {

    private String name;
    private String location;
    private double totalSales;


    public Restaurant(String name, String location) {
        this.name = name;
        this.location = location;
        totalSales = 0;
    }


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getTotalSales() {
        return totalSales;
    }


    public void addOrder(double amount) {
        totalSales = totalSales + amount;
    }


    public void displayInfo() {
    /*Restaurant r = new Restaurant("Spicy Corner", "Dhaka");
    r.addOrder(500);*/

        System.out.println("Restaurant: " + name);
        System.out.println("Location: " + location);
        System.out.println("Sales: " + totalSales);
    }
}