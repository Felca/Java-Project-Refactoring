package solution;
import java.util.List;
import java.util.Scanner;

public class BalanceAmount{

    public void updateAccountBalance(List<Account> accounts, int accountNumber, int amount, String actionMessage) {
        for (Account account : accounts) {
            if (account.number == accountNumber) {
                account.updateBalance(amount);
                System.out.println(actionMessage + " successfully!");
                return;
            }
        }
        System.out.println("Account not found!");
    }
}