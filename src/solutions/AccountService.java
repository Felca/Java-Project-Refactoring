package solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import solutions.Account.AccountType;

public class AccountService {
    private List<Account> accounts;
    
    public AccountService() {
        this.accounts = new ArrayList<>();
    }
    
    public boolean isAccountNumberUnique(int accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isAccountExist(int accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                return true;
            }
        }
        return false;
    }
    
    public boolean createAccount(int minBalanceToOpen) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Select account type\n1. Salary Account\n2. Savings Account\n3. Current Account\n");
        int choice = scanner.nextInt();
        scanner.nextLine();
          
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        int number;
        do {
            System.out.print("Enter account number: ");
            number = scanner.nextInt();
            if (!isAccountNumberUnique(number)) {
                System.out.println("Account Number exists! Please use different number");
            }
        } while (!isAccountNumberUnique(number));
        
        System.out.print("Enter creation date: ");
        String creationDate = scanner.next();
        
        int balance;
        do {
            System.out.print("Enter account balance: ");
            balance = scanner.nextInt();
            if (balance < minBalanceToOpen) {
                System.out.println("Opening Balance must be at least " + minBalanceToOpen + ".");
            }
        } while (balance < minBalanceToOpen);
        
        Account account = null;
        switch (choice) {
            case 1:
                account = new Account(AccountType.SALARY, name, number, creationDate, balance);
                break;
            case 2:
                account = new Account(AccountType.SAVING, name, number, creationDate, balance);
                break;
            case 3:
                account = new Account(AccountType.CURRENT, name, number, creationDate, balance);
                break;
            default:
                System.out.println("Invalid choice!");
                return false;
        }
        
        accounts.add(account);
        System.out.println("Account successfully created.");
        return true;
    }
    
    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts to display.");
            return;
        }
        
        for (Account account : accounts) {
            account.display();
        }
    }
    
    public boolean updateAccountBalance(int accountNumber) {
        if (!isAccountExist(accountNumber)) {
            System.out.println("Account not found!");
            return false;
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to update balance (positive or negative): ");
        int amount = scanner.nextInt();
        
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                account.updateBalance(amount);
                System.out.println("Balance updated successfully!");
                return true;
            }
        }
        
        return false;
    }
    
    public boolean deleteAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                accounts.remove(account);
                System.out.println("Account deleted successfully!");
                return true;
            }
        }
        
        System.out.println("Account not found!");
        return false;
    }
    
    public boolean depositAmount(int accountNumber) {
        if (!isAccountExist(accountNumber)) {
            System.out.println("Account not found!");
            return false;
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to be deposited: ");
        int amount = scanner.nextInt();
        
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive!");
            return false;
        }
        
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                account.updateBalance(amount);
                System.out.println("Amount deposited successfully!");
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isPossibleWithdraw(int accountNumber, int amount, int minBalanceRemainAfterWithdraw) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                int remainingBalance = account.getBalance() - amount;
                return remainingBalance >= minBalanceRemainAfterWithdraw;
            }
        }
        return false;
    }
    
    public boolean withdrawAmount(int accountNumber, int minBalanceRemainAfterWithdraw) {
        if (!isAccountExist(accountNumber)) {
            System.out.println("Account not found!");
            return false;
        }
        
        Scanner scanner = new Scanner(System.in);
        int amount;
        
        while (true) {
            System.out.print("Enter amount to be withdrawn: ");
            amount = scanner.nextInt();
            
            if (amount <= 0) {
                System.out.println("Withdrawal amount must be positive!");
                continue;
            }
            
            if (!isPossibleWithdraw(accountNumber, amount, minBalanceRemainAfterWithdraw)) {
                System.out.println("Cannot withdraw. Minimum balance requirement not met.");
            } else {
                break;
            }
        }
        
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                account.updateBalance(-amount);
                System.out.println("Successfully withdrawn amount!");
                return true;
            }
        }
        
        return false;
    }
    
    public void searchAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                System.out.println("Account found!");
                account.display();
                return;
            }
        }
        
        System.out.println("Account not found!");
    }
}
