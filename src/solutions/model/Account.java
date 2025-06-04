package solutions.model;

public class Account {
	public enum AccountType {
        CURRENT, SALARY, SAVING
    }
    private AccountType type;
    private String name;
    private String creationDate;
    private int number;
    private Money balance;
    
    public Account(AccountType type, String name, int number, String creationDate, Money balance) {
        this.type = type;
        this.name = name;
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
    }

    public AccountType getType(){
        return type;
    }
    
    public String getName() {
        return name;
    }
    
    public int getNumber() {
        return number;
    }
    
    public String getCreationDate() {
        return creationDate;
    }
    
    public Money getBalance() {
        return balance;
    }
    
    public void updateBalance(int amount) {
        if (amount >= 0) {
            balance = balance.add(new Money(amount));
        } 
        else {
            balance = balance.subtract(new Money(-amount));
        }
    }
    
    public void display() {
        System.out.println(type + " Account - Name: " + name + 
                          ", Account Number: " + number +
                          ", Creation Date: " + creationDate + 
                          ", Balance: " + balance);
    }
}