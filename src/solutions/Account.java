package solutions;

public class Account {
    enum AccountType { CURRENT, SALARY, SAVING }
    private AccountType type;
    private String name;
    private String creationDate;
    private int number;
    private int balance;
    
    public Account(AccountType type, String name, int number, String creationDate, int balance) {
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
    
    public int getBalance() {
        return balance;
    }
    
    public void updateBalance(int amount) {
        this.balance += amount;
    }
    
    public void display() {
        System.out.println(type + " Account - Name: " + name + 
                          ", Account Number: " + number +
                          ", Creation Date: " + creationDate + 
                          ", Balance: " + balance);
    }
}