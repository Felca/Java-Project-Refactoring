package solutions;

import java.util.Scanner;

import solutions.handler.*;
import solutions.service.AccountService;

public class BankingApplication {
	private AccountService accountService;
    private Scanner scanner;

    private CreateAccountHandler createAccHandler;
    private UpdateAccountHandler updateAccHandler;
    private DeleteAccountHandler delAccHandler;
    private TransactionHandler transactionHandler;
    private SearchHandler searchHandler;
    private ValidatorHandler validation;
    

    public static final int MIN_BALANCE_TO_OPEN = 1000;
    public static final int MIN_BALANCE_REMAIN_AFTER_WITHDRAW = 1000;

    public BankingApplication() {
        this.accountService = new AccountService();
        this.validation = new ValidatorHandler(accountService);
        this.scanner = new Scanner(System.in);
        
        this.createAccHandler = new CreateAccountHandler(accountService, validation, scanner);
        this.updateAccHandler = new UpdateAccountHandler(accountService, scanner);
        this.delAccHandler = new DeleteAccountHandler(accountService, scanner);
        this.transactionHandler = new TransactionHandler(accountService, validation, scanner);
        this.searchHandler = new SearchHandler(accountService, scanner);

    }
    
    private void displayMenu() {
    	System.out.println("\nAvailable Choices"
		    			+ "\n1.Create a new Account"
		    			+ "\n2.Display all accounts"
		    			+ "\n3.Update an account"
		    			+ "\n4.Delete an account"
		    			+ "\n5.Deposit an amount into your account"
		    			+ "\n6.Withdraw an amount from your account"
		    			+ "\n7.Search for an account"
		    			+ "\n8.Exit\n");
        System.out.println("Enter your choice: ");
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            handleChoice(choice);
        } while(choice != 8);
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
            	createAccHandler.handle();
            	break;
            case 2:
            	accountService.displayAllAccounts();
            	break;
            case 3:
            	 updateAccHandler.handle();
            	 break;
            case 4:
            	delAccHandler.handle();
            	break;
            case 5:
            	transactionHandler.handleDeposit();
            	break;
            case 6:
            	transactionHandler.handleWithdraw();
            	break;
            case 7:
            	searchHandler.handle();
            	break;
            case 8:
            	System.out.println("Exit the application");
            	break;
            default:
            	System.out.println("Invalid choice!");
        }
    }
}
