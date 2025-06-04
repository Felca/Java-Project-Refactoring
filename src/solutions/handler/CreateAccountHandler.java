package solutions.handler;

import java.util.Scanner;

import solutions.service.AccountService;

public class CreateAccountHandler {
	private AccountService accountService;
    private ValidatorHandler validation;
    private Scanner scanner;

    public CreateAccountHandler(AccountService service, ValidatorHandler validation, Scanner scanner) {
        this.accountService = service;
        this.validation =  new ValidatorHandler(accountService);
        this.scanner = scanner;
    }

    public void handle() {
    	System.out.println("Select account type\n1. Salary Account\n2. Savings Account\n3. Current Account\n");
        int type = scanner.nextInt();
        scanner.nextLine();
        
        if (type < 1 || type > 3) {
            System.out.println("Invalid choice!");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        int number;
        do {
            System.out.print("Enter account number: ");
            number = scanner.nextInt();
        } while (!validation.isAccountNumberUnique(number));

        System.out.print("Enter creation date: ");
        String creationDate = scanner.next();

        int balance=0;
        do {
            System.out.print("Enter account balance: ");
            balance = scanner.nextInt();
        } while (!validation.accountBalanceAccepted(balance));
                
        accountService.createAccount(type, name, number, creationDate, balance);
    }
}
