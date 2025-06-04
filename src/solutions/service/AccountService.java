package solutions.service;

import java.util.ArrayList;
import java.util.List;

import solutions.model.Account;
import solutions.model.Money;

public class AccountService {
    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
		return accounts;
	}
    
    public boolean isAccountNumberUnique(int accountNumber) {
    	for(Account account : accounts){
            if(account.getNumber() == accountNumber)
            {
                return false;
            }
        }
        return true;
    }

    public boolean createAccount(int type, String name, int number, String date, int balanceAmount) {
        Account.AccountType accountType;

        switch (type) {
            case 1:
                accountType = Account.AccountType.SALARY;
                break;
            case 2:
                accountType = Account.AccountType.SAVING;
                break;
            case 3:
                accountType = Account.AccountType.CURRENT;
                break;
            default:
                System.out.println("Invalid choice!");
                return false;
        }
        
        Money balance = new Money(balanceAmount);

        Account account = new Account(accountType, name, number, date, balance);
        accounts.add(account);
        return true;
    }


    public void displayAllAccounts() {
        for (Account account : accounts) {
            account.display();
        }
    }

    public void updateAccountBalance(Account acc, int amount) {
    	acc.updateBalance(amount);
    }

    public boolean deleteAccount(int accountNumber) {
    	Account acc = getAccountByNumber(accountNumber);
    	if (acc != null) {
    		accounts.remove(acc);
            return true;
    	}
    	
        return false;
    }

    public void depositAmount(Account acc, int amount) {
        acc.updateBalance(amount);
    }

    public void withdrawAmount(Account acc, int amount) {
        acc.updateBalance(-amount);
    }

    public Account getAccountByNumber(int accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                return account;
            }
        }
        System.out.println("Acount not found!\n");
        return null;
    }
}
