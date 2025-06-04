package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * smell code : large class
 * reason: Class Main mencakupi semua class yang ada sehingga terlalu panjang dan memiliki lebih dari satu tanggung jawab
 * solution: Extract class
 * 
 * smell code : long method
 * reason: Method yang memiliki code yang terlalu banyak dan panjang.
 * solution: Extract method
 * 
 * smell code : comments
 * reason: Terdapat comment yang lupa di delete oleh developer di BankingApplication pada method isPossibleWithdraw().
 * solution: Delete comment
 * 
 * smell code : duplicate code
 * reason: Terdapat beberapa duplicate code dalam beberapa bagian
 * solution: Delete code
 * 
 * smell code : switch statement
 * reason: Switch Statement yang sangat panjang dan kompleks
 * solution: Extract method/Make method
 * 
 * smell code : temporary field
 * reason: Suatu variabel dalam class hanya digunakan dalam beberapa situasi tertentu, hal ini merugikan dikarenakan memakan memory, dan membingungkan karena memiliki nilai yang tidak valid (Terdapat pada deleteAccount())
 * solution: Delete field
 * 
 * smell code : divergent change
 * reason: Class BankingApplication memiliki sangat banyak kode dan method dalam satu class
 * solution: Extract class
 * 
 * smell code : shotgun surgery
 * reason: Penggunaan account.number dan account.balance merupakan pengulangan logic yang sama yang digunakan di berbagai method. Apabila terjadi perubahan, maka harus mengubah juga account.number dan account.balance yang di method lain.
 * solution: Move method/Encapsulation
 * 
 * smell code : dead code
 * reason: Terdapat dead code di code original, yaitu di bagian method isPossibleWithdraw(), karena terdapat variabel “possibleWithdraw” yang sebenarnya tidak ada gunanya
 * solution: Delete dead code
 * 
 * smell code : speculative generality
 * reason: Pada bagian abstract class Account, code tersebut dibuat tetapi tidak beroperasi dengan baik, karena diasumsikan bahwa penggunaannya akan ada perbedaan untuk subclassnya, dimana implementasi code dari subclass Account menggunakan implementasi yang sama.
 * solution: Delete not-yet-used variables/methods/classes
 * 
 * smell code : feature envy
 * reason: Class BankingApplication sering mengakses data dari class Account seperti yang dapat kita lihat pada method isPossibleWithdraw() yang mengakses field account.number dan account.balance secara langsung
 * solution: Move method
 * 
 * smell code : inappropriate intimacy
 * reason: Terdapat di method isPossibleWithdraw() di baris if(account.number == accountNumber) int remainingBalance = account.balance - amount. Itu dikarenakan class BankingApplication bisa mengakses secara langsung ke class Account dan mengetahui terlalu banyak apa yang di dalam class Account.
 * solution: Encapsulation of fields
 * 
 * smell code : middle man
 * reason: Method depositAmount() dimana kelas dimana depositAccount() berada, yaitu BankingApplication, hanya membantu “mencarikan” akun yang dicari dan serahkan data yang ditemukannya/tugasnya ke account.updateBalance() yang ada di class Account.
 * solution: Removing middle man
 * 
 * smell code : multifaceted abstraction
 * reason: Class Main menanggung sangat banyak responsibility karena mengandung banyak class (inner class)
 * solution: Extract class
 * 
 * smell code : deficient encapsulation
 * reason: Hampir semua variabel/field tidak mempunyai identifier, dan karena itu, class yang berbeda bisa mengakses ke kelas lain secara langsung, contohnya account.number di beberapa method yang mengakses dari class lain seperti BankingApplication ke class Account.
 * solution: Give private identifiers untuk fields
 * 
 * smell code : insufficient modularization
 * reason: Class Main dan BankingApplication yang menampung sangat banyak code yang tidak dipisah ke modul lainnya.
 * solution: Extract modules
 * 
 * smell code : wide hierarchy
 * reason: SavingsAccount, SalaryAccount, dan CurrentAccount mempunyai behavior yang sama persis dengan satu sama lain dan tidak menambahkan behavior yang baru ataupun field yang unik dari subclass tersebut.
 * solution: Using enum and deleting unnecessary subclasses
 */



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
                    bankingapp.createAccount();
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

                    bankingapp.withdrawAmount(accountNumber);
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
    private  static  class BankingApplication
    {
        private List<Account> accounts;

        BankingApplication()
        {
            accounts = new ArrayList<>();
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

        void createAccount(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select account type\n1. Salary Account\n2. Savings Account\n3. Current Account\n");
            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            int number;
            do{
                System.out.print("Enter account number: ");
                number = scanner.nextInt();
                if(!isAccountNumberUnique(number)){
                    System.out.println("Account Number exists! Please use different number");
                }
            }while (!isAccountNumberUnique(number));

            System.out.print("Enter creation date: ");
            String creationDate = scanner.next();

            int balance;
            do{
                System.out.print("Enter account balance: ");
                balance = scanner.nextInt();
                System.out.print("\n");
                if(balance < minBalanceToOpen)
                {
                    System.out.println("Opening Balance must be at least " + minBalanceToOpen + ".");

                }
            } while (balance < minBalanceToOpen);
            switch (choice)
            {
                case 1:
                    accounts.add(new SalaryAccount(name, number, creationDate, balance));
                    break;
                case 2:
                    accounts.add(new SavingsAccount(name, number, creationDate, balance));
                    break;
                case 3:
                    accounts.add(new CurrentAccount(name, number, creationDate, balance));
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }

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
        void updateAccountBalance(int accountNumber)
        {
            if(isAccountExist(accountNumber))
            {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter amount to be updated: ");
                int amount = scanner.nextInt();
                for (Account account : accounts) {
                    if (account.number == accountNumber) {
                        account.updateBalance(amount);
                        System.out.println("Balance updated successfully!");
                        return;
                    }
                }
            }
            System.out.println("Account not found!");
        }

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

        void depositAmount(int accountNumber)
        {
            if(isAccountExist(accountNumber))
            {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter amount to be deposited: ");
                int amount = scanner.nextInt();
                for (Account account : accounts) {
                    if (account.number == accountNumber) {
                        account.updateBalance(amount);
                        System.out.println("Amount deposited successfully!");
                        return;
                    }
                }
            }
            System.out.println("Account not found!");
        }

        private boolean isPossibleWithdraw(int accountNumber, int amount)
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



        void withdrawAmount(int accountNumber)
        {
            if(isAccountExist(accountNumber))
            {
                Scanner scanner = new Scanner(System.in);
                int amount;
                while (true) {
                    System.out.println("Enter amount to be withdrawn: ");
                    amount = scanner.nextInt();

                    if (!isPossibleWithdraw(accountNumber, amount)) {
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

    private static abstract class Account
    {
        String name, creationDate;
        int number, balance;
        Account(String n, int num, String date, int bal) {
            name = n;
            number = num;
            creationDate = date;
            balance = bal;
        }

        abstract void display();

        abstract void updateBalance(int amount);
    }
    private static class SavingsAccount extends Account
    {
        SavingsAccount(String n, int num, String date, int bal) {
            super(n, num, date, bal);
        }
        void display() {
            System.out.println("Savings Account - Name: " + name + ", Account Number: " + number +
                    ", Creation Date: " + creationDate + ", Balance: " + balance);
        }
        void updateBalance(int amount) {
            balance += amount;
        }
    }

    private static class SalaryAccount extends Account
    {
        SalaryAccount(String n, int num, String date, int bal) {
            super(n, num, date, bal);
        }
        void display() {
            System.out.println("Salary Account - Name: " + name + ", Account Number: " + number +
                    ", Creation Date: " + creationDate + ", Balance: " + balance);
        }
        void updateBalance(int amount) {
            balance += amount;
        }
    }

    private static class CurrentAccount extends Account
    {
        CurrentAccount(String n, int num, String date, int bal) {
            super(n, num, date, bal);
        }
        void display() {
            System.out.println("Current Account - Name: " + name + ", Account Number: " + number +
                    ", Creation Date: " + creationDate + ", Balance: " + balance);
        }
        void updateBalance(int amount) {
            balance += amount;
        }
    }
}
