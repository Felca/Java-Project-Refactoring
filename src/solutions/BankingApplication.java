package solutions;

import java.util.Scanner;

public class BankingApplication {
    private final AccountService accountService;
    private final Scanner scanner;

    private static final int MIN_BALANCE_TO_OPEN = 1000;
    private static final int MIN_BALANCE_REMAIN_AFTER_WITHDRAW = 1000;

    public BankingApplication() {
        this.accountService = new AccountService();
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
    	Account acc = null;
    	int amount = 0;
        int accountNumber;

        switch (choice) {
            case 1:                
            	System.out.println("Select account type\n1. Salary Account\n2. Savings Account\n3. Current Account\n");
                int type = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                
                int number;
                do {
                    System.out.print("Enter account number: ");
                    number = scanner.nextInt();
                    if (!accountService.isAccountNumberUnique(number)) {
                        System.out.println("Account Number exists! Please use a different number.");
                    }
                } while (!accountService.isAccountNumberUnique(number));

                System.out.print("Enter creation date: ");
                String creationDate = scanner.next();

                int balance;
                do {
                    System.out.print("Enter account balance: ");
                    balance = scanner.nextInt();
                    if (balance < MIN_BALANCE_TO_OPEN) {
                        System.out.println("Opening Balance must be at least " + MIN_BALANCE_TO_OPEN + ".");
                    }
                } while (balance < MIN_BALANCE_TO_OPEN);

                accountService.createAccount(type, name, number, creationDate, balance);
                
                break;

            case 2:
                accountService.displayAllAccounts();
                break;

            case 3:
                System.out.print("Enter account number: ");
                accountNumber = scanner.nextInt();
                
                acc = accountService.searchAccount(accountNumber);
                if(acc == null) break;
                
                System.out.println("Enter amount to be updated: ");
                amount = scanner.nextInt();
                
                accountService.updateAccountBalance(acc, amount);
                break;

            case 4:
                System.out.print("Enter account number to delete: ");
                accountNumber = scanner.nextInt();
                accountService.deleteAccount(accountNumber);
                break;

            case 5:
                System.out.print("Enter account number to deposit: ");
                accountNumber = scanner.nextInt();
                
                acc = accountService.searchAccount(accountNumber);
                if(acc == null) break;
                
                System.out.println("Enter amount to be deposited: ");
                amount = scanner.nextInt();
                
                accountService.depositAmount(acc, amount);
                break;

            case 6:
                System.out.print("Enter account number to withdraw: ");
                accountNumber = scanner.nextInt();
                
                acc = accountService.searchAccount(accountNumber);
                if(acc == null) break;
                
                boolean success = false;
                do {
                    System.out.print("Enter amount to be withdrawn: ");
                    amount = scanner.nextInt();

                    if (accountService.isPossibleWithdraw(acc, amount, MIN_BALANCE_REMAIN_AFTER_WITHDRAW)) {
                        System.out.println("Cannot withdraw. Minimum balance requirement not met.");
                        success = false;
                    } else {
                    	accountService.withdrawAmount(acc, amount);
                    	success = true;
                    }

                } while (!success);
                break;

            case 7:
                System.out.print("Enter account number to search: ");
                accountNumber = scanner.nextInt();
                accountService.searchAccount(accountNumber);
                break;

            case 8:
                System.out.println("Exit the application");
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }
}
