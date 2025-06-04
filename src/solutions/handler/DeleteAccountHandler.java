package solutions.handler;

import java.util.Scanner;

import solutions.service.AccountService;

public class DeleteAccountHandler {
	private final AccountService accountService;
    private final Scanner scanner;

    public DeleteAccountHandler(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    public void handle(){
        System.out.print("Enter account number to delete: ");
        int number = scanner.nextInt();
        boolean deleted = accountService.deleteAccount(number);
        if(deleted) {
            System.out.println("Account deleted successfully!");
        }
    }
}
