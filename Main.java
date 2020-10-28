//1715821
package coursework3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static Scanner keyboard = new Scanner(System.in);
    static PrintWriter printWriter;

    static boolean isInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean isValidDay(int day) {
        return day >= 1 && day <= 30;
    }

    static void mainMenu(Database database) {
        while (true) {
            System.out.println("1. Search customer");
            System.out.println("2. Exit");
            System.out.print("Enter a selection: ");
            String input = keyboard.nextLine().trim();
            if (input.equals("1")) {
                Customer customer = searchMenu(database);
                if (customer != null) {
                    customerMenu(database, customer);
                }
            } else if (input.equals("2")) {
                break;
            }
            System.out.println();
        }
    }

    static Customer searchMenu(Database database) {
        System.out.print("Enter a keyword: ");
        String keywords = keyboard.nextLine().toLowerCase().toUpperCase().trim();
        List<Customer> result = new ArrayList<>();
        for (Customer customer : database.getCustomers()) {
            if (customer.getFamilyName().toLowerCase().toUpperCase().contains(keywords)) {
                result.add(customer);
            }
        }

        System.out.println("No.   Id          Family Name     Given Name      Balance");
        for (int i = 0; i < result.size(); i++) {
            Customer customer = result.get(i);
            System.out.println(String.format(
                    "%-6d%-12s%-16s%-16s%.2f",
                    i + 1,
                    customer.getId(),
                    customer.getFamilyName(),
                    customer.getGivenName(),
                    customer.getBalance()
            ));
        }
        System.out.print("Enter number (0 means cancel.): ");
        String input = keyboard.nextLine().trim();
        if (isInt(input)) {
            int num = Integer.parseInt(input);
            if (num >= 1 && num <= result.size()) {
                Customer customer = result.get(num - 1);
                return customer;
            } else if (num == 0) {

            } else {
                System.out.println("Invalid selection.");
            }
        }
        return null;

    }

    static void customerMenu(Database database, Customer customer) {
        while (true) {
            System.out.println("1. Display transaction information");
            System.out.println("2. Display balance information");
            System.out.println("0. Go back");
            System.out.print("Enter a selection: ");
            String input = keyboard.nextLine().trim();
            if (input.equals("1")) {
                transactionTimeRangeMenu(database, customer);
            } else if (input.equals("2")) {
                balanceTimeRangeMenu(database, customer);
            } else if (input.equals("0")) {
                break;
            } else {
                System.out.println("Invalid selection!");
            }
            System.out.println();
        }
    }

    static TimeRange timeRangeMenu() {
        while (true) {
            System.out.println("1. Whole month");
            System.out.println("2. A single day");
            System.out.println("3. A time range in month");
            System.out.print("Enter a selection: ");
            String input = keyboard.nextLine().trim();
            if (input.equals("1")) {
                return new TimeRange(true, 1, 30);
            } else if (input.equals("2")) {
                int day=1;
                while (true) {
                    System.out.print("Enter a day: ");
                    String line = keyboard.nextLine().trim();
                    if (isInt(line)) {
                        day = Integer.parseInt(line);
                        if (isValidDay(day)) {
                            break;
                        }
                    }
                }
                return new TimeRange(false, day, day);
            } else if (input.equals("3")) {
                int startday = 1;
                int endday = 1;
                while (true) {
                    while (true) {
                        System.out.print("Enter a start day: ");
                        String line = keyboard.nextLine().trim();
                        if (isInt(line)) {
                            startday = Integer.parseInt(line);
                            if (isValidDay(startday)) {
                                break;
                            }
                        }
                    }

                    while (true) {
                        System.out.print("Enter an end day: ");
                        String line = keyboard.nextLine().trim();
                        if (isInt(line)) {
                            endday = Integer.parseInt(line);
                            if (isValidDay(endday)) {
                                break;
                            }
                        }
                    }
                    if (endday >= startday) {
                        break;
                    }
                }
                return new TimeRange(false, startday, endday);
            } else {
                System.out.println("Invalid selection!");
            }
            System.out.println();
        }

    }

    static void transactionTimeRangeMenu(Database database, Customer customer) {
        TimeRange timeRange = timeRangeMenu();
        List<Transaction> result = new ArrayList<>();
        List<Double> spends = new ArrayList<>();
        List<Double> incomes = new ArrayList<>();
        double totalSpends = 0;
        double totalIncomes = 0;

        printWriter.println("Transaction Statistics");
        printWriter.println();
        if (timeRange.isWholeMonth()) {
            printWriter.println("Time range: Whole month");
        } else if (timeRange.getStart() == timeRange.getEnd()) {
            printWriter.println(String.format("Time range: %d", timeRange.getStart()));
        } else {
            printWriter.println(String.format("Time range: %d-%d", timeRange.getStart(), timeRange.getEnd()));
        }
        printWriter.println();

        for (Transaction transaction : database.transactions) {
            if (transaction.getCustomerId().equals(customer.getId())) {
                if (timeRange.isInTimeRange(transaction.getDay())) {
                    result.add(transaction);
                    if (transaction.getAmount() < 0) {
                        spends.add(-transaction.getAmount());
                        totalSpends += -transaction.getAmount();
                    }
                    if (transaction.getAmount() > 0) {
                        incomes.add(transaction.getAmount());
                        totalIncomes += transaction.getAmount();
                    }
                }
            }
        }

        if (spends.size() > 0 || incomes.size() > 0) {
            spends.sort(null);
            incomes.sort(null);
            System.out.println(String.format("Min spend %.2f", spends.get(0)));
            System.out.println(String.format("Max spend %.2f", spends.get(spends.size() - 1)));
            System.out.println(String.format("Total spend %.2f", totalSpends));
            System.out.println(String.format("Average spend %.2f", totalSpends / spends.size()));
            System.out.println(String.format("Total income %.2f", totalIncomes));

            printWriter.println(String.format("Min spend %.2f", spends.get(0)));
            printWriter.println(String.format("Max spend %.2f", spends.get(spends.size() - 1)));
            printWriter.println(String.format("Total spend %.2f", totalSpends));
            printWriter.println(String.format("Average spend %.2f", totalSpends / spends.size()));
            printWriter.println(String.format("Total income %.2f", totalIncomes));
        } else {
            System.out.println("No spends");
            printWriter.println("No spends");
        }
        System.out.println();
        printWriter.println();

    }

    static void balanceTimeRangeMenu(Database database, Customer customer) {
        TimeRange timeRange = timeRangeMenu();

        printWriter.println("Balance Statistics");
        printWriter.println();
        if (timeRange.isWholeMonth()) {
            printWriter.println("Time range: Whole month");
        } else if (timeRange.getStart() == timeRange.getEnd()) {
            printWriter.println(String.format("Time range: %d", timeRange.getStart()));
        } else {
            printWriter.println(String.format("Time range: %d-%d", timeRange.getStart(), timeRange.getEnd()));
        }
        printWriter.println();

        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : database.transactions) {
            if (transaction.getCustomerId().equals(customer.getId())) {
                if (timeRange.isInTimeRange(transaction.getDay())) {
                    result.add(transaction);
                }
            }
        }

        if (result.size() > 0) {
            result.sort(new Comparator<Transaction>() {
                @Override
                public int compare(Transaction o1, Transaction o2) {
                    Integer d1 = o1.getDay();
                    Integer d2 = o2.getDay();
                    return d1.compareTo(d2);
                }
            });
            
            double[] dayTrans = new double[31];
            List<Double>balances = new ArrayList<>();
            for (Transaction transaction : result) {
                dayTrans[transaction.getDay()] += transaction.getAmount();            
            }
            double curBalance = customer.getBalance();                       
            balances.add(curBalance);
            dayTrans[0] = curBalance;
            for (int day = 1; day < timeRange.getEnd(); day++) {
                curBalance = curBalance + dayTrans[day];
                balances.add(curBalance);                         
            }
            int overdraftDay = 0;
            for (int i = 0; i < balances.size(); i++) {
                if (balances.get(i) < 0) {
                    overdraftDay++;
                }
            }
            
            System.out.println(String.format("Min balance: %.2f", Collections.min(balances)));
            System.out.println(String.format("Max balance: %.2f", Collections.max(balances)));
            System.out.println(String.format("Overdraft days: %d", overdraftDay));
            
            printWriter.println(String.format("Min balance: %.2f", balances.get(0)));
            printWriter.println(String.format("Max balance: %.2f", balances.get(balances.size() - 1)));
            printWriter.println(String.format("Overdraft days: %d", overdraftDay));

        } else {
            System.out.println("No transaction found!");
            printWriter.println("No transaction found!");
        }
        printWriter.println();
    }

    public static void main(String[] args) throws FileNotFoundException {
        printWriter = new PrintWriter(new File("out.txt"));
        Database database = new Database();
        database.loadCustomers();
        database.loadTransactions();
        mainMenu(database);
        printWriter.flush();
        printWriter.close();
    }
}
