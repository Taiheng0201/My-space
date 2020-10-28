//1715821
package coursework3;

public class Customer {

    private String id;
    private String familyName;
    private String givenName;
    private double balance;

    public Customer(String id, String familyName, String givenName, double balance) {
        this.id = id;
        this.familyName = familyName;
        this.givenName = givenName;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
