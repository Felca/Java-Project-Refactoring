package solution;

public class SavingsAccount extends Account
{
    SavingsAccount(String n, int num, String date, int bal) {
        super(n, num, date, bal);
    }
    void display() {
        System.out.println("Savings Account - Name: " + name + ", Account Number: " + number +
                ", Creation Date: " + creationDate + ", Balance: " + balance);
    }
    void updateBalance(int amount) {
        balance += amount;
    }
}
