package solution;

public class SalaryAccount extends Account
{
    SalaryAccount(String n, int num, String date, int bal) {
        super(n, num, date, bal);
    }
    void display() {
        System.out.println("Salary Account - Name: " + name + ", Account Number: " + number +
                ", Creation Date: " + creationDate + ", Balance: " + balance);
    }
    void updateBalance(int amount) {
        balance += amount;
    }
}
