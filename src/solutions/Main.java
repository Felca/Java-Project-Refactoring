package solutions;

import java.util.Scanner;

public class Main {
    private static final int minBalanceToOpen = 1000;
    private static final int minBalanceRemainAfterWithdraw = 1000;

    public static void main(String[] args) {
        BankingApplication bankingApp = new BankingApplication();
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            
            processChoice(choice, bankingApp, scanner);
            
        } while (choice != 8);
    }
    
    private static void displayMenu() {
        System.out.println("\nAvailable Choices");
        System.out.println("1. Create a new Account");
        System.out.println("2. Display all accounts");
        System.out.println("3. Update an account");
        System.out.println("4. Delete an account");
        System.out.println("5. Deposit an amount into your account");
        System.out.println("6. Withdraw an amount from your account");
        System.out.println("7. Search for an account");
        System.out.println("8. Exit\n");
    }
    
    private static void processChoice(int choice, BankingApplication bankingApp, Scanner scanner) {
        int accountNumber;
        
        switch (choice) {
            case 1:
                bankingApp.createAccount(minBalanceToOpen, minBalanceRemainAfterWithdraw);
                break;
            case 2:
                bankingApp.displayAllAccounts();
                break;
            case 3:
                System.out.print("Enter account number: ");
                accountNumber = scanner.nextInt();
                bankingApp.updateAccountBalance(accountNumber);
                break;
            case 4:
                System.out.print("Enter account number to delete: ");
                accountNumber = scanner.nextInt();
                bankingApp.deleteAccount(accountNumber);
                break;
            case 5:
                System.out.print("Enter account number to deposit: ");
                accountNumber = scanner.nextInt();
                bankingApp.depositAmount(accountNumber);
                break;
            case 6:
                System.out.print("Enter account number to withdraw: ");
                accountNumber = scanner.nextInt();
                bankingApp.withdrawAmount(accountNumber, minBalanceRemainAfterWithdraw);
                break;
            case 7:
                System.out.print("Enter account number to search: ");
                accountNumber = scanner.nextInt();
                bankingApp.searchAccount(accountNumber);
                break;
            case 8:
                System.out.println("Exit the application");
                break;
            default:
                System.out.println("Invalid Choice!");
        }
    }
}
