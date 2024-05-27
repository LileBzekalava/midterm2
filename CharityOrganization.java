import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class CharityOrganization {
    private String name;
    private List<Beneficiary> beneficiaries;

    // Constructor
    public CharityOrganization(String name) {
        this.name = name;
        this.beneficiaries = new ArrayList<>();
    }

    // Method to add a beneficiary
    public void addBeneficiary(Beneficiary beneficiary) {
        beneficiaries.add(beneficiary);
    }

    // Method to remove a beneficiary
    public void removeBeneficiary(Beneficiary beneficiary) {
        beneficiaries.remove(beneficiary);
    }

    // Method to get the list of beneficiaries
    public List<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    // Method to save state to a CSV file
    public void saveState() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/USER/ideaProjects/state.csv"))) {
            for (Beneficiary beneficiary : beneficiaries) {
                writer.write(beneficiary.toCSV());
                writer.newLine();
            }
            System.out.println("State saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving state: " + e.getMessage());
        }
    }

    // Method to restore state from a CSV file
    public void restoreState() {
        beneficiaries.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("state.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                beneficiaries.add(Beneficiary.fromCSV(line));
            }
        } catch (IOException e) {
            System.err.println("Error restoring state: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Charity Organization: ").append(name).append("\nBeneficiaries:\n");
        for (Beneficiary beneficiary : beneficiaries) {
            sb.append(beneficiary.toString()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CharityOrganization charity = new CharityOrganization("Helping Hands");

        Beneficiary beneficiary1 = new Beneficiary("John Doe", 30, "Medical support");
        Beneficiary beneficiary2 = new Beneficiary("Jane Smith", 25, "Educational support");

        // Adding beneficiaries
        charity.addBeneficiary(beneficiary1);
        charity.addBeneficiary(beneficiary2);

        // Save state to file
        charity.saveState();

        // Clear current state and print to show it's empty
        charity.getBeneficiaries().clear();
        System.out.println("After clearing beneficiaries:");
        System.out.println(charity);

        // Restore state from file
        charity.restoreState();
        System.out.println("After restoring state:");
        System.out.println(charity);
    }
}