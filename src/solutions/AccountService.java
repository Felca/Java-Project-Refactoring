package solutions;

import java.util.ArrayList;
import java.util.List;

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

    public boolean createAccount(int type, String name, int number, String date, int balance) {
        Account account = null;
        switch (type) {
            case 1:
                account = new Account(Account.AccountType.SALARY, name, number, date, balance);
                break;
            case 2:
                account = new Account(Account.AccountType.SAVING, name, number, date, balance);
                break;
            case 3:
                account = new Account(Account.AccountType.CURRENT, name, number, date, balance);
                break;
            default:
                System.out.println("Invalid choice!");
                return false;
        }

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
        System.out.println("Amount deposited successfully!");
    }
    
    public boolean isPossibleWithdraw(Account acc, int amount, int minBalance) {
    	int remainingBalance = acc.getBalance() - amount;
    	if(remainingBalance >= minBalance) {
    		return true;
    	}
    	else return false;
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
