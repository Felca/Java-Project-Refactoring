package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int minBalanceToOpen = 1000;
    private static final int minBalanceRemainAfterWithdraw = 1000;

    public static void main(String[] args)
    {
        BankingApplication bankingapp = new BankingApplication();
        Scanner scanner = new Scanner(System.in);
        int choices;
        do {
            System.out.println("Available Choices\n1.Create a new Account\n2.Display all accounts\n3.Update an account\n4.Delete an account\n5.Deposit an amount into your account\n6.Withdraw an amount from your account\n7.Search for an account\n8.Exit\n");
            System.out.println("Enter your choice: ");
            choices = scanner.nextInt();

            switch (choices)
            {
                case 1:
                    bankingapp.createAccount(minBalanceToOpen, minBalanceRemainAfterWithdraw);
                    break;
                case 2:
                    bankingapp.displayAllAccounts();
                    break;
                case 3:
                    int accountNumber;
                    System.out.println("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    bankingapp.updateAccountBalance(accountNumber);
                    break;
                case 4:
                    System.out.println("Enter account number to delete: ");
                    accountNumber = scanner.nextInt();
                    bankingapp.deleteAccount(accountNumber);
                    break;
                case 5:
                    System.out.println("Enter account number to deposit: ");
                    accountNumber = scanner.nextInt();
                    bankingapp.depositAmount(accountNumber);
                    break;
                case 6:
                    System.out.println("Enter account number to withdraw: ");
                    accountNumber = scanner.nextInt();

                    bankingapp.withdrawAmount(accountNumber, minBalanceRemainAfterWithdraw);
                    break;
                case 7:
                    System.out.println("Enter account number to search: ");
                    accountNumber = scanner.nextInt();
                    bankingapp.searchAccount(accountNumber);
                    break;
                case 8:
                    System.out.println("Exit the application");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        }while(choices != 8);

    }

    
}
