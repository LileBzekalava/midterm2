import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Beneficiary {
    private String name;
    private int age;
    private String needs;

    // Constructor
    public Beneficiary(String name, int age, String needs) {
        this.name = name;
        this.age = age;
        this.needs = needs;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNeeds() {
        return needs;
    }

    @Override
    public String toString() {
        return "Beneficiary(Name: " + name + ", Age: " + age + ", Needs: " + needs + ")";
    }

    // Static method to create a Beneficiary from a CSV line
    public static Beneficiary fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Beneficiary(parts[0], Integer.parseInt(parts[1]), parts[2]);
    }

    // Method to convert Beneficiary to CSV line
    public String toCSV() {
        return name + "," + age + "," + needs;
    }
}

