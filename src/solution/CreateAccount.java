package solution;

import java.util.List;
import java.util.Scanner;


public class CreateAccount extends BankingApplication{
	private BankingApplication bankingApp;
	
	public CreateAccount(BankingApplication bankingApp) {
        this.bankingApp = bankingApp;
    }

	 public void createAccount(int minBalanceToOpen, int minBalanceRemainAfterWithdraw) {
	        Scanner scanner = new Scanner(System.in);
	        
	        int number;
	        String name;
	        int choice;
	        String creationDate;
	        int balance;

	        System.out.println("Select account type\n1. Salary Account\n2. Savings Account\n3. Current Account\n");
	        choice = scanner.nextInt();
	        scanner.nextLine();

	        System.out.print("Enter name: ");
	        name = scanner.nextLine();
	        
	        do {
	            System.out.print("Enter account number: ");
	            number = scanner.nextInt();
	            if (!bankingApp.isAccountNumberUnique(number)) {
	                System.out.println("Account Number exists! Please use different number");
	            }
	        } while (!bankingApp.isAccountNumberUnique(number));

	        System.out.print("Enter creation date: ");
	        creationDate = scanner.next();
	        
	        do {
	            System.out.print("Enter account balance: ");
	            balance = scanner.nextInt();
	            if (balance < minBalanceToOpen) {
	                System.out.println("Opening Balance must be at least " + minBalanceToOpen + ".");
	            }
	        } while (balance < minBalanceToOpen);

	        Account account = null;
	        switch (choice) {
	            case 1:
	            	account = new SalaryAccount(name, number, creationDate, balance);
	            case 2:
	            	account = new SavingsAccount(name, number, creationDate, balance);
	            case 3 :
	            	account = new CurrentAccount(name, number, creationDate, balance);
	            default :
	            	System.out.println("Invalid choice!");
	        }

	        if (account != null) {
	            bankingApp.add(account);
	            System.out.println("Account successfully created.");
	        }
	    }
	}