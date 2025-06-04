package solutions;

import java.util.Scanner;

public class UpdateAccountHandler {
	private final AccountService accountService;
    private final Scanner scanner;

    public UpdateAccountHandler(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    public void handle() {
        System.out.print("Enter account number: ");
        int number = scanner.nextInt();
        Account acc = accountService.getAccountByNumber(number);
        if (acc == null) return;

        System.out.print("Enter amount to update: ");
        int amount = scanner.nextInt();
        accountService.updateAccountBalance(acc, amount);
        System.out.println("Balance updated successfully!");
    }
}
