public class Chef {


    private String name;
    private String specialty;


    public Chef(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }


    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }


    public void cook() {
        System.out.println(name + " is cooking " + specialty + ".");
    }


    public void displayInfo() {
        System.out.println("Chef Name: " + name);
        System.out.println("Specialty: " + specialty);
    }
}