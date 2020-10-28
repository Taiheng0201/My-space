//Taiheng.Zhang 1715821
package coursework2;

public class Subscription {

    private String name;
    private String code;
    private String description;
    private double price;

    //constructor
    public Subscription() {

    }

    public Subscription(String name, String code, String description, double price) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
    }

    //getter methods
    public String getName() {
        return name;
    }

    public String getCode() {

        return code;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return (code + ": " + name + " subscription. " + description + ". Yearly price: " + price + " RMB.");
    }
    //Purpose:Convenient to display the data and information of a subscription in neat.
}
