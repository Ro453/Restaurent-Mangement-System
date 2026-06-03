public class Drive {

    public static void main(String[] args) {

      


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