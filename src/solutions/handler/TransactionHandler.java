package solutions.handler;

import java.util.Scanner;

import solutions.model.Account;
import solutions.service.AccountService;

public class TransactionHandler {
	private AccountService accountService;
	private ValidatorHandler validation;
    private Scanner scanner;

    public TransactionHandler(AccountService accountService, ValidatorHandler validation, Scanner scanner) {
        this.accountService = accountService;
        this.validation = new ValidatorHandler(accountService);
        this.scanner = scanner;
    }

    public void handleDeposit() {
        System.out.print("Enter account number: ");
        int number = scanner.nextInt();
        Account acc = accountService.getAccountByNumber(number);
        if (acc == null) return;

        System.out.print("Enter deposit amount: ");
        int amount = scanner.nextInt();
        accountService.depositAmount(acc, amount);
        System.out.println("Amount deposited successfully!");
    }

    public void handleWithdraw() {
    	int amount;
        boolean success;
        
        System.out.print("Enter account number: ");
        int number = scanner.nextInt();
        Account acc = accountService.getAccountByNumber(number);
        if (acc == null) return;

        do {
            System.out.print("Enter withdraw amount: ");
            amount = scanner.nextInt();
            
            success = validation.isPossibleWithdraw(acc, amount);
            if (success) {
                accountService.withdrawAmount(acc, amount);
                System.out.println("Successfully withdrawn amount!\n");
            }
        } while (!validation.isPossibleWithdraw(acc, number));
    }
}
