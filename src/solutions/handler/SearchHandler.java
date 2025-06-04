package solutions.handler;

import java.util.Scanner;

import solutions.model.Account;
import solutions.service.AccountService;

public class SearchHandler {
	private final AccountService accountService;
    private final Scanner scanner;

    public SearchHandler(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    public void handle() {
        System.out.print("Enter account number to search: ");
        int number = scanner.nextInt();
        Account acc = accountService.getAccountByNumber(number);
        if (acc != null) {
            System.out.println("Account found!");
            acc.display();
        }
        else {
            System.out.println("Account not found!");
        }
    }
}
