package solutions;

import java.util.List;

public class Validator {
	private static final int MIN_BALANCE_TO_OPEN = 1000;
	private static final int MIN_BALANCE_REMAIN_AFTER_WITHDRAW = 1000;
	
	public boolean isAccountNumberUnique(int accountNumber, List<Account> accounts) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
            	System.out.println("Account Number exists! Please use a different number.");
            	return false;
            }
        }
        return true;
    }

    public boolean isPossibleWithdraw(Account acc, int amount) {
        int remainingBalance = acc.getBalance() - amount;
        if(remainingBalance >= MIN_BALANCE_REMAIN_AFTER_WITHDRAW) {
        	return true;
        }
        else {
        	System.out.println("Cannot withdraw. Minimum balance requirement not met.");
        	return false;
        }
    }
    
    public boolean accountBalanceAccepted(int balance) {
    	if (balance < MIN_BALANCE_TO_OPEN) {
            System.out.println("Opening Balance must be at least " + MIN_BALANCE_TO_OPEN + ".");
            return false;
        }
    	return true;
    }
    
    
}
