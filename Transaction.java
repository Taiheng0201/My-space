//1715821
package coursework3;

public class Transaction {

    private String customerId;
    private String transId;
    private String type;
    private double amount;
    private int day;

    public Transaction(String customerId, String transId, String type, double amount, int day) {
        this.customerId = customerId;
        this.transId = transId;
        this.type = type;
        this.amount = amount;
        this.day = day;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDay() {
        return day;
    }

    public void setMonth(int month) {
        this.day = day;
    }
}
