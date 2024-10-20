import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class csvReader {

    // Lists to store each column's data
    List<String> idList = new ArrayList<>();
    List<String> firstNameList = new ArrayList<>();
    List<String> lastNameList = new ArrayList<>();
    List<String> dobList = new ArrayList<>();
    List<String> addressList = new ArrayList<>();
    List<String> phoneList = new ArrayList<>();
    List<String> checkingAccNumList = new ArrayList<>();
    List<String> checkingBalanceList = new ArrayList<>();
    List<String> savingsAccNumList = new ArrayList<>();
    List<String> savingsBalanceList = new ArrayList<>();
    List<String> creditAccNumList = new ArrayList<>();
    List<String> creditMaxList = new ArrayList<>();
    List<String> creditBalanceList = new ArrayList<>();

    // Method to read from the CSV file
    public void readCSV(String csvFileName) {
        try {
            FileReader reader = new FileReader(csvFileName);
            BufferedReader bfreader = new BufferedReader(reader);
            String line;
            String[] data;

            // Clear the lists in case they were already populated
            clearData();

            // Skip the header row
            bfreader.readLine();

            // Read each row and split it into individual fields
            while ((line = bfreader.readLine()) != null) {
                data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Handle commas in quotes

                // Add each value to the respective list
                idList.add(data[0]);
                firstNameList.add(data[1]);
                lastNameList.add(data[2]);
                dobList.add(data[3]);
                addressList.add(data[4]);
                phoneList.add(data[5]);
                checkingAccNumList.add(data[6]);
                checkingBalanceList.add(data[7]);
                savingsAccNumList.add(data[8]);
                savingsBalanceList.add(data[9]);
                creditAccNumList.add(data[10]);
                creditMaxList.add(data[11]);
                creditBalanceList.add(data[12]);
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to write the updated data back to the CSV file
    public void writeToCSV(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            // Write the header row
            writer.write("Identification Number,First Name,Last Name,Date of Birth,Address,Phone Number,Checking Account Number,Checking Starting Balance,Savings Account Number,Savings Starting Balance,Credit Account Number,Credit Max,Credit Starting Balance");
            writer.newLine();  // Move to the next line

            // Loop through the lists and write each row of data
            for (int i = 0; i < idList.size(); i++) {
                writer.write(idList.get(i) + "," + 
                             firstNameList.get(i) + "," + 
                             lastNameList.get(i) + "," + 
                             dobList.get(i) + "," + 
                             addressList.get(i) + "," +  // Address may contain commas, so we quote it
                             phoneList.get(i) + "," + 
                             checkingAccNumList.get(i) + "," + 
                             checkingBalanceList.get(i) + "," + 
                             savingsAccNumList.get(i) + "," + 
                             savingsBalanceList.get(i) + "," + 
                             creditAccNumList.get(i) + "," + 
                             creditMaxList.get(i) + "," + 
                             creditBalanceList.get(i));
                writer.newLine();  // Move to the next line for the next record
            }

            writer.close();
            System.out.println("CSV file updated successfully!");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to print the data for testing purposes
    public void printData() {
        for (int i = 0; i < idList.size(); i++) {
            System.out.println(idList.get(i) + "\t" +
                               firstNameList.get(i) + "\t" +
                               lastNameList.get(i) + "\t" +
                               dobList.get(i) + "\t" +
                               addressList.get(i) + "\t" +
                               phoneList.get(i) + "\t" +
                               checkingAccNumList.get(i) + "\t" +
                               checkingBalanceList.get(i) + "\t" +
                               savingsAccNumList.get(i) + "\t" +
                               savingsBalanceList.get(i) + "\t" +
                               creditAccNumList.get(i) + "\t" +
                               creditMaxList.get(i) + "\t" +
                               creditBalanceList.get(i));
        }
    }

    // Method to clear the data before reading
    private void clearData() {
        idList.clear();
        firstNameList.clear();
        lastNameList.clear();
        dobList.clear();
        addressList.clear();
        phoneList.clear();
        checkingAccNumList.clear();
        checkingBalanceList.clear();
        savingsAccNumList.clear();
        savingsBalanceList.clear();
        creditAccNumList.clear();
        creditMaxList.clear();
        creditBalanceList.clear();
    }
    
    public List<String> getIdList() {
        return idList;
    }

    public List<String> getFirstNameList() {
        return firstNameList;
    }

    public List<String> getLastNameList() {
        return lastNameList;
    }

    public List<String> getDobList() {
        return dobList;
    }

    public List<String> getAddressList() {
        return addressList;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public List<String> getCheckingAccNumList() {
        return checkingAccNumList;
    }

    public List<String> getCheckingBalanceList() {
        return checkingBalanceList;
    }

    public List<String> getSavingsAccNumList() {
        return savingsAccNumList;
    }

    public List<String> getSavingsBalanceList() {
        return savingsBalanceList;
    }

    public List<String> getCreditAccNumList() {
        return creditAccNumList;
    }

    public List<String> getCreditMaxList() {
        return creditMaxList;
    }

    public List<String> getCreditBalanceList() {
        return creditBalanceList;
    }
}
