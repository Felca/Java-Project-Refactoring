package solution;

public abstract class Account
{
    String name, creationDate;
    int number, balance;
    Account(String n, int num, String date, int bal) {
        name = n;
        number = num;
        creationDate = date;
        balance = bal;
    }

    abstract void display();

    abstract void updateBalance(int amount);
}
