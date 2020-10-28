//Taiheng.Zhang  1715821
package coursework2;

import java.util.Scanner;

public class Coursework2 {

    static Subscription eco = new Subscription("Economy", "ECO", "Max 10 videos per month", 200);
    static Subscription std = new Subscription("Standard", "STD", "Max 20 videos per month", 350);
    static Subscription prm = new Subscription("Premium", "PRM", "Max 50 videos per month", 500);
    static Subscription vip = new Subscription("VIP", "VIP", "Unlimited videos", 600);
    static Subscription[] subscriptionOptionsArray = {eco, std, prm, vip};
    static Scanner keyboard;

    public static void main(String[] args) {
        keyboard = new Scanner(System.in);
        showPackage();
        keyboard.close();

    }

    public static void showPackage() {
        System.out.println("Welcome to the NitFlex subscription service.");
        System.out.println("Please choose one of the subscription options from the list below.");
        System.out.println("The subscription options are:");
        System.out.println(eco.toString());
        System.out.println(std.toString());
        System.out.println(prm.toString());
        System.out.println(vip.toString());
        while (true) {
            System.out.print("Please input one of: ");
            for (Subscription subscription : subscriptionOptionsArray) {
                System.out.print(subscription.getCode() + " ");
            }
            System.out.println();
            String input = keyboard.nextLine();
            if (checkInput(input)) {
                String code = input.trim().toUpperCase();
                Subscription subscription = findSubscriptionFromCode(code);
                System.out.println("you chose the '" + subscription.getName() + "' subscription.");
                System.out.println("Please choose the length of your subscription");
                System.out.println("1 year(s): total price = " + subscription.getPrice() + " RMB.");
                System.out.println("2 year(s): total price = " + subscription.getPrice() * 2 * 0.9 + " RMB.");
                System.out.println("3 year(s): total price = " + subscription.getPrice() * 3 * 0.8 + " RMB.");
                while (true) {
                    System.out.println("Please input 1,2 or 3.");
                    String input1 = keyboard.nextLine();
                    if (yearchosen(input1)) {
                        int chooseYear = Integer.parseInt(input1.trim());
                        System.out.println("your chose a " + chooseYear + " year subscription period.");
                        System.out.println("Here is a summary of your subscription package.");
                        System.out.println("You chose the '" + subscription.getName() + "' subscription option.");
                        System.out.println("You have subscribed for a " + chooseYear + " year period.");
                        double totalPrice = 0.0;
                        if (chooseYear == 1) {
                            totalPrice = subscription.getPrice();
                        } else if (chooseYear == 2) {
                            totalPrice = subscription.getPrice() * 2 * 0.9;
                        } else if (chooseYear == 3) {
                            totalPrice = subscription.getPrice() * 3 * 0.8;
                        }
                        System.out.println("The total cost of your package is: " + totalPrice + " RMB.");
                        break;
                    } else {
                        System.out.println("Your input is wrong, please input again！");
                    }

                }
                break;

            } else {
                System.out.println("Your input is wrong, please input again！");
            }
        }

    }

    public static Subscription findSubscriptionFromCode(String code) {
        Subscription subscription = null;
        for (int i = 0; i < subscriptionOptionsArray.length; i++) {
            if (code.equals(subscriptionOptionsArray[i].getCode())) {
                subscription = subscriptionOptionsArray[i];
                break;
            }
        }
        return subscription;
    }

    public static boolean checkInput(String input) {
        boolean codeformat = false;
        for (Subscription subscription : subscriptionOptionsArray) {
            if (subscription.getCode().equalsIgnoreCase(input.trim())) {
                codeformat = true;
                break;
            }
        }
        return codeformat;

    }

    private static boolean yearchosen(String input1) {
        boolean yearformat = false;
        if (input1.trim().equals("1") || input1.trim().equals("2") || input1.trim().equals("3")) {
            yearformat = true;
        }
        return yearformat;
    }

}
