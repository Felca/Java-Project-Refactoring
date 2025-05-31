package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import solution.*;

public class BankingApplication
{
    private List<Account> accounts;
    private BalanceAmount balanceAmount;

    BankingApplication()
    {
        accounts = new ArrayList<>();
        balanceAmount = new BalanceAmount();
    }
    
    
    private boolean isAccountNumberUnique(int accountNumber)
    {
        for(Account account : accounts){
            if(account.number == accountNumber)
            {
                return  false;
            }
        }
        return  true;
    }

//    void createAccount(int minBalanceToOpen, int minBalanceRemainAfterWithdraw){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Select account type\n1. Salary Account\n2. Savings Account\n3. Current Account\n");
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        System.out.print("Enter name: ");
//        String name = scanner.nextLine();
//        int number;
//        do{
//            System.out.print("Enter account number: ");
//            number = scanner.nextInt();
//            if(!isAccountNumberUnique(number)){
//                System.out.println("Account Number exists! Please use different number");
//            }
//        }while (!isAccountNumberUnique(number));
//
//        System.out.print("Enter creation date: ");
//        String creationDate = scanner.next();
//
//        int balance;
//        do{
//            System.out.print("Enter account balance: ");
//            balance = scanner.nextInt();
//            System.out.print("\n");
//            if(balance < minBalanceToOpen)
//            {
//                System.out.println("Opening Balance must be at least " + minBalanceToOpen + ".");
//
//            }
//        } while (balance < minBalanceToOpen);
//        switch (choice)
//        {
//            case 1:
//                accounts.add(new SalaryAccount(name, number, creationDate, balance));
//                break;
//            case 2:
//                accounts.add(new SavingsAccount(name, number, creationDate, balance));
//                break;
//            case 3:
//                accounts.add(new CurrentAccount(name, number, creationDate, balance));
//                break;
//            default:
//                System.out.println("Invalid choice!");
//                break;
//        }
//    }

    void displayAllAccounts()
    {
        for (Account account : accounts) {
            account.display();
        }
    }

    private boolean isAccountExist(int accountNumber)
    {
        for(Account account : accounts){
            if(account.number == accountNumber)
            {
                return  true;
            }
        }
        return  false;
    }
//    void updateAccountBalance(int accountNumber)
//    {
//        if(isAccountExist(accountNumber))
//        {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter amount to be updated: ");
//            int amount = scanner.nextInt();
//            for (Account account : accounts) {
//                if (account.number == accountNumber) {
//                    account.updateBalance(amount);
//                    System.out.println("Balance updated successfully!");
//                    return;
//                }
//            }
//        }
//        System.out.println("Account not found!");
//    }

    void deleteAccount(int accountNumber)
    {
        boolean accountFound = false;

        for (Account account : accounts) {
            if (account.number == accountNumber) {
                accounts.remove(account);
                System.out.println("Account deleted successfully!");
                accountFound = true;
                break;
            }
        }

        if (!accountFound) {
            System.out.println("Account not found!");
        }
    }

//    void depositAmount(int accountNumber)
//    {
//        if(isAccountExist(accountNumber))
//        {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter amount to be deposited: ");
//            int amount = scanner.nextInt();
//            for (Account account : accounts) {
//                if (account.number == accountNumber) {
//                    account.updateBalance(amount);
//                    System.out.println("Amount deposited successfully!");
//                    return;
//                }
//            }
//        }
//        System.out.println("Account not found!");
//    }

    private boolean isPossibleWithdraw(int accountNumber, int amount, int minBalanceRemainAfterWithdraw)
    {
        boolean possibleWithdraw = false;

        for(Account account : accounts)
        {
            if(account.number == accountNumber)
            {
                int remainingBalance = account.balance - amount;

                if (remainingBalance >= minBalanceRemainAfterWithdraw) {
                    // System.out.println("withdraw hoise");
                    // account.updateBalance(-amount);
                    return true;
                }
            }
        }
        return possibleWithdraw;
    }



    void withdrawAmount(int accountNumber, int minBalanceRemainAfterWithdraw)
    {
        if(isAccountExist(accountNumber))
        {
            Scanner scanner = new Scanner(System.in);
            int amount;
            while (true) {
                System.out.println("Enter amount to be withdrawn: ");
                amount = scanner.nextInt();

                if (!isPossibleWithdraw(accountNumber, amount, minBalanceRemainAfterWithdraw)) {
                    System.out.println("Cannot withdraw. Minimum balance requirement not met.");
                } else {
                    break;
                }
            }


            for (Account account : accounts) {
                if (account.number == accountNumber) {

                    System.out.println("Successfully withdrawn amount!\n");
                    account.updateBalance(-amount);
                    return;

                }
            }
        }
        System.out.println("Account not found!");
    }

    void searchAccount(int accountNumber)
    {
        for (Account account : accounts) {
            if (account.number == accountNumber) {
                System.out.println("Account found!");
                account.display();
                return;
            }
        }
        System.out.println("Account not found!");
    }



}
