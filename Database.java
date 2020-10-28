//1715821
package coursework3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {

    List<Customer> customers;
    List<Transaction> transactions;

    public Database() {
        customers = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void loadCustomers() throws FileNotFoundException {
        customers.clear();
        Scanner scanner = new Scanner(new File("customers.csv"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 4) {
                Customer customer = new Customer(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
                customers.add(customer);
            }
        }
        scanner.close();
    }

    public void loadTransactions() throws FileNotFoundException {
        transactions.clear();
        Scanner scanner = new Scanner(new File("transactions.csv"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 5) {
                Transaction transaction = new Transaction(
                        parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), Integer.parseInt(parts[4])
                );
                transactions.add(transaction);
            }
        }

        scanner.close();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
