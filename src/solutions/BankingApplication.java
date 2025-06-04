package solutions;

import java.util.Scanner;

public class BankingApplication {
    private final AccountService accountService;
    private final Validator validator;
    private final Scanner scanner;

    public BankingApplication() {
        this.accountService = new AccountService();
		this.validator = new Validator();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            handleChoice(choice);
        } while (choice != 8);
    }

    private void displayMenu() {
        System.out.println("\nAvailable Choices:");
        System.out.println("1. Create a new Account");
        System.out.println("2. Display all Accounts");
        System.out.println("3. Update Account Balance");
        System.out.println("4. Delete Account");
        System.out.println("5. Deposit Amount");
        System.out.println("6. Withdraw Amount");
        System.out.println("7. Search for Account");
        System.out.println("8. Exit");
    }

    private void handleChoice(int choice) {
    	
        switch (choice) {
            case 1:                
            	handleCreateAccount();
                break;

            case 2:
            	handleDisplayAccounts();
                break;

            case 3:
                handleUpdateBalance();
                break;

            case 4:
                handleDeleteAccount();
                break;

            case 5:
            	handleDeposit();
                break;

            case 6:
            	handleWithdraw();
                break;

            case 7:
                handleSearch();
                break;

            case 8:
                System.out.println("Exit the application");
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private void handleCreateAccount() {
        System.out.println("Select account type\n1. Salary Account\n2. Savings Account\n3. Current Account\n");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        int number;
        boolean valid = false;
        do {
            System.out.print("Enter account number: ");
            number = scanner.nextInt();
            valid = validator.isAccountNumberUnique(number, accountService.getAccounts());
        } while (!valid);

        System.out.print("Enter creation date: ");
        String creationDate = scanner.next();

        int balance;
        do {
            System.out.print("Enter account balance: ");
            balance = scanner.nextInt();
            valid = validator.accountBalanceAccepted(balance);
        } while (!valid);

        accountService.createAccount(type, name, number, creationDate, balance);
    }
    
    private void handleDisplayAccounts() {
    	accountService.displayAllAccounts();
    }
    
    private void handleUpdateBalance() {
    	System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        
        Account acc = accountService.searchAccount(accountNumber);
        if(acc == null) return;
        
        System.out.println("Enter amount to be updated: ");
        int amount = scanner.nextInt();
        
        accountService.updateAccountBalance(acc, amount);
    }
    
    private void handleDeleteAccount() {
    	System.out.print("Enter account number to delete: ");
        int accountNumber = scanner.nextInt();
        accountService.deleteAccount(accountNumber);
    }
    
    private void handleDeposit() {
    	System.out.print("Enter account number to deposit: ");
        int accountNumber = scanner.nextInt();
        
        Account acc = accountService.searchAccount(accountNumber);
        if(acc == null) return;
        
        System.out.println("Enter amount to be deposited: ");
        int amount = scanner.nextInt();
        
        accountService.depositAmount(acc, amount);
    }
    
    private void handleWithdraw() {
    	System.out.print("Enter account number to withdraw: ");
        int accountNumber = scanner.nextInt();
        
        Account acc = accountService.searchAccount(accountNumber);
        if(acc == null) return;
        
        int amount;
    	boolean valid = false;
    	do {
    		System.out.println("Enter amount to be withdrawn: ");
            amount = scanner.nextInt();
            valid = validator.isPossibleWithdraw(acc, amount);
        } while (!valid);
        
        accountService.withdrawAmount(acc, amount);
    }
    
    private void handleSearch() {
    	System.out.print("Enter account number to search: ");
        int accountNumber = scanner.nextInt();
        accountService.searchAccount(accountNumber);
    }
}
