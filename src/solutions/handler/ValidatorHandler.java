package solutions.handler;

import java.util.List;

import solutions.model.Account;
import solutions.model.Money;
import solutions.service.AccountService;

public class ValidatorHandler {
	private static final int MIN_BALANCE_TO_OPEN = 1000;
	private static final int MIN_BALANCE_REMAIN_AFTER_WITHDRAW = 1000;
	
	private AccountService accountService;
	
    public ValidatorHandler(AccountService accountService) {
        this.accountService = accountService;
    }
	
	public boolean isAccountNumberUnique(int accountNumber) {
		List<Account> accounts = accountService.getAccounts();
    	for(Account account : accounts){
            if(account.getNumber() == accountNumber)
            {
            	System.out.println("Account Number exists! Please use different number");
                return false;
            }
        }
        return true;
    }

    
    public boolean isPossibleWithdraw(Account acc, int amount) {
        Money currentBalance = acc.getBalance();
        Money withdrawAmount = new Money(amount);

        try {
            Money remainingBalance = currentBalance.subtract(withdrawAmount);
            if (remainingBalance.getAmount() >= MIN_BALANCE_REMAIN_AFTER_WITHDRAW) {
                return true;
            } else {
                System.out.println("Cannot withdraw. Minimum balance requirement not met.");
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot withdraw: " + e.getMessage());
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
