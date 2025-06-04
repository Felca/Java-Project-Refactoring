package solutions;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
	Validator validator = new Validator();
    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
		return accounts;
	}

	public boolean isAccountExist(int accountNumber) {
        return searchAccount(accountNumber) != null;
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
        System.out.println("Balance updated successfully!");
    }

    public boolean deleteAccount(int accountNumber) {
        Account acc = searchAccount(accountNumber);
        if (acc != null) {
            accounts.remove(acc);
            System.out.println("Account deleted successfully!");
            return true;
        }
        return false;
    }

    public void depositAmount(Account acc, int amount) {
        acc.updateBalance(amount);
        System.out.println("Amount deposited successfully!");
    }

    public void withdrawAmount(Account acc, int amount) {
        acc.updateBalance(-amount);
        System.out.println("Successfully withdrawn amount!\n");
    }

    public Account searchAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                System.out.println("Account found!");
                account.display();
                return account;
            }
        }
        System.out.println("Account not found!");
        return null;
    }
}
