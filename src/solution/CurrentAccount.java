package solution;

public class CurrentAccount extends Account
{
    CurrentAccount(String n, int num, String date, int bal) {
        super(n, num, date, bal);
    }
    void display() {
        System.out.println("Current Account - Name: " + name + ", Account Number: " + number +
                ", Creation Date: " + creationDate + ", Balance: " + balance);
    }
    void updateBalance(int amount) {
        balance += amount;
    }
}
